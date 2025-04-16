package io.github.com.claudevan.testcontainers.services;

import io.github.com.claudevan.testcontainers.domain.CepDto;
import io.github.com.claudevan.testcontainers.domain.entity.CepEntity;
import io.github.com.claudevan.testcontainers.domain.repository.CepRepository;
import io.github.com.claudevan.testcontainers.mappers.CepMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@Slf4j
public class CepService {

    private final CepRepository cepRepository;
    private final CepMapper cepMapper;

    public CepService(CepRepository cepRepository,
                      CepMapper cepMapper) {
        this.cepRepository = cepRepository;
        this.cepMapper = cepMapper;
    }

    public CepDto getAddress(String cep) {
        CepDto data = new CepDto();

        //buscar no redis

        //buscar na base
        var cepEntity = cepRepository.findByCep(cep);

        //buscar na API
        if (cepEntity == null) {
            data = getAddressFromApi(cep);

            //Esquentar a base
            
            

            cepRepository.save(new CepEntity(null,
                    data.getCep().replace("-", ""),
                    data.getLogradouro(),
                    data.getBairro(),
                    data.getLocalidade(),
                    data.getUf(),
                    data.getEstado(),
                    data.getRegiao(),
                    data.getDdd()));


            //Esquentar redis
        }




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
