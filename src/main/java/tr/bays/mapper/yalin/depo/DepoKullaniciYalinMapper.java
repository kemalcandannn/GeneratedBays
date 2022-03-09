package tr.bays.mapper.yalin.depo;

import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;

import tr.bays.dto.yalin.depo.DepoKullaniciYalinDto;
import tr.bays.entity.depo.DepoKullanici;
import tr.bays.mapper.DtoMapper;

@Mapper(componentModel = "spring", injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public interface DepoKullaniciYalinMapper extends DtoMapper<DepoKullaniciYalinDto, DepoKullanici> {
}

