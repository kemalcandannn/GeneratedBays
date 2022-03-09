package tr.bays.mapper.arsivMateryali;

import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;

import tr.bays.dto.arsivMateryali.ArsivMateryaliDto;
import tr.bays.entity.arsivMateryali.ArsivMateryali;
import tr.bays.mapper.DtoMapper;

@Mapper(componentModel = "spring", injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public interface ArsivMateryaliMapper extends DtoMapper<ArsivMateryaliDto, ArsivMateryali> {
}

