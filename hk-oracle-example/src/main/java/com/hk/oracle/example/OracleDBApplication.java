package com.hk.oracle.example;

import com.hk.oracle.example.entity.IcCard;
import com.hk.oracle.example.entity.IcCardTransLineView;
import com.hk.oracle.example.entity.IcCustomer;
import com.hk.oracle.example.repository.IcCardRepository;
import com.hk.oracle.example.repository.IcCardTransLineViewRepository;
import com.hk.oracle.example.repository.IcCustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author kevin
 * @date 2018-09-10 10:36
 */
@SpringBootApplication
public class OracleDBApplication {

    public static void main(String[] args) {
        SpringApplication.run(OracleDBApplication.class, args);
    }

    @Component
    public class MyCommandLineRunner implements CommandLineRunner {

        @Autowired
        private IcCardRepository icCardRepository;

        @Autowired
        private IcCardTransLineViewRepository cardTransLineViewRepository;

        @Autowired
        private IcCustomerRepository customerRepository;

        @Override
        public void run(String... args) {
            List<IcCard> list = icCardRepository.findAll();
            System.out.println("IcCard Size : " + list.size());

            List<IcCustomer> customerList = customerRepository.findAll();
            System.out.println("IcCustomer Size : " + customerList.size());

            List<IcCardTransLineView> viewList = cardTransLineViewRepository.findAll();
            System.out.println("IcCardtranslineView Size : " + viewList.size());
        }
    }

}
