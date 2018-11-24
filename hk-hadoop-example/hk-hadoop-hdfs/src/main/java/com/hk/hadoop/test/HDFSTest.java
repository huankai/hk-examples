package com.hk.hadoop.test;


import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.*;
import org.apache.hadoop.fs.permission.FsAction;
import org.apache.hadoop.fs.permission.FsPermission;
import org.apache.zookeeper.common.IOUtils;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * @author huangkai
 * @date 2018-6-23 9:12
 */
public class HDFSTest {

    private FileSystem fileSystem;

    @Before
    public void init() throws IOException {
        Configuration conf = new Configuration();
        //配置使用的是 HDFS 文件系统，如果不指定，会使用 core-default.xml（在hadoop-common jar包中） 中的默认配置
        conf.set("fs.defaultFS", "hdfs://192.168.64.128:9000");
        //配置 HADOOP 的用户名，Linux 上的每个文件或目录都有可读、可写、可执行的权限，这里需要保证上传的文件需要有可写的权限
        System.setProperty("HADOOP_USER_NAME", "huangkai");
        fileSystem = FileSystem.get(conf);
//        也可以使用如下方式来获取 FileSystem对象，不需要在 conf中配置 fs.defaultFS 和 HADOOP_USER_NAME这两个参数
//        FileSystem.get(new URI("hdfs://192.168.64.1:9000"), conf, "root");
    }

    /**
     * Create 文件
     */
    @Test
    public void createTest() throws IOException {
        //在根目录创建 JavaHelloDir 文件
        fileSystem.create(new Path("/javaHelloDir"), true);
    }

    /**
     * Create 目录
     *
     * @throws IOException
     */
    @Test
    public void createDirTest() throws IOException {
        fileSystem.mkdirs(new Path("/test2"), new FsPermission(FsAction.ALL, FsAction.ALL, FsAction.ALL));
    }

    /**
     * 删除目录
     *
     * @throws IOException
     */
    @Test
    public void deleteDirTest() throws IOException {
        //删除目录，第二个参数表示是否递归删除子级
        fileSystem.delete(new Path("/user"), true);
    }

    /**
     * 重命名目录
     *
     * @throws IOException
     */
    @Test
    public void renameDirTest() throws IOException {
        fileSystem.rename(new Path("/test"), new Path("/test2"));
    }

    /**
     * Upload File
     *
     * @throws IOException
     */
    @Test
    public void uploadFileTest() throws IOException {
//       将本地 D盘 a.txt文件上传到 JavaHelloDir 目录中
        fileSystem.copyFromLocalFile(new Path("D:/a.txt"), new Path("/JavaHelloDir"));
    }

    /**
     * Down File
     *
     * @throws IOException
     */
    @Test
    public void downFileTest() throws IOException {
        //将 /JavaHelloDir/a.txt 下载到D盘 b.txt,在 windows 平台下，需要配置hadoop环境，查看 ： https://blog.csdn.net/woshixuye/article/details/53537519
        fileSystem.copyToLocalFile(new Path("/JavaHelloDir/a.txt"), new Path("D:/b.txt"));
    }

    /**
     * 获取文件信息
     *
     * @throws IOException
     */
    @Test
    public void fileInfoTest() throws IOException {
        RemoteIterator<LocatedFileStatus> files = fileSystem.listFiles(new Path("/"), true);
        while (files.hasNext()) {
            LocatedFileStatus file = files.next();
            System.out.println(file.getBlockSize());
            System.out.println(file.getAccessTime());
            System.out.println(file.getPath().getName());
            System.out.println(file.getPermission());
            System.out.println(file.getLen());
            for (BlockLocation blockLocation : file.getBlockLocations()) {
                System.out.println(blockLocation.getLength());
                System.out.println(blockLocation.getOffset());
            }

        }
    }

    /**
     * 使用 stream 操作 上传文件,是更加底层的操作形式
     *
     * @throws IOException
     */
    @Test
    public void streamOperatorUploadTest() throws IOException {
        FileInputStream inputStream = new FileInputStream("E:\\Windows\\Navicat.zip");
        FSDataOutputStream outputStream = fileSystem.create(new Path("/Navicat.zip"), true);
//        IOUtils.copy(inputStream, outputStream);
        IOUtils.copyBytes(inputStream, outputStream, 4096, false);
        IOUtils.closeStream(inputStream);
        IOUtils.closeStream(outputStream);
    }

    /**
     * 使用 stream 操作 下载文件,是更加底层的操作形式
     *
     * @throws IOException
     */
    @Test
    public void streamOperatorDownTest() throws IOException {
        FSDataInputStream fsDataInputStream = fileSystem.open(new Path("/natapp_windows_amd64_2_3_8.zip"));
        IOUtils.copyBytes(fsDataInputStream, new FileOutputStream(new File("E:/natapp_windows_amd64_2_3_8.zip")), 4096, false);
        IOUtils.closeStream(fsDataInputStream);
    }


    /* **********************************************分开读取文件内容 start******************************************************************** */

    /**
     * 分开读取文件内容 ，先读取第一部分
     *
     * @throws IOException
     */
    private void readFileSeek1() throws IOException {
        FSDataInputStream dataInputStream = fileSystem.open(new Path("/Navicat.zip"));
        FileOutputStream outputStream = new FileOutputStream(new File("E:/navicat.zip.part1"));
        byte[] buf = new byte[1024];
        for (int i = 0; i < 50 * 1024; i++) {
            dataInputStream.read(buf);
            outputStream.write(buf);
        }
        IOUtils.closeStream(dataInputStream);
        IOUtils.closeStream(outputStream);
    }

    /**
     * 分开读取文件内容 ，读取剩下部分
     *
     * <p>
     * 在window命令窗口中执行 type navicat.zip.part1 nav  icat.zip.part2 >> navicat.zip 将第一部分与第二部分文件合并到navicat.zip文件中
     * </p>
     *
     * @throws IOException
     */
    @Test
    public void readFileSeek2() throws IOException {
        readFileSeek1();
        FSDataInputStream dataInputStream = fileSystem.open(new Path("/Navicat.zip"));
        FileOutputStream outputStream = new FileOutputStream(new File("E:/navicat.zip.part2"));
        dataInputStream.seek(1024 * 1024 * 50);
        IOUtils.copyBytes(dataInputStream, outputStream, 1024);
        IOUtils.closeStream(dataInputStream);
        IOUtils.closeStream(outputStream);
    }

    /* ************************************************分开读取文件内容 end****************************************************************** */

    @After
    public void after() throws IOException {
        if (null != fileSystem) {
            fileSystem.close();
        }
    }

}
