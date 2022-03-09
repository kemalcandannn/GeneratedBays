package tr.bays.dto.teskilat;

import java.sql.Time;
import java.util.Date;

import lombok.Data;
import lombok.EqualsAndHashCode;
import tr.bays.dto.yalin.teskilat.KullaniciYalinDto;

@SuppressWarnings("serial")
@Data
@EqualsAndHashCode(callSuper = false)
public class KullaniciDto extends KullaniciYalinDto {
	private String eposta;
	private String kurum_tel_no;
	private String cep_tel_no;
	private int aktif;
}

