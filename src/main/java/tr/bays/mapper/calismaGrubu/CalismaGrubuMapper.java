package tr.bays.mapper.calismaGrubu;

import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;

import tr.bays.dto.calismaGrubu.CalismaGrubuDto;
import tr.bays.entity.calismaGrubu.CalismaGrubu;
import tr.bays.mapper.DtoMapper;

@Mapper(componentModel = "spring", injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public interface CalismaGrubuMapper extends DtoMapper<CalismaGrubuDto, CalismaGrubu> {
}

