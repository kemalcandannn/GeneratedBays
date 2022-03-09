package tr.bays.mapper.yalin.depo;

import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;

import tr.bays.dto.yalin.depo.DepoAlanYalinDto;
import tr.bays.entity.depo.DepoAlan;
import tr.bays.mapper.DtoMapper;

@Mapper(componentModel = "spring", injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public interface DepoAlanYalinMapper extends DtoMapper<DepoAlanYalinDto, DepoAlan> {
}

