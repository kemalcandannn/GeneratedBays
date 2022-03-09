package tr.bays.mapper.depo;

import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;

import tr.bays.dto.depo.DepoAlanDto;
import tr.bays.entity.depo.DepoAlan;
import tr.bays.mapper.DtoMapper;

@Mapper(componentModel = "spring", injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public interface DepoAlanMapper extends DtoMapper<DepoAlanDto, DepoAlan> {
}

