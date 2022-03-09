package tr.bays.mapper.arsivSahibi;

import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;

import tr.bays.dto.arsivSahibi.ArsivSahibiDto;
import tr.bays.entity.arsivSahibi.ArsivSahibi;
import tr.bays.mapper.DtoMapper;

@Mapper(componentModel = "spring", injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public interface ArsivSahibiMapper extends DtoMapper<ArsivSahibiDto, ArsivSahibi> {
}

