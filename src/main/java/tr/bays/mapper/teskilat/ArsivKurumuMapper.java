package tr.bays.mapper.teskilat;

import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;

import tr.bays.dto.teskilat.ArsivKurumuDto;
import tr.bays.entity.teskilat.ArsivKurumu;
import tr.bays.mapper.DtoMapper;

@Mapper(componentModel = "spring", injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public interface ArsivKurumuMapper extends DtoMapper<ArsivKurumuDto, ArsivKurumu> {
}

