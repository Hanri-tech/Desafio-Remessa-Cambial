package br.com.desafio.remessa;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class RemessaApplication {

    public static void main(String[] args) {
        SpringApplication.run(RemessaApplication.class, args);
    }

}
