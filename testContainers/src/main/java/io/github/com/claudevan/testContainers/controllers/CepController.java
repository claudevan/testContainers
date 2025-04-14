package io.github.com.claudevan.testcontainers.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/cep")
public class CepController {

    @GetMapping("{cep}")
    public String getAddress(@PathVariable String cep) {
        return "está funcionando " + cep;
    }
}
