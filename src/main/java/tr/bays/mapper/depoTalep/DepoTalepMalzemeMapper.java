package tr.bays.mapper.depoTalep;

import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;

import tr.bays.dto.depoTalep.DepoTalepMalzemeDto;
import tr.bays.entity.depoTalep.DepoTalepMalzeme;
import tr.bays.mapper.DtoMapper;

@Mapper(componentModel = "spring", injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public interface DepoTalepMalzemeMapper extends DtoMapper<DepoTalepMalzemeDto, DepoTalepMalzeme> {
}

