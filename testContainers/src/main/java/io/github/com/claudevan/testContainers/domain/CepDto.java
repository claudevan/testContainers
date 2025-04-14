package io.github.com.claudevan.testcontainers.domain;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Data
public class CepDto {

    public String cep;
    public String logradouro;
    public String bairro;
    public String localidade;
    public String uf;
    public String estado;
    public String regiao;
    public String ddd;
}