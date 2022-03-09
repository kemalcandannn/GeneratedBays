package tr.bays.dto.hamEvrak;

import java.sql.Time;
import java.util.Date;

import lombok.Data;
import lombok.EqualsAndHashCode;
import tr.bays.dto.yalin.hamEvrak.HamEvrakBirimYalinDto;
import tr.bays.dto.yalin.teskilat.BirimYalinDto;
import tr.bays.dto.yalin.teskilat.KullaniciYalinDto;
import tr.bays.dto.yalin.teskilat.KullaniciYalinDto;
import tr.bays.dto.yalin.fon.FonYalinDto;
import tr.bays.dto.yalin.hamEvrak.HamEvrakYalinDto;

@SuppressWarnings("serial")
@Data
@EqualsAndHashCode(callSuper = false)
public class HamEvrakBirimDto extends HamEvrakBirimYalinDto {
	private int adet;
	private int saklanma_turu;
	private BirimYalinDto birim_id;
	private KullaniciYalinDto depo_sorumlusu_id;
	private KullaniciYalinDto birim_sorumlusu_id;
	private FonYalinDto fon_id;
	private HamEvrakYalinDto ham_evrak_id;
}

