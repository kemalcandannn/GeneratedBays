package tr.bays.mapper.arsivMateryali;

import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;

import tr.bays.dto.arsivMateryali.IslenmemisKlasorDto;
import tr.bays.entity.arsivMateryali.IslenmemisKlasor;
import tr.bays.mapper.DtoMapper;

@Mapper(componentModel = "spring", injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public interface IslenmemisKlasorMapper extends DtoMapper<IslenmemisKlasorDto, IslenmemisKlasor> {
}

