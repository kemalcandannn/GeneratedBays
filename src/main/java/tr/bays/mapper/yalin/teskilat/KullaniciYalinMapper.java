package tr.bays.mapper.yalin.teskilat;

import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;

import tr.bays.dto.yalin.teskilat.KullaniciYalinDto;
import tr.bays.entity.teskilat.Kullanici;
import tr.bays.mapper.DtoMapper;

@Mapper(componentModel = "spring", injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public interface KullaniciYalinMapper extends DtoMapper<KullaniciYalinDto, Kullanici> {
}

