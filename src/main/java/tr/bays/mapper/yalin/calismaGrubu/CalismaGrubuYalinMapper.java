package tr.bays.mapper.yalin.calismaGrubu;

import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;

import tr.bays.dto.yalin.calismaGrubu.CalismaGrubuYalinDto;
import tr.bays.entity.calismaGrubu.CalismaGrubu;
import tr.bays.mapper.DtoMapper;

@Mapper(componentModel = "spring", injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public interface CalismaGrubuYalinMapper extends DtoMapper<CalismaGrubuYalinDto, CalismaGrubu> {
}

