package tr.bays.mapper.yalin.arsivMateryali;

import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;

import tr.bays.dto.yalin.arsivMateryali.ArsivMateryaliAlanYalinDto;
import tr.bays.entity.arsivMateryali.ArsivMateryaliAlan;
import tr.bays.mapper.DtoMapper;

@Mapper(componentModel = "spring", injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public interface ArsivMateryaliAlanYalinMapper extends DtoMapper<ArsivMateryaliAlanYalinDto, ArsivMateryaliAlan> {
}

