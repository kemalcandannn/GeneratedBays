package tr.bays.dto.fon;

import java.sql.Time;
import java.util.Date;

import lombok.Data;
import lombok.EqualsAndHashCode;
import tr.bays.dto.yalin.fon.FonYalinDto;
import tr.bays.dto.yalin.teskilat.ArsivKurumuYalinDto;
import tr.bays.dto.yalin.arsivSahibi.ArsivSahibiYalinDto;
import tr.bays.dto.yalin.fon.FonYalinDto;

@SuppressWarnings("serial")
@Data
@EqualsAndHashCode(callSuper = false)
public class FonDto extends FonYalinDto {
	private int gizlilik;
	private int gomlek_defter_tarih_tur;
	private Date tarih;
	private Date onay_tarihi;
	private int tur;
	private ArsivKurumuYalinDto arsiv_kurumu_id;
	private ArsivSahibiYalinDto arsiv_sahibi_id;
	private FonYalinDto ust_fon_id;
}

