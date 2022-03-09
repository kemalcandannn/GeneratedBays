package tr.bays.mapper.yalin.teskilat;

import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;

import tr.bays.dto.yalin.teskilat.KullaniciRolYalinDto;
import tr.bays.entity.teskilat.KullaniciRol;
import tr.bays.mapper.DtoMapper;

@Mapper(componentModel = "spring", injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public interface KullaniciRolYalinMapper extends DtoMapper<KullaniciRolYalinDto, KullaniciRol> {
}

