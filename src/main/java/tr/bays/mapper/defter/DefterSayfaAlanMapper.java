package tr.bays.mapper.defter;

import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;

import tr.bays.dto.defter.DefterSayfaAlanDto;
import tr.bays.entity.defter.DefterSayfaAlan;
import tr.bays.mapper.DtoMapper;

@Mapper(componentModel = "spring", injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public interface DefterSayfaAlanMapper extends DtoMapper<DefterSayfaAlanDto, DefterSayfaAlan> {
}

