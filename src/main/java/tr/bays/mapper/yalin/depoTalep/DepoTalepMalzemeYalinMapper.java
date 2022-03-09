package tr.bays.mapper.yalin.depoTalep;

import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;

import tr.bays.dto.yalin.depoTalep.DepoTalepMalzemeYalinDto;
import tr.bays.entity.depoTalep.DepoTalepMalzeme;
import tr.bays.mapper.DtoMapper;

@Mapper(componentModel = "spring", injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public interface DepoTalepMalzemeYalinMapper extends DtoMapper<DepoTalepMalzemeYalinDto, DepoTalepMalzeme> {
}

