package tr.bays.mapper.yalin.hamEvrak;

import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;

import tr.bays.dto.yalin.hamEvrak.DepoIciTasimaYalinDto;
import tr.bays.entity.hamEvrak.DepoIciTasima;
import tr.bays.mapper.DtoMapper;

@Mapper(componentModel = "spring", injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public interface DepoIciTasimaYalinMapper extends DtoMapper<DepoIciTasimaYalinDto, DepoIciTasima> {
}

