package org.youngmonkeys.refactoring.v1.controller;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.youngmonkeys.refactoring.v1.service.GreetService;

@Controller
@AllArgsConstructor
public class HomeController {

    private final GreetService greetService;

    @ResponseBody
    @RequestMapping(value = "/api/v1/greet", method = RequestMethod.GET)
    public String greet(@RequestParam("name") String name) {
        return greetService.greet(name);
    }
}
