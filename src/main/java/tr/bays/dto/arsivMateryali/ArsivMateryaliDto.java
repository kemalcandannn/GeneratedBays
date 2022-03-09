package tr.bays.dto.arsivMateryali;

import java.sql.Time;
import java.util.Date;

import lombok.Data;
import lombok.EqualsAndHashCode;
import tr.bays.dto.yalin.arsivMateryali.ArsivMateryaliYalinDto;
import tr.bays.dto.yalin.fon.FonYalinDto;
import tr.bays.dto.yalin.depo.DepoYalinDto;

@SuppressWarnings("serial")
@Data
@EqualsAndHashCode(callSuper = false)
public class ArsivMateryaliDto extends ArsivMateryaliYalinDto {
	private String materyal_no;
	private String ek_no;
	private int gizlilik;
	private FonYalinDto fon_id;
	private DepoYalinDto depo_id;
}

