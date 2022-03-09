package tr.bays.mapper.arsivMateryali;

import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;

import tr.bays.dto.arsivMateryali.ArsivMateryaliAlanDto;
import tr.bays.entity.arsivMateryali.ArsivMateryaliAlan;
import tr.bays.mapper.DtoMapper;

@Mapper(componentModel = "spring", injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public interface ArsivMateryaliAlanMapper extends DtoMapper<ArsivMateryaliAlanDto, ArsivMateryaliAlan> {
}

