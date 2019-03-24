下载，安装 thrift 

下载地址：http://www.apache.org/dyn/closer.cgi?path=/thrift/0.12.0/thrift-0.12.0.tar.gz

mac 安装：
```
brew install thrift

```
查看 thrift 版本:

```
thrift --version

```

进入 hk-netty-example/src/main 目录，执行如下命令：

```
huangkaideMBP:~ huangkai$> brew install thrift
```

生成 java 代码，如下，会在当前目录生成 gen-java 目录，可以将生成的文件复制到指定的目录中，生成的文件不要做任何修改。
```
huangkaideMBP:main huangkai$ pwd
/Users/huangkai/worksplace/hk-examples/hk-netty-example/src/main
huangkaideMBP:main huangkai$ thrift --gen java thrift/data.thrift

```
