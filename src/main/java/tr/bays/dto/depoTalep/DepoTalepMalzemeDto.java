package tr.bays.dto.depoTalep;

import java.sql.Time;
import java.util.Date;

import lombok.Data;
import lombok.EqualsAndHashCode;
import tr.bays.dto.yalin.depoTalep.DepoTalepMalzemeYalinDto;
import tr.bays.dto.yalin.klasor.KlasorYalinDto;
import tr.bays.dto.yalin.gomlek.GomlekYalinDto;
import tr.bays.dto.yalin.defter.DefterYalinDto;
import tr.bays.dto.yalin.arsivMateryali.ArsivMateryaliYalinDto;
import tr.bays.dto.yalin.arsivMateryali.IslenmemisKlasorYalinDto;
import tr.bays.dto.yalin.depoTalep.DepoTalepYalinDto;

@SuppressWarnings("serial")
@Data
@EqualsAndHashCode(callSuper = false)
public class DepoTalepMalzemeDto extends DepoTalepMalzemeYalinDto {
	private KlasorYalinDto klasor_id;
	private GomlekYalinDto gomlek_id;
	private DefterYalinDto defter_id;
	private ArsivMateryaliYalinDto arsiv_materyali_id;
	private IslenmemisKlasorYalinDto islenmemis_klasor_id;
	private DepoTalepYalinDto depo_talep_id;
}

