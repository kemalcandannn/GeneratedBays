package tr.bays.dto.klasor;

import java.sql.Time;
import java.util.Date;

import lombok.Data;
import lombok.EqualsAndHashCode;
import tr.bays.dto.yalin.klasor.KlasorYalinDto;
import tr.bays.dto.yalin.fon.FonYalinDto;
import tr.bays.dto.yalin.klasor.KlasorYalinDto;
import tr.bays.dto.yalin.depo.DepoYalinDto;

@SuppressWarnings("serial")
@Data
@EqualsAndHashCode(callSuper = false)
public class KlasorDto extends KlasorYalinDto {
	private String klasor_ek_no;
	private int gizlilik;
	private FonYalinDto fon_id;
	private KlasorYalinDto ust_klasor_id;
	private DepoYalinDto depo_id;
}

