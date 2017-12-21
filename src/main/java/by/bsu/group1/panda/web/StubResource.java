package by.bsu.group1.panda.web;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class StubResource {

    @Value("${panda.message.stub}")
    private String stubMessage;

    @RequestMapping("/")
    String index() {
        return stubMessage;
    }
}