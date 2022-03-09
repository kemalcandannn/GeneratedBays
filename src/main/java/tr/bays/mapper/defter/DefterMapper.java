package tr.bays.mapper.defter;

import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;

import tr.bays.dto.defter.DefterDto;
import tr.bays.entity.defter.Defter;
import tr.bays.mapper.DtoMapper;

@Mapper(componentModel = "spring", injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public interface DefterMapper extends DtoMapper<DefterDto, Defter> {
}

