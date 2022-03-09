package tr.bays.mapper.fon;

import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;

import tr.bays.dto.fon.FonOnculVeriLogDto;
import tr.bays.entity.fon.FonOnculVeriLog;
import tr.bays.mapper.DtoMapper;

@Mapper(componentModel = "spring", injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public interface FonOnculVeriLogMapper extends DtoMapper<FonOnculVeriLogDto, FonOnculVeriLog> {
}

