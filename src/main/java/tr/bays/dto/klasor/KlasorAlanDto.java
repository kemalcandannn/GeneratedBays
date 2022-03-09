package tr.bays.dto.klasor;

import java.sql.Time;
import java.util.Date;

import lombok.Data;
import lombok.EqualsAndHashCode;
import tr.bays.dto.yalin.klasor.KlasorAlanYalinDto;
import tr.bays.dto.yalin.metadata.AlanYalinDto;
import tr.bays.dto.yalin.klasor.KlasorYalinDto;

@SuppressWarnings("serial")
@Data
@EqualsAndHashCode(callSuper = false)
public class KlasorAlanDto extends KlasorAlanYalinDto {
	private AlanYalinDto alan_id;
	private KlasorYalinDto klasor_id;
}

