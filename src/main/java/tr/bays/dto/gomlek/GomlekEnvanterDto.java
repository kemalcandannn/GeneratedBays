package tr.bays.dto.gomlek;

import java.sql.Time;
import java.util.Date;

import lombok.Data;
import lombok.EqualsAndHashCode;
import tr.bays.dto.yalin.gomlek.GomlekEnvanterYalinDto;
import tr.bays.dto.yalin.gomlek.GomlekYalinDto;

@SuppressWarnings("serial")
@Data
@EqualsAndHashCode(callSuper = false)
public class GomlekEnvanterDto extends GomlekEnvanterYalinDto {
	private int tespit_edilmis_sayfa_sayisi;
	private GomlekYalinDto gomlek_id;
}

