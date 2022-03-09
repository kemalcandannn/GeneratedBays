package tr.bays.mapper.loglama;

import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;

import tr.bays.dto.loglama.DefterSayfaTarihceLogDto;
import tr.bays.entity.loglama.DefterSayfaTarihceLog;
import tr.bays.mapper.DtoMapper;

@Mapper(componentModel = "spring", injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public interface DefterSayfaTarihceLogMapper extends DtoMapper<DefterSayfaTarihceLogDto, DefterSayfaTarihceLog> {
}

