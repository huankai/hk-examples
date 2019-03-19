下载，安装 protoc ，并配置环境变量:

下载地址：https://github.com/protocolbuffers/protobuf/releases

查看 protoc 版本:

```
protoc --version

```

进入 hk-netty-example/src/main 目录，执行如下命令：会在 hk-netty-example/src/main/java/com/hk/protobuf 目录下生成 DataInfo.java文件
注意：不要修改生成的 java文件内容，否则会出现问题

```
G:\worksplace\hk\src\hk-examples\hk-netty-example\src\main> protoc --java_out=./java ./protobuf/Student.proto
```

