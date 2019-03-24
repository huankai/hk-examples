package com.hk.netty.example.seven;

import com.hk.thrift.Person;
import com.hk.thrift.PersonService;
import org.apache.thrift.protocol.TCompactProtocol;
import org.apache.thrift.protocol.TProtocol;
import org.apache.thrift.transport.TFramedTransport;
import org.apache.thrift.transport.TSocket;
import org.apache.thrift.transport.TTransport;

/**
 * @author huangkai
 * @date 2019-03-23 21:41
 */
public class ThriftClient {

    public static void main(String[] args) {
        // 这里的 TFramedTransport 要与  ThriftServer 类中的 arg.transportFactory(new TFramedTransport.Factory()); 一致。
        TTransport transport = new TFramedTransport(new TSocket("localhost", 8899), 600);
        // 这里的 TCompactProtocol 要与  arg.protocolFactory(new TCompactProtocol.Factory()); 一致。
        TProtocol protocol = new TCompactProtocol(transport);
        PersonService.Client client = new PersonService.Client(protocol);
        try {
            transport.open();
            Person person = new Person();
            person.setAge(10);
            person.setUsername("admin");
            person.setMarried(false);
            client.save(person);

            person = client.getByUsername("admin");
            if (null != person) {
                System.out.println(person.getAge());
                System.out.println(person.getUsername());
                System.out.println(person.isMarried());
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            transport.close();
        }
    }
}
