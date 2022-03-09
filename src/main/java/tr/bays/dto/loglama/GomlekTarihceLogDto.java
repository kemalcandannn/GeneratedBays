package tr.bays.dto.loglama;

import java.sql.Time;
import java.util.Date;

import lombok.Data;
import lombok.EqualsAndHashCode;
import tr.bays.dto.yalin.loglama.GomlekTarihceLogYalinDto;
import tr.bays.dto.yalin.teskilat.KullaniciYalinDto;
import tr.bays.dto.yalin.gomlek.GomlekYalinDto;

@SuppressWarnings("serial")
@Data
@EqualsAndHashCode(callSuper = false)
public class GomlekTarihceLogDto extends GomlekTarihceLogYalinDto {
	private String ip;
	private String veri;
	private int durum;
	private KullaniciYalinDto kullanici_id;
	private GomlekYalinDto gomlek_id;
}

