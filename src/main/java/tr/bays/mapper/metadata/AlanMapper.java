package tr.bays.mapper.metadata;

import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;

import tr.bays.dto.metadata.AlanDto;
import tr.bays.entity.metadata.Alan;
import tr.bays.mapper.DtoMapper;

@Mapper(componentModel = "spring", injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public interface AlanMapper extends DtoMapper<AlanDto, Alan> {
}

