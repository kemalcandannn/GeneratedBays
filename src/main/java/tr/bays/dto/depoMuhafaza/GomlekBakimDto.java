package tr.bays.dto.depoMuhafaza;

import java.sql.Time;
import java.util.Date;

import lombok.Data;
import lombok.EqualsAndHashCode;
import tr.bays.dto.yalin.depoMuhafaza.GomlekBakimYalinDto;
import tr.bays.dto.yalin.teskilat.KullaniciYalinDto;

@SuppressWarnings("serial")
@Data
@EqualsAndHashCode(callSuper = false)
public class GomlekBakimDto extends GomlekBakimYalinDto {
	private String ip;
	private int durum;
	private KullaniciYalinDto kullanici_id;
}

