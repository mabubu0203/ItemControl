package jp.co.valtech.items.rdb;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;

@SpringBootApplication
@Import({RdbCore.class})
public class RdbCoreTest {
    public static void main(String[] args) {
        SpringApplication.run(RdbCoreTest.class, args);
    }
}
