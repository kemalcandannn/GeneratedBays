package tr.bays.mapper.teskilat;

import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;

import tr.bays.dto.teskilat.KullaniciDto;
import tr.bays.entity.teskilat.Kullanici;
import tr.bays.mapper.DtoMapper;

@Mapper(componentModel = "spring", injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public interface KullaniciMapper extends DtoMapper<KullaniciDto, Kullanici> {
}

