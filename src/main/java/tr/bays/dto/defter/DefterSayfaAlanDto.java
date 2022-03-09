package tr.bays.dto.defter;

import java.sql.Time;
import java.util.Date;

import lombok.Data;
import lombok.EqualsAndHashCode;
import tr.bays.dto.yalin.defter.DefterSayfaAlanYalinDto;
import tr.bays.dto.yalin.metadata.AlanYalinDto;
import tr.bays.dto.yalin.defter.DefterSayfaYalinDto;

@SuppressWarnings("serial")
@Data
@EqualsAndHashCode(callSuper = false)
public class DefterSayfaAlanDto extends DefterSayfaAlanYalinDto {
	private AlanYalinDto alan_id;
	private DefterSayfaYalinDto defter_id;
}

