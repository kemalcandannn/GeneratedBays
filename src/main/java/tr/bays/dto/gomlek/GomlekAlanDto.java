package tr.bays.dto.gomlek;

import java.sql.Time;
import java.util.Date;

import lombok.Data;
import lombok.EqualsAndHashCode;
import tr.bays.dto.yalin.gomlek.GomlekAlanYalinDto;
import tr.bays.dto.yalin.metadata.AlanYalinDto;
import tr.bays.dto.yalin.gomlek.GomlekYalinDto;

@SuppressWarnings("serial")
@Data
@EqualsAndHashCode(callSuper = false)
public class GomlekAlanDto extends GomlekAlanYalinDto {
	private AlanYalinDto alan_id;
	private GomlekYalinDto gomlek_id;
}

