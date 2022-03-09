package tr.bays.dto.loglama;

import java.sql.Time;
import java.util.Date;

import lombok.Data;
import lombok.EqualsAndHashCode;
import tr.bays.dto.yalin.loglama.DefterSayfaTarihceLogYalinDto;
import tr.bays.dto.yalin.teskilat.KullaniciYalinDto;
import tr.bays.dto.yalin.defter.DefterSayfaYalinDto;

@SuppressWarnings("serial")
@Data
@EqualsAndHashCode(callSuper = false)
public class DefterSayfaTarihceLogDto extends DefterSayfaTarihceLogYalinDto {
	private String ip;
	private String veri;
	private int durum;
	private KullaniciYalinDto kullanici_id;
	private DefterSayfaYalinDto defter_sayfa_id;
}

