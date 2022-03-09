package tr.bays.dto.arsivSahibi;

import java.sql.Time;
import java.util.Date;

import lombok.Data;
import lombok.EqualsAndHashCode;
import tr.bays.dto.yalin.arsivSahibi.PersonelHesapBilgileriYalinDto;
import tr.bays.dto.yalin.arsivSahibi.ArsivSahibiPersonelYalinDto;

@SuppressWarnings("serial")
@Data
@EqualsAndHashCode(callSuper = false)
public class PersonelHesapBilgileriDto extends PersonelHesapBilgileriYalinDto {
	private String parola;
	private int aktif;
	private int onay_durumu;
	private ArsivSahibiPersonelYalinDto arsiv_sahibi_personel_id;
}

