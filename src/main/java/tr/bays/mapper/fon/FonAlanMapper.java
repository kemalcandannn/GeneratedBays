package tr.bays.mapper.fon;

import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;

import tr.bays.dto.fon.FonAlanDto;
import tr.bays.entity.fon.FonAlan;
import tr.bays.mapper.DtoMapper;

@Mapper(componentModel = "spring", injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public interface FonAlanMapper extends DtoMapper<FonAlanDto, FonAlan> {
}

