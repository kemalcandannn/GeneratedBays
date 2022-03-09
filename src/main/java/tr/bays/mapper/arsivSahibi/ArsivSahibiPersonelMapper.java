package tr.bays.mapper.arsivSahibi;

import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;

import tr.bays.dto.arsivSahibi.ArsivSahibiPersonelDto;
import tr.bays.entity.arsivSahibi.ArsivSahibiPersonel;
import tr.bays.mapper.DtoMapper;

@Mapper(componentModel = "spring", injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public interface ArsivSahibiPersonelMapper extends DtoMapper<ArsivSahibiPersonelDto, ArsivSahibiPersonel> {
}

