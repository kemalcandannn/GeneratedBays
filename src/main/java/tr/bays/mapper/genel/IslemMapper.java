package tr.bays.mapper.genel;

import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;

import tr.bays.dto.genel.IslemDto;
import tr.bays.entity.genel.Islem;
import tr.bays.mapper.DtoMapper;

@Mapper(componentModel = "spring", injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public interface IslemMapper extends DtoMapper<IslemDto, Islem> {
}

