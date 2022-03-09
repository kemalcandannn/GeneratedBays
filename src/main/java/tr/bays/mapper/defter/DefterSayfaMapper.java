package tr.bays.mapper.defter;

import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;

import tr.bays.dto.defter.DefterSayfaDto;
import tr.bays.entity.defter.DefterSayfa;
import tr.bays.mapper.DtoMapper;

@Mapper(componentModel = "spring", injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public interface DefterSayfaMapper extends DtoMapper<DefterSayfaDto, DefterSayfa> {
}

