package tr.bays.mapper.arsivSahibi;

import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;

import tr.bays.dto.arsivSahibi.PersonelHesapBilgileriDto;
import tr.bays.entity.arsivSahibi.PersonelHesapBilgileri;
import tr.bays.mapper.DtoMapper;

@Mapper(componentModel = "spring", injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public interface PersonelHesapBilgileriMapper extends DtoMapper<PersonelHesapBilgileriDto, PersonelHesapBilgileri> {
}

