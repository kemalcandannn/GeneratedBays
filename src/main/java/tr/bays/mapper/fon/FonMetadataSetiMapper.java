package tr.bays.mapper.fon;

import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;

import tr.bays.dto.fon.FonMetadataSetiDto;
import tr.bays.entity.fon.FonMetadataSeti;
import tr.bays.mapper.DtoMapper;

@Mapper(componentModel = "spring", injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public interface FonMetadataSetiMapper extends DtoMapper<FonMetadataSetiDto, FonMetadataSeti> {
}

