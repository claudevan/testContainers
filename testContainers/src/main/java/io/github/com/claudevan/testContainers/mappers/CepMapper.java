package io.github.com.claudevan.testcontainers.mappers;

import io.github.com.claudevan.testcontainers.domain.CepDto;
import io.github.com.claudevan.testcontainers.domain.entity.CepEntity;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING,
        injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public interface CepMapper {

    // Mapeia a entidade para o DTO
    CepDto toCepDTO(CepEntity entity);

    // Mapeia o DTO para a entidade
    CepEntity toCepEntity(CepDto dto);
}
