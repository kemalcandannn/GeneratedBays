package tr.bays.mapper.sozluk;

import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;

import tr.bays.dto.sozluk.SozlukMaddeDto;
import tr.bays.entity.sozluk.SozlukMadde;
import tr.bays.mapper.DtoMapper;

@Mapper(componentModel = "spring", injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public interface SozlukMaddeMapper extends DtoMapper<SozlukMaddeDto, SozlukMadde> {
}

