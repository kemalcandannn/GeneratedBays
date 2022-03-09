package tr.bays.dto.defter;

import java.sql.Time;
import java.util.Date;

import lombok.Data;
import lombok.EqualsAndHashCode;
import tr.bays.dto.yalin.defter.DefterAlanYalinDto;
import tr.bays.dto.yalin.metadata.AlanYalinDto;
import tr.bays.dto.yalin.defter.DefterYalinDto;

@SuppressWarnings("serial")
@Data
@EqualsAndHashCode(callSuper = false)
public class DefterAlanDto extends DefterAlanYalinDto {
	private AlanYalinDto alan_id;
	private DefterYalinDto defter_id;
}

