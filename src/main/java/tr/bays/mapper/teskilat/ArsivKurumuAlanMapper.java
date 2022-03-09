package tr.bays.mapper.teskilat;

import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;

import tr.bays.dto.teskilat.ArsivKurumuAlanDto;
import tr.bays.entity.teskilat.ArsivKurumuAlan;
import tr.bays.mapper.DtoMapper;

@Mapper(componentModel = "spring", injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public interface ArsivKurumuAlanMapper extends DtoMapper<ArsivKurumuAlanDto, ArsivKurumuAlan> {
}

