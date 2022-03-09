package tr.bays.dto.depoMuhafaza;

import java.sql.Time;
import java.util.Date;

import lombok.Data;
import lombok.EqualsAndHashCode;
import tr.bays.dto.yalin.depoMuhafaza.GomlekBakimKlasorYalinDto;
import tr.bays.dto.yalin.klasor.KlasorYalinDto;
import tr.bays.dto.yalin.depoMuhafaza.GomlekBakimYalinDto;
import tr.bays.dto.yalin.depoMuhafaza.GomlekBakimYalinDto;
import tr.bays.dto.yalin.depoMuhafaza.GomlekBakimYalinDto;

@SuppressWarnings("serial")
@Data
@EqualsAndHashCode(callSuper = false)
public class GomlekBakimKlasorDto extends GomlekBakimKlasorYalinDto {
	private KlasorYalinDto klasor_id;
	private GomlekBakimYalinDto planlanan_id;
	private GomlekBakimYalinDto atanan_id;
	private GomlekBakimYalinDto yenilenen_id;
}

