package io.github.com.claudevan.testcontainers.controllers;

import io.github.com.claudevan.testcontainers.domain.CepDto;
import io.github.com.claudevan.testcontainers.services.CepService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/cep")
public class CepController {

    private final CepService cepService;

    public CepController(CepService cepService) {
        this.cepService = cepService;
    }

    @GetMapping("{cep}")
    public CepDto getAddress(@PathVariable String cep) {

        return cepService.getAddress(cep);
    }
}
