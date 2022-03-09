package tr.bays.dto.hamEvrak;

import java.sql.Time;
import java.util.Date;

import lombok.Data;
import lombok.EqualsAndHashCode;
import tr.bays.dto.yalin.hamEvrak.DepoIciTasimaYalinDto;
import tr.bays.dto.yalin.teskilat.KullaniciYalinDto;
import tr.bays.dto.yalin.hamEvrak.HamEvrakYalinDto;
import tr.bays.dto.yalin.hamEvrak.HamEvrakYalinDto;

@SuppressWarnings("serial")
@Data
@EqualsAndHashCode(callSuper = false)
public class DepoIciTasimaDto extends DepoIciTasimaYalinDto {
	private int adet;
	private KullaniciYalinDto kullanici_id;
	private HamEvrakYalinDto kaynak_id;
	private HamEvrakYalinDto hedef_id;
}

