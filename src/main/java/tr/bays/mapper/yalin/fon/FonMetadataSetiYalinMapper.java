package tr.bays.mapper.yalin.fon;

import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;

import tr.bays.dto.yalin.fon.FonMetadataSetiYalinDto;
import tr.bays.entity.fon.FonMetadataSeti;
import tr.bays.mapper.DtoMapper;

@Mapper(componentModel = "spring", injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public interface FonMetadataSetiYalinMapper extends DtoMapper<FonMetadataSetiYalinDto, FonMetadataSeti> {
}

