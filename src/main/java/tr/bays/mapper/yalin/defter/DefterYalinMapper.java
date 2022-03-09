package tr.bays.mapper.yalin.defter;

import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;

import tr.bays.dto.yalin.defter.DefterYalinDto;
import tr.bays.entity.defter.Defter;
import tr.bays.mapper.DtoMapper;

@Mapper(componentModel = "spring", injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public interface DefterYalinMapper extends DtoMapper<DefterYalinDto, Defter> {
}

