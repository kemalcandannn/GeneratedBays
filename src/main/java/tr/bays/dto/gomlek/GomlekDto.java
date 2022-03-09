package tr.bays.dto.gomlek;

import java.sql.Time;
import java.util.Date;

import lombok.Data;
import lombok.EqualsAndHashCode;
import tr.bays.dto.yalin.gomlek.GomlekYalinDto;
import tr.bays.dto.yalin.sozluk.SozlukMaddeYalinDto;
import tr.bays.dto.yalin.klasor.KlasorYalinDto;

@SuppressWarnings("serial")
@Data
@EqualsAndHashCode(callSuper = false)
public class GomlekDto extends GomlekYalinDto {
	private int tarih_tur;
	private String ay;
	private String gun;
	private String yil;
	private int gizlilik;
	private int restorasyon_ihtiyaci;
	private String ozel_no;
	private SozlukMaddeYalinDto ozel_no_turu_id;
	private KlasorYalinDto klasor_id;
}

