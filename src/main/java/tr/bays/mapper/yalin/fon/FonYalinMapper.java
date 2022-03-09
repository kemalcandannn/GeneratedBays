package tr.bays.mapper.yalin.fon;

import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;

import tr.bays.dto.yalin.fon.FonYalinDto;
import tr.bays.entity.fon.Fon;
import tr.bays.mapper.DtoMapper;

@Mapper(componentModel = "spring", injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public interface FonYalinMapper extends DtoMapper<FonYalinDto, Fon> {
}

