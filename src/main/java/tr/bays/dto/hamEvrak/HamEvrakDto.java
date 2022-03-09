package tr.bays.dto.hamEvrak;

import java.sql.Time;
import java.util.Date;

import lombok.Data;
import lombok.EqualsAndHashCode;
import tr.bays.dto.yalin.hamEvrak.HamEvrakYalinDto;
import tr.bays.dto.yalin.fon.FonYalinDto;
import tr.bays.dto.yalin.depo.DepoYalinDto;
import tr.bays.dto.yalin.depo.DepoYalinDto;

@SuppressWarnings("serial")
@Data
@EqualsAndHashCode(callSuper = false)
public class HamEvrakDto extends HamEvrakYalinDto {
	private int adet;
	private FonYalinDto fon_id;
	private DepoYalinDto bas_depo_id;
	private DepoYalinDto bit_depo_id;
}

