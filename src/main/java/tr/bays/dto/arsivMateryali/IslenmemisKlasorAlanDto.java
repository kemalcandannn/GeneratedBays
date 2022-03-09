package tr.bays.dto.arsivMateryali;

import java.sql.Time;
import java.util.Date;

import lombok.Data;
import lombok.EqualsAndHashCode;
import tr.bays.dto.yalin.arsivMateryali.IslenmemisKlasorAlanYalinDto;
import tr.bays.dto.yalin.metadata.AlanYalinDto;
import tr.bays.dto.yalin.arsivMateryali.IslenmemisKlasorYalinDto;

@SuppressWarnings("serial")
@Data
@EqualsAndHashCode(callSuper = false)
public class IslenmemisKlasorAlanDto extends IslenmemisKlasorAlanYalinDto {
	private AlanYalinDto alan_id;
	private IslenmemisKlasorYalinDto islenmemis_klasor_id;
}

