package tr.bays.mapper.yalin.gomlek;

import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;

import tr.bays.dto.yalin.gomlek.GomlekYalinDto;
import tr.bays.entity.gomlek.Gomlek;
import tr.bays.mapper.DtoMapper;

@Mapper(componentModel = "spring", injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public interface GomlekYalinMapper extends DtoMapper<GomlekYalinDto, Gomlek> {
}

