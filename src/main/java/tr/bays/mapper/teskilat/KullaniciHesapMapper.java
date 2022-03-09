package tr.bays.mapper.teskilat;

import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;

import tr.bays.dto.teskilat.KullaniciHesapDto;
import tr.bays.entity.teskilat.KullaniciHesap;
import tr.bays.mapper.DtoMapper;

@Mapper(componentModel = "spring", injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public interface KullaniciHesapMapper extends DtoMapper<KullaniciHesapDto, KullaniciHesap> {
}

