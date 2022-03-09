package tr.bays.dto.yalin.teskilat;

import java.util.Date;
import java.sql.Time;
import java.text.SimpleDateFormat;

import tr.bays.common.base.VersionedDto;

import lombok.Data;

@Data
public class KullaniciYalinDto extends VersionedDto{
	private String kullanici_adi;
	private String parola;
	private String ad_soyad;
}
