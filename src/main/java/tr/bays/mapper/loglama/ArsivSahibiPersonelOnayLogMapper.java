package tr.bays.mapper.loglama;

import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;

import tr.bays.dto.loglama.ArsivSahibiPersonelOnayLogDto;
import tr.bays.entity.loglama.ArsivSahibiPersonelOnayLog;
import tr.bays.mapper.DtoMapper;

@Mapper(componentModel = "spring", injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public interface ArsivSahibiPersonelOnayLogMapper extends DtoMapper<ArsivSahibiPersonelOnayLogDto, ArsivSahibiPersonelOnayLog> {
}

