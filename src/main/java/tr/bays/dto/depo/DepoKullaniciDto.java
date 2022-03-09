package tr.bays.dto.depo;

import java.sql.Time;
import java.util.Date;

import lombok.Data;
import lombok.EqualsAndHashCode;
import tr.bays.dto.yalin.depo.DepoKullaniciYalinDto;
import tr.bays.dto.yalin.teskilat.KullaniciYalinDto;
import tr.bays.dto.yalin.depo.DepoYalinDto;

@SuppressWarnings("serial")
@Data
@EqualsAndHashCode(callSuper = false)
public class DepoKullaniciDto extends DepoKullaniciYalinDto {
	private KullaniciYalinDto kullanici_id;
	private DepoYalinDto depo_id;
}

