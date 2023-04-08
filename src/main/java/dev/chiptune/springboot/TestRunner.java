package dev.chiptune.springboot;

import java.sql.Connection;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

/**
 * @apiNote H2 Database Runner Sample
 * @version 1.0
 * @author DK
 * @since 2022.07
 */
@Component
public class TestRunner implements ApplicationRunner {

    @Autowired
    DataSource dataSource;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        Connection connection = dataSource.getConnection();
        System.out.println("url : " + connection.getMetaData().getURL());
        System.out.println("userName : " + connection.getMetaData().getUserName());
    }

}
