package tr.bays.mapper.yalin.fon;

import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;

import tr.bays.dto.yalin.fon.FonAlanYalinDto;
import tr.bays.entity.fon.FonAlan;
import tr.bays.mapper.DtoMapper;

@Mapper(componentModel = "spring", injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public interface FonAlanYalinMapper extends DtoMapper<FonAlanYalinDto, FonAlan> {
}

