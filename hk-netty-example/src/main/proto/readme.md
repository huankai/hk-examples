google grpc 示例：

文档地址 ：https://github.com/grpc/grpc-java

# 一、在 pom 文件中配置 maven 依赖与插件 #

```
<dependencies>
<dependency>
    <groupId>io.grpc</groupId>
    <artifactId>grpc-netty-shaded</artifactId>
    <version>${google.grpc.version}</version>
</dependency>
<dependency>
    <groupId>io.grpc</groupId>
    <artifactId>grpc-protobuf</artifactId>
    <version>${google.grpc.version}</version>
</dependency>
<dependency>
    <groupId>io.grpc</groupId>
    <artifactId>grpc-stub</artifactId>
    <version>${google.grpc.version}</version>
</dependency>
</dependencies>

<build>
        <extensions>
            <extension>
                <groupId>kr.motd.maven</groupId>
                <artifactId>os-maven-plugin</artifactId>
                <version>1.5.0.Final</version>
            </extension>
        </extensions>
        <plugins>
            <plugin>
                <groupId>org.xolstice.maven.plugins</groupId>
                <artifactId>protobuf-maven-plugin</artifactId>
                <version>0.5.1</version>
                <configuration>
                    <protocArtifact>com.google.protobuf:protoc:3.6.1:exe:${os.detected.classifier}</protocArtifact>
                    <pluginId>grpc-java</pluginId>
                    <pluginArtifact>io.grpc:protoc-gen-grpc-java:1.19.0:exe:${os.detected.classifier}</pluginArtifact>
                </configuration>
                <executions>
                    <execution>
                        <goals>
                            <goal>compile</goal>
                            <goal>compile-custom</goal>
                        </goals>
                    </execution>
                </executions>
            </plugin>
        </plugins>
    </build>
```

# 二、创建 proto 文件 #

在 src/main/proto 目录中创建 proto 文件 ，如这里的 Student.proto

# 三、通过插件生成java 源代码 #
打开 Maven 视图窗口，找到 hk-nettty-example 项目，点击 Lifecycle -> compile，会在 target/generated-sources/protobuf/java 和 target/generated-sources/protobuf/grpc-java 目录下生成 java 源码文件，
将生成的源码文件移动到 src/main/java 目录中，生成的文件不要修改。


