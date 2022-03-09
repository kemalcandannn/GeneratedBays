package tr.bays.mapper.yalin.teskilat;

import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;

import tr.bays.dto.yalin.teskilat.KullaniciHesapYalinDto;
import tr.bays.entity.teskilat.KullaniciHesap;
import tr.bays.mapper.DtoMapper;

@Mapper(componentModel = "spring", injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public interface KullaniciHesapYalinMapper extends DtoMapper<KullaniciHesapYalinDto, KullaniciHesap> {
}

