package tr.bays.dto.loglama;

import java.sql.Time;
import java.util.Date;

import lombok.Data;
import lombok.EqualsAndHashCode;
import tr.bays.dto.yalin.loglama.ArsivSahibiPersonelOnayLogYalinDto;
import tr.bays.dto.yalin.teskilat.KullaniciYalinDto;
import tr.bays.dto.yalin.arsivSahibi.ArsivSahibiPersonelYalinDto;

@SuppressWarnings("serial")
@Data
@EqualsAndHashCode(callSuper = false)
public class ArsivSahibiPersonelOnayLogDto extends ArsivSahibiPersonelOnayLogYalinDto {
	private int islem;
	private KullaniciYalinDto onaylayan_kullanici_id;
	private ArsivSahibiPersonelYalinDto onaylanan_personel_id;
}

