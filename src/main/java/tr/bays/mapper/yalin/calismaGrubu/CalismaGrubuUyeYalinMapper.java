package tr.bays.mapper.yalin.calismaGrubu;

import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;

import tr.bays.dto.yalin.calismaGrubu.CalismaGrubuUyeYalinDto;
import tr.bays.entity.calismaGrubu.CalismaGrubuUye;
import tr.bays.mapper.DtoMapper;

@Mapper(componentModel = "spring", injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public interface CalismaGrubuUyeYalinMapper extends DtoMapper<CalismaGrubuUyeYalinDto, CalismaGrubuUye> {
}

