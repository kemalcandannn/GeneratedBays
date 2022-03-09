package tr.bays.mapper.yalin.loglama;

import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;

import tr.bays.dto.yalin.loglama.DefterTarihceLogYalinDto;
import tr.bays.entity.loglama.DefterTarihceLog;
import tr.bays.mapper.DtoMapper;

@Mapper(componentModel = "spring", injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public interface DefterTarihceLogYalinMapper extends DtoMapper<DefterTarihceLogYalinDto, DefterTarihceLog> {
}

