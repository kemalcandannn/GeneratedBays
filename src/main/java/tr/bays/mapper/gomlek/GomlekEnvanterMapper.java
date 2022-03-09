package tr.bays.mapper.gomlek;

import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;

import tr.bays.dto.gomlek.GomlekEnvanterDto;
import tr.bays.entity.gomlek.GomlekEnvanter;
import tr.bays.mapper.DtoMapper;

@Mapper(componentModel = "spring", injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public interface GomlekEnvanterMapper extends DtoMapper<GomlekEnvanterDto, GomlekEnvanter> {
}

