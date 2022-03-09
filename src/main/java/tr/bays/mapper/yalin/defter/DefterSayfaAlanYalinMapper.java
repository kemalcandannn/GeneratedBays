package tr.bays.mapper.yalin.defter;

import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;

import tr.bays.dto.yalin.defter.DefterSayfaAlanYalinDto;
import tr.bays.entity.defter.DefterSayfaAlan;
import tr.bays.mapper.DtoMapper;

@Mapper(componentModel = "spring", injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public interface DefterSayfaAlanYalinMapper extends DtoMapper<DefterSayfaAlanYalinDto, DefterSayfaAlan> {
}

