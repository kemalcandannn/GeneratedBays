package tr.bays.mapper.gomlek;

import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;

import tr.bays.dto.gomlek.GomlekDto;
import tr.bays.entity.gomlek.Gomlek;
import tr.bays.mapper.DtoMapper;

@Mapper(componentModel = "spring", injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public interface GomlekMapper extends DtoMapper<GomlekDto, Gomlek> {
}

