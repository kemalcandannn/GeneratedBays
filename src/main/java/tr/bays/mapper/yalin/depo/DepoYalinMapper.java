package tr.bays.mapper.yalin.depo;

import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;

import tr.bays.dto.yalin.depo.DepoYalinDto;
import tr.bays.entity.depo.Depo;
import tr.bays.mapper.DtoMapper;

@Mapper(componentModel = "spring", injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public interface DepoYalinMapper extends DtoMapper<DepoYalinDto, Depo> {
}

