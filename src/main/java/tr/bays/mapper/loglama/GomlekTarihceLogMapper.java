package tr.bays.mapper.loglama;

import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;

import tr.bays.dto.loglama.GomlekTarihceLogDto;
import tr.bays.entity.loglama.GomlekTarihceLog;
import tr.bays.mapper.DtoMapper;

@Mapper(componentModel = "spring", injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public interface GomlekTarihceLogMapper extends DtoMapper<GomlekTarihceLogDto, GomlekTarihceLog> {
}

