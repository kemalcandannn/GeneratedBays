package tr.bays.mapper.sistem;

import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;

import tr.bays.dto.sistem.DbLogDto;
import tr.bays.entity.sistem.DbLog;
import tr.bays.mapper.DtoMapper;

@Mapper(componentModel = "spring", injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public interface DbLogMapper extends DtoMapper<DbLogDto, DbLog> {
}

