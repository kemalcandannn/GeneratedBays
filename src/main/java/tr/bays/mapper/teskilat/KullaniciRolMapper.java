package tr.bays.mapper.teskilat;

import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;

import tr.bays.dto.teskilat.KullaniciRolDto;
import tr.bays.entity.teskilat.KullaniciRol;
import tr.bays.mapper.DtoMapper;

@Mapper(componentModel = "spring", injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public interface KullaniciRolMapper extends DtoMapper<KullaniciRolDto, KullaniciRol> {
}

