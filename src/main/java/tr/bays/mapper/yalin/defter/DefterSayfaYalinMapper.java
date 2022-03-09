package tr.bays.mapper.yalin.defter;

import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;

import tr.bays.dto.yalin.defter.DefterSayfaYalinDto;
import tr.bays.entity.defter.DefterSayfa;
import tr.bays.mapper.DtoMapper;

@Mapper(componentModel = "spring", injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public interface DefterSayfaYalinMapper extends DtoMapper<DefterSayfaYalinDto, DefterSayfa> {
}

