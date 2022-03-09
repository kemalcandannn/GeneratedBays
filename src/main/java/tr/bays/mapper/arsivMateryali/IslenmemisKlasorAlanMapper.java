package tr.bays.mapper.arsivMateryali;

import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;

import tr.bays.dto.arsivMateryali.IslenmemisKlasorAlanDto;
import tr.bays.entity.arsivMateryali.IslenmemisKlasorAlan;
import tr.bays.mapper.DtoMapper;

@Mapper(componentModel = "spring", injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public interface IslenmemisKlasorAlanMapper extends DtoMapper<IslenmemisKlasorAlanDto, IslenmemisKlasorAlan> {
}

