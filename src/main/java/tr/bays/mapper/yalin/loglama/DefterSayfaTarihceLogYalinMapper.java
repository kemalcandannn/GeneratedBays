package tr.bays.mapper.yalin.loglama;

import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;

import tr.bays.dto.yalin.loglama.DefterSayfaTarihceLogYalinDto;
import tr.bays.entity.loglama.DefterSayfaTarihceLog;
import tr.bays.mapper.DtoMapper;

@Mapper(componentModel = "spring", injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public interface DefterSayfaTarihceLogYalinMapper extends DtoMapper<DefterSayfaTarihceLogYalinDto, DefterSayfaTarihceLog> {
}

