package tr.bays.mapper.yalin.arsivSahibi;

import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;

import tr.bays.dto.yalin.arsivSahibi.ArsivSahibiYalinDto;
import tr.bays.entity.arsivSahibi.ArsivSahibi;
import tr.bays.mapper.DtoMapper;

@Mapper(componentModel = "spring", injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public interface ArsivSahibiYalinMapper extends DtoMapper<ArsivSahibiYalinDto, ArsivSahibi> {
}

