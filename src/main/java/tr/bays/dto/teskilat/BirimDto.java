package tr.bays.dto.teskilat;

import java.sql.Time;
import java.util.Date;

import lombok.Data;
import lombok.EqualsAndHashCode;
import tr.bays.dto.yalin.teskilat.BirimYalinDto;
import tr.bays.dto.yalin.teskilat.BirimYalinDto;
import tr.bays.dto.yalin.teskilat.ArsivKurumuYalinDto;
import tr.bays.dto.yalin.sozluk.SozlukMaddeYalinDto;
import tr.bays.dto.yalin.teskilat.KullaniciYalinDto;

@SuppressWarnings("serial")
@Data
@EqualsAndHashCode(callSuper = false)
public class BirimDto extends BirimYalinDto {
	private int aktif;
	private BirimYalinDto ust_birim_id;
	private ArsivKurumuYalinDto arsiv_kurumu_id;
	private SozlukMaddeYalinDto birim_turu_sozluk_satir_id;
	private KullaniciYalinDto yonetici_id;
}

