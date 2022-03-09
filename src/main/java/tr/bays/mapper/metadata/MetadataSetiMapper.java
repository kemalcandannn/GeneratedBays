package tr.bays.mapper.metadata;

import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;

import tr.bays.dto.metadata.MetadataSetiDto;
import tr.bays.entity.metadata.MetadataSeti;
import tr.bays.mapper.DtoMapper;

@Mapper(componentModel = "spring", injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public interface MetadataSetiMapper extends DtoMapper<MetadataSetiDto, MetadataSeti> {
}

