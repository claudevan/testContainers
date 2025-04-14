package io.github.com.claudevan.testcontainers.services;

import io.github.com.claudevan.testcontainers.domain.CepDto;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class CepService {


    public CepDto getAddress(String cep) {
        CepDto data;

        //buscar no redis

        //buscar na base

        //buscar na API
        data = getAddressFromApi(cep);

        //Esquentar a base

        //Esquentar redis

        return data;
    }

    private CepDto getAddressFromApi(String cep) {
        try {
            String apiUrl = "https://viacep.com.br/ws/" + cep + "/json/";
            RestTemplate restTemplate = new RestTemplate();
            return restTemplate.getForObject(apiUrl, CepDto.class);
        } catch (Exception e) {
            throw new RuntimeException("Error fetching address from ViaCEP API: " + e.getMessage());
        }
    }


}
