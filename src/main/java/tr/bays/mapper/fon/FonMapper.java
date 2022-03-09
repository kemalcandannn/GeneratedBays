package tr.bays.mapper.fon;

import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;

import tr.bays.dto.fon.FonDto;
import tr.bays.entity.fon.Fon;
import tr.bays.mapper.DtoMapper;

@Mapper(componentModel = "spring", injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public interface FonMapper extends DtoMapper<FonDto, Fon> {
}

