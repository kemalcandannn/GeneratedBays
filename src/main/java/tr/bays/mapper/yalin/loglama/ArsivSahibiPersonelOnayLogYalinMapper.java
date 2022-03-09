package tr.bays.mapper.yalin.loglama;

import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;

import tr.bays.dto.yalin.loglama.ArsivSahibiPersonelOnayLogYalinDto;
import tr.bays.entity.loglama.ArsivSahibiPersonelOnayLog;
import tr.bays.mapper.DtoMapper;

@Mapper(componentModel = "spring", injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public interface ArsivSahibiPersonelOnayLogYalinMapper extends DtoMapper<ArsivSahibiPersonelOnayLogYalinDto, ArsivSahibiPersonelOnayLog> {
}

