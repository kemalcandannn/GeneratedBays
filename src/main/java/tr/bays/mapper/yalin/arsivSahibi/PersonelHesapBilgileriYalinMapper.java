package tr.bays.mapper.yalin.arsivSahibi;

import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;

import tr.bays.dto.yalin.arsivSahibi.PersonelHesapBilgileriYalinDto;
import tr.bays.entity.arsivSahibi.PersonelHesapBilgileri;
import tr.bays.mapper.DtoMapper;

@Mapper(componentModel = "spring", injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public interface PersonelHesapBilgileriYalinMapper extends DtoMapper<PersonelHesapBilgileriYalinDto, PersonelHesapBilgileri> {
}

