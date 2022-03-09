package tr.bays.mapper.yalin.sozluk;

import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;

import tr.bays.dto.yalin.sozluk.SozlukYalinDto;
import tr.bays.entity.sozluk.Sozluk;
import tr.bays.mapper.DtoMapper;

@Mapper(componentModel = "spring", injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public interface SozlukYalinMapper extends DtoMapper<SozlukYalinDto, Sozluk> {
}

