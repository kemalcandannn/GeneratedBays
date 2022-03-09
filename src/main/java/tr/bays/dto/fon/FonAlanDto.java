package tr.bays.dto.fon;

import java.sql.Time;
import java.util.Date;

import lombok.Data;
import lombok.EqualsAndHashCode;
import tr.bays.dto.yalin.fon.FonAlanYalinDto;
import tr.bays.dto.yalin.metadata.AlanYalinDto;
import tr.bays.dto.yalin.fon.FonYalinDto;

@SuppressWarnings("serial")
@Data
@EqualsAndHashCode(callSuper = false)
public class FonAlanDto extends FonAlanYalinDto {
	private AlanYalinDto alan_id;
	private FonYalinDto fon_id;
}

