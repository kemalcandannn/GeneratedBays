package tr.bays.dto.depoTalep;

import java.sql.Time;
import java.util.Date;

import lombok.Data;
import lombok.EqualsAndHashCode;
import tr.bays.dto.yalin.depoTalep.DepoTalepTarihceLogYalinDto;
import tr.bays.dto.yalin.sozluk.SozlukMaddeYalinDto;
import tr.bays.dto.yalin.teskilat.KullaniciYalinDto;
import tr.bays.dto.yalin.teskilat.KullaniciYalinDto;
import tr.bays.dto.yalin.depoTalep.DepoTalepYalinDto;

@SuppressWarnings("serial")
@Data
@EqualsAndHashCode(callSuper = false)
public class DepoTalepTarihceLogDto extends DepoTalepTarihceLogYalinDto {
	private Date tarih_saat;
	private String ip;
	private String sebep;
	private SozlukMaddeYalinDto iptal_red_sebebi_id;
	private KullaniciYalinDto depo_sorumlusu_id;
	private KullaniciYalinDto birim_sorumlusu_id;
	private DepoTalepYalinDto depo_talep_id;
}

