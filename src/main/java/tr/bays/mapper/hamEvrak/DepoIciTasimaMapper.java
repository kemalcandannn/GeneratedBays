package tr.bays.mapper.hamEvrak;

import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;

import tr.bays.dto.hamEvrak.DepoIciTasimaDto;
import tr.bays.entity.hamEvrak.DepoIciTasima;
import tr.bays.mapper.DtoMapper;

@Mapper(componentModel = "spring", injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public interface DepoIciTasimaMapper extends DtoMapper<DepoIciTasimaDto, DepoIciTasima> {
}

