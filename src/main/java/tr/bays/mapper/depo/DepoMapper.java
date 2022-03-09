package tr.bays.mapper.depo;

import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;

import tr.bays.dto.depo.DepoDto;
import tr.bays.entity.depo.Depo;
import tr.bays.mapper.DtoMapper;

@Mapper(componentModel = "spring", injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public interface DepoMapper extends DtoMapper<DepoDto, Depo> {
}

