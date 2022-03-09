package tr.bays.mapper.yalin.fon;

import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;

import tr.bays.dto.yalin.fon.FonOnculVeriLogYalinDto;
import tr.bays.entity.fon.FonOnculVeriLog;
import tr.bays.mapper.DtoMapper;

@Mapper(componentModel = "spring", injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public interface FonOnculVeriLogYalinMapper extends DtoMapper<FonOnculVeriLogYalinDto, FonOnculVeriLog> {
}

