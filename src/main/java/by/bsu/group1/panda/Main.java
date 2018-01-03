package by.bsu.group1.panda;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.PrintWriter;
import java.sql.DriverManager;

@SpringBootApplication
public class Main {
    public static void main(String[] args) {
        DriverManager.setLogWriter(new PrintWriter(System.out));
        SpringApplication.run(Main.class, args);
    }
}