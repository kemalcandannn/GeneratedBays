package tr.bays.mapper.yalin.arsivMateryali;

import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;

import tr.bays.dto.yalin.arsivMateryali.ArsivMateryaliYalinDto;
import tr.bays.entity.arsivMateryali.ArsivMateryali;
import tr.bays.mapper.DtoMapper;

@Mapper(componentModel = "spring", injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public interface ArsivMateryaliYalinMapper extends DtoMapper<ArsivMateryaliYalinDto, ArsivMateryali> {
}

