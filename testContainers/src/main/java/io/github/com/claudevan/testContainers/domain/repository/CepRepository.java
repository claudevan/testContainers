package io.github.com.claudevan.testcontainers.domain.repository;

import io.github.com.claudevan.testcontainers.domain.entity.CepEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CepRepository extends JpaRepository<CepEntity, Long> {

    CepEntity findByCep(String cep);
}
