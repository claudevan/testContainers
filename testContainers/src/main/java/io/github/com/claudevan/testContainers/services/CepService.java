package io.github.com.claudevan.testcontainers.services;

import io.github.com.claudevan.testcontainers.domain.CepDto;
import io.github.com.claudevan.testcontainers.domain.entity.CepEntity;
import io.github.com.claudevan.testcontainers.domain.repository.CepRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@Slf4j
public class CepService {

    private final CepRepository cepRepository;

    public CepService(CepRepository cepRepository) {
        this.cepRepository = cepRepository;
    }

    public CepDto getAddress(String cep) {
        CepDto data = new CepDto();

        //buscar na base
        var cepEntity = cepRepository.findByCep(cep);

        //buscar na API
        if (cepEntity == null) {
            log.info("Address not found in database, fetching from API...");

            data = getAddressFromApi(cep);

            log.info("Address fetched from API: {}", data);
            cepRepository.save(CepEntity.Builder(data));
            log.info("Address saved in database");
        }else{
            log.info("Address found in database: {}", cepEntity);
            data = CepDto.Builder(cepEntity);
        }

        return data;
    }

    private CepDto getAddressFromApi(String cep) {
        try {
            log.info("Fetching address from ViaCEP API...");

            String apiUrl = "https://viacep.com.br/ws/" + cep + "/json/";
            RestTemplate restTemplate = new RestTemplate();

            log.info("Calling ViaCEP API: {}", apiUrl);
            log.info("Response: {}", restTemplate.getForObject(apiUrl, String.class));

            return restTemplate.getForObject(apiUrl, CepDto.class);
        } catch (Exception e) {
            throw new RuntimeException("Error fetching address from ViaCEP API: " + e.getMessage());
        }
    }


}
