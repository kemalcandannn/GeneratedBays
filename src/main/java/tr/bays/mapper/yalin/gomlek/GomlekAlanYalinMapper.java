package tr.bays.mapper.yalin.gomlek;

import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;

import tr.bays.dto.yalin.gomlek.GomlekAlanYalinDto;
import tr.bays.entity.gomlek.GomlekAlan;
import tr.bays.mapper.DtoMapper;

@Mapper(componentModel = "spring", injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public interface GomlekAlanYalinMapper extends DtoMapper<GomlekAlanYalinDto, GomlekAlan> {
}

