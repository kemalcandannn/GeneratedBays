package tr.bays.dto.depoTalep;

import java.sql.Time;
import java.util.Date;

import lombok.Data;
import lombok.EqualsAndHashCode;
import tr.bays.dto.yalin.depoTalep.DepoTalepYalinDto;
import tr.bays.dto.yalin.teskilat.BirimYalinDto;
import tr.bays.dto.yalin.teskilat.KullaniciYalinDto;
import tr.bays.dto.yalin.teskilat.KullaniciYalinDto;
import tr.bays.dto.yalin.teskilat.KullaniciYalinDto;
import tr.bays.dto.yalin.depo.DepoYalinDto;

@SuppressWarnings("serial")
@Data
@EqualsAndHashCode(callSuper = false)
public class DepoTalepDto extends DepoTalepYalinDto {
	private int durum;
	private String ip;
	private BirimYalinDto talep_eden_birim_id;
	private KullaniciYalinDto talep_eden_kullanici_id;
	private KullaniciYalinDto teslim_alan_id;
	private KullaniciYalinDto depo_gorevlisi_id;
	private DepoYalinDto depo_id;
}

