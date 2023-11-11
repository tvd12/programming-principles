package org.youngmonkeys.refactoring.v2.controller;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.youngmonkeys.refactoring.v2.service.GreetService;

@RestController
@AllArgsConstructor
public class HomeController {

    private final GreetService greetService;

    @GetMapping("/api/v1/greet")
    public String greet(@RequestParam("name") String name) {
        return greetService.greet(name);
    }
}
