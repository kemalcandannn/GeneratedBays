package tr.bays.dto.fon;

import java.sql.Time;
import java.util.Date;

import lombok.Data;
import lombok.EqualsAndHashCode;
import tr.bays.dto.yalin.fon.FonOnculVeriLogYalinDto;
import tr.bays.dto.yalin.teskilat.KullaniciYalinDto;
import tr.bays.dto.yalin.fon.FonYalinDto;

@SuppressWarnings("serial")
@Data
@EqualsAndHashCode(callSuper = false)
public class FonOnculVeriLogDto extends FonOnculVeriLogYalinDto {
	private String ip;
	private Date tarih_saat;
	private KullaniciYalinDto kullanici_id;
	private FonYalinDto fon_id;
}

