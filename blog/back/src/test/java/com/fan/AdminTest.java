package com.fan;

import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootTest
public class AdminTest {

    @Test
    public void test() {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        String encode = encoder.encode("root");
        System.out.println(encode); // $2a$10$ODNox74WrVz/c0hL8E0CzOWqoMUg89YTHhkc7ZsUf0BCe6W.V17yC
        System.out.println("match="+encoder.matches("root","$2a$10$ODNox74WrVz/c0hL8E0CzOWqoMUg89YTHhkc7ZsUf0BCe6W.V17yC"));
    }
}
