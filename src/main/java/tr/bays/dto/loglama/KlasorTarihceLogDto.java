package tr.bays.dto.loglama;

import java.sql.Time;
import java.util.Date;

import lombok.Data;
import lombok.EqualsAndHashCode;
import tr.bays.dto.yalin.loglama.KlasorTarihceLogYalinDto;
import tr.bays.dto.yalin.teskilat.KullaniciYalinDto;
import tr.bays.dto.yalin.teskilat.KullaniciYalinDto;
import tr.bays.dto.yalin.klasor.KlasorYalinDto;

@SuppressWarnings("serial")
@Data
@EqualsAndHashCode(callSuper = false)
public class KlasorTarihceLogDto extends KlasorTarihceLogYalinDto {
	private String ip;
	private int durum;
	private String veri;
	private KullaniciYalinDto kullanici_id;
	private KullaniciYalinDto teslim_alan_depo_sorumlusu_id;
	private KlasorYalinDto klasor_id;
}

