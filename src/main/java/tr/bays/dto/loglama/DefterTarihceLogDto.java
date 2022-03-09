package tr.bays.dto.loglama;

import java.sql.Time;
import java.util.Date;

import lombok.Data;
import lombok.EqualsAndHashCode;
import tr.bays.dto.yalin.loglama.DefterTarihceLogYalinDto;
import tr.bays.dto.yalin.teskilat.KullaniciYalinDto;
import tr.bays.dto.yalin.defter.DefterYalinDto;

@SuppressWarnings("serial")
@Data
@EqualsAndHashCode(callSuper = false)
public class DefterTarihceLogDto extends DefterTarihceLogYalinDto {
	private String ip;
	private String veri;
	private int durum;
	private KullaniciYalinDto kullanici_id;
	private DefterYalinDto defter_id;
}

