package tr.bays.mapper.yalin.sozluk;

import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;

import tr.bays.dto.yalin.sozluk.SozlukMaddeYalinDto;
import tr.bays.entity.sozluk.SozlukMadde;
import tr.bays.mapper.DtoMapper;

@Mapper(componentModel = "spring", injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public interface SozlukMaddeYalinMapper extends DtoMapper<SozlukMaddeYalinDto, SozlukMadde> {
}

