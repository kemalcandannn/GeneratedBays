package tr.bays.dto.depoMuhafaza;

import java.sql.Time;
import java.util.Date;

import lombok.Data;
import lombok.EqualsAndHashCode;
import tr.bays.dto.yalin.depoMuhafaza.TopluTasimaYalinDto;
import tr.bays.dto.yalin.teskilat.KullaniciYalinDto;

@SuppressWarnings("serial")
@Data
@EqualsAndHashCode(callSuper = false)
public class TopluTasimaDto extends TopluTasimaYalinDto {
	private String ip;
	private KullaniciYalinDto kullanici_id;
}

