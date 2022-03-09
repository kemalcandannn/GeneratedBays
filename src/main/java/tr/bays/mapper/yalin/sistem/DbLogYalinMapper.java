package tr.bays.mapper.yalin.sistem;

import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;

import tr.bays.dto.yalin.sistem.DbLogYalinDto;
import tr.bays.entity.sistem.DbLog;
import tr.bays.mapper.DtoMapper;

@Mapper(componentModel = "spring", injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public interface DbLogYalinMapper extends DtoMapper<DbLogYalinDto, DbLog> {
}

