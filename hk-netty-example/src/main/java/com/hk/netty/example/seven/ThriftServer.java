package com.hk.netty.example.seven;

import com.hk.thrift.PersonService;
import org.apache.thrift.TProcessorFactory;
import org.apache.thrift.protocol.*;
import org.apache.thrift.server.THsHaServer;
import org.apache.thrift.server.TServer;
import org.apache.thrift.transport.*;


/**
 * 启动类
 *
 * @author huangkai
 * @date 2019-03-23 21:35
 */
public class ThriftServer {

    public static void main(String[] args) throws TTransportException {
        TNonblockingServerSocket serverSocket = new TNonblockingServerSocket(8899);
        THsHaServer.Args arg = new THsHaServer.Args(serverSocket)
                .minWorkerThreads(2)
                .maxWorkerThreads(4);
        PersonService.Processor<ThriftPersonServiceImpl> processor = new PersonService.Processor<>(
                new ThriftPersonServiceImpl());
        /**
         * 指定数据传输格式
         *  new TCompactProtocol.Factory(): 压缩格式，开发中用得最多的一种
         *  new TBinaryProtocol.Factory(): 二进制格式，开发中用得第二多的一种
         *  new TJSONProtocol.Factory() :  json 格式
         *  new TSimpleJSONProtocol.Factory(): 提供 json 只写协议，生成的文件容易通过脚本语言解析，极少使用
         *  new TTupleProtocol.Factory(): 数组格式
         */

        arg.protocolFactory(new TCompactProtocol.Factory());

        /**
         * 数据传输方式：
         * new TFramedTransport.Factory(): 以 frame 为单位传输，非阻塞式服务模型使用。
         * new TFastFramedTransport.Factory():
         * new TZlibTransport.Factory(): 使用 zlib 进行压缩
         * new TSaslServerTransport.Factory():
         * new THttpClient.Factory():
         *
         */
        arg.transportFactory(new TFramedTransport.Factory());
        arg.processorFactory(new TProcessorFactory(processor));

        /**
         * 支持的服务模型：
         * new TSimpleServer(): 简单的单线程服务模型，常用于测试
         *
         * new TThreadPoolServer(): 多线程服务模型，使用标准的阻塞式 IO
         *
         * new TNonblockingServer(): 多线程服务模型，使用非阻塞式 IO ，需要使用 TFramedTransport 数据传输方式。
         *
         * new THsHaServer():  引入了线程池去处理，其模型把读写任务放到线程池中， half-sync/half-async 的处理模式，
         *                      half-async 是在处理IO 事件上(accept/read/write IO)，
         *                      half-sync 是用于 handler 对 rpc 的同步处理。
         *
         */
        TServer server = new THsHaServer(arg);
        server.serve();//异步非阻塞，死循环
    }
}
