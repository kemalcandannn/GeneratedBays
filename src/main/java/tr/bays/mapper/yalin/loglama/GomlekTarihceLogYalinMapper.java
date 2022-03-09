package tr.bays.mapper.yalin.loglama;

import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;

import tr.bays.dto.yalin.loglama.GomlekTarihceLogYalinDto;
import tr.bays.entity.loglama.GomlekTarihceLog;
import tr.bays.mapper.DtoMapper;

@Mapper(componentModel = "spring", injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public interface GomlekTarihceLogYalinMapper extends DtoMapper<GomlekTarihceLogYalinDto, GomlekTarihceLog> {
}

