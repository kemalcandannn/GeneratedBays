package tr.bays.mapper.yalin.metadata;

import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;

import tr.bays.dto.yalin.metadata.MetadataSetiYalinDto;
import tr.bays.entity.metadata.MetadataSeti;
import tr.bays.mapper.DtoMapper;

@Mapper(componentModel = "spring", injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public interface MetadataSetiYalinMapper extends DtoMapper<MetadataSetiYalinDto, MetadataSeti> {
}

