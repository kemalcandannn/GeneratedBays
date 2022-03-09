package tr.bays.mapper.yalin.arsivSahibi;

import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;

import tr.bays.dto.yalin.arsivSahibi.ArsivSahibiPersonelYalinDto;
import tr.bays.entity.arsivSahibi.ArsivSahibiPersonel;
import tr.bays.mapper.DtoMapper;

@Mapper(componentModel = "spring", injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public interface ArsivSahibiPersonelYalinMapper extends DtoMapper<ArsivSahibiPersonelYalinDto, ArsivSahibiPersonel> {
}

