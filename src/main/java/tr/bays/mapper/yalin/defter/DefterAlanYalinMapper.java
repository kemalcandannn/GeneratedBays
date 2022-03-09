package tr.bays.mapper.yalin.defter;

import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;

import tr.bays.dto.yalin.defter.DefterAlanYalinDto;
import tr.bays.entity.defter.DefterAlan;
import tr.bays.mapper.DtoMapper;

@Mapper(componentModel = "spring", injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public interface DefterAlanYalinMapper extends DtoMapper<DefterAlanYalinDto, DefterAlan> {
}

