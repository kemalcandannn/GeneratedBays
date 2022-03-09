package tr.bays.dto.arsivMateryali;

import java.sql.Time;
import java.util.Date;

import lombok.Data;
import lombok.EqualsAndHashCode;
import tr.bays.dto.yalin.arsivMateryali.ArsivMateryaliAlanYalinDto;
import tr.bays.dto.yalin.metadata.AlanYalinDto;
import tr.bays.dto.yalin.arsivMateryali.ArsivMateryaliYalinDto;

@SuppressWarnings("serial")
@Data
@EqualsAndHashCode(callSuper = false)
public class ArsivMateryaliAlanDto extends ArsivMateryaliAlanYalinDto {
	private AlanYalinDto alan_id;
	private ArsivMateryaliYalinDto arsiv_materyali_id;
}

