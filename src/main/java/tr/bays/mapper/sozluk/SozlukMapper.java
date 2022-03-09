package tr.bays.mapper.sozluk;

import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;

import tr.bays.dto.sozluk.SozlukDto;
import tr.bays.entity.sozluk.Sozluk;
import tr.bays.mapper.DtoMapper;

@Mapper(componentModel = "spring", injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public interface SozlukMapper extends DtoMapper<SozlukDto, Sozluk> {
}

