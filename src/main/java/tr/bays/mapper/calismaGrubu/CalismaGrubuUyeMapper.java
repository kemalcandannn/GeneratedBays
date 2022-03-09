package tr.bays.mapper.calismaGrubu;

import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;

import tr.bays.dto.calismaGrubu.CalismaGrubuUyeDto;
import tr.bays.entity.calismaGrubu.CalismaGrubuUye;
import tr.bays.mapper.DtoMapper;

@Mapper(componentModel = "spring", injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public interface CalismaGrubuUyeMapper extends DtoMapper<CalismaGrubuUyeDto, CalismaGrubuUye> {
}

