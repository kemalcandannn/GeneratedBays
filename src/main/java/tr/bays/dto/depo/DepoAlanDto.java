package tr.bays.dto.depo;

import java.sql.Time;
import java.util.Date;

import lombok.Data;
import lombok.EqualsAndHashCode;
import tr.bays.dto.yalin.depo.DepoAlanYalinDto;
import tr.bays.dto.yalin.metadata.AlanYalinDto;
import tr.bays.dto.yalin.depo.DepoYalinDto;

@SuppressWarnings("serial")
@Data
@EqualsAndHashCode(callSuper = false)
public class DepoAlanDto extends DepoAlanYalinDto {
	private AlanYalinDto alan_id;
	private DepoYalinDto depo_id;
}

