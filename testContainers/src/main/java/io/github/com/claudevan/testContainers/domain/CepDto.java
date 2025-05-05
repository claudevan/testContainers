package io.github.com.claudevan.testcontainers.domain;

import io.github.com.claudevan.testcontainers.domain.entity.CepEntity;
import lombok.*;

@Getter
@Setter
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CepDto {

    public String cep;
    public String logradouro;
    public String bairro;
    public String localidade;
    public String uf;
    public String estado;
    public String regiao;
    public String ddd;

    public static CepDto Builder(CepEntity cepEntity) {
        var cep = new CepDto();
        cep.setCep(cepEntity.getCep().replace("-", ""));
        cep.setLogradouro(cepEntity.getLogradouro());
        cep.setBairro(cepEntity.getBairro());
        cep.setLocalidade(cepEntity.getLocalidade());
        cep.setUf(cepEntity.getUf());
        cep.setEstado(cepEntity.getEstado());
        cep.setRegiao(cepEntity.getRegiao());
        cep.setDdd(cepEntity.getDdd());
        return cep;
    };
}