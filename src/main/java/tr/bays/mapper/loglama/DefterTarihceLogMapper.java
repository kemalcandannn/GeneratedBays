package tr.bays.mapper.loglama;

import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;

import tr.bays.dto.loglama.DefterTarihceLogDto;
import tr.bays.entity.loglama.DefterTarihceLog;
import tr.bays.mapper.DtoMapper;

@Mapper(componentModel = "spring", injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public interface DefterTarihceLogMapper extends DtoMapper<DefterTarihceLogDto, DefterTarihceLog> {
}

