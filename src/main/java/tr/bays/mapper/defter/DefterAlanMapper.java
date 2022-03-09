package tr.bays.mapper.defter;

import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;

import tr.bays.dto.defter.DefterAlanDto;
import tr.bays.entity.defter.DefterAlan;
import tr.bays.mapper.DtoMapper;

@Mapper(componentModel = "spring", injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public interface DefterAlanMapper extends DtoMapper<DefterAlanDto, DefterAlan> {
}

