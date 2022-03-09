package tr.bays.dto.teskilat;

import java.sql.Time;
import java.util.Date;

import lombok.Data;
import lombok.EqualsAndHashCode;
import tr.bays.dto.yalin.teskilat.KullaniciHesapYalinDto;
import tr.bays.dto.yalin.teskilat.KullaniciYalinDto;

@SuppressWarnings("serial")
@Data
@EqualsAndHashCode(callSuper = false)
public class KullaniciHesapDto extends KullaniciHesapYalinDto {
	private String parola;
	private int aktif;
	private int onay_durumu;
	private KullaniciYalinDto kullanici_id;
}

