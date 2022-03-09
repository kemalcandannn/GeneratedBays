package tr.bays.mapper.yalin.gomlek;

import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;

import tr.bays.dto.yalin.gomlek.GomlekEnvanterYalinDto;
import tr.bays.entity.gomlek.GomlekEnvanter;
import tr.bays.mapper.DtoMapper;

@Mapper(componentModel = "spring", injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public interface GomlekEnvanterYalinMapper extends DtoMapper<GomlekEnvanterYalinDto, GomlekEnvanter> {
}

