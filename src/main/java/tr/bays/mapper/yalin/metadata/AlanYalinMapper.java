package tr.bays.mapper.yalin.metadata;

import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;

import tr.bays.dto.yalin.metadata.AlanYalinDto;
import tr.bays.entity.metadata.Alan;
import tr.bays.mapper.DtoMapper;

@Mapper(componentModel = "spring", injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public interface AlanYalinMapper extends DtoMapper<AlanYalinDto, Alan> {
}

