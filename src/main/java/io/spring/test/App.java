package io.spring.test;

import io.spring.test.service.CustomerService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.io.File;
import java.io.IOException;

/**
 * Created by IDEA
 * User: ride
 * Date: 17-10-12
 * Time: 下午12:27
 */
public class App {
    public static void main(String[] args) {
/*        File file = new File("test.txt");
        try {
            file.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }*/
        ApplicationContext context = new ClassPathXmlApplicationContext("spring-context.xml");
        System.out.println(context);
        CustomerService service = (CustomerService) context.getBean("customerService");
        System.out.println(service.getCustomerDao().toString());
    }
}
