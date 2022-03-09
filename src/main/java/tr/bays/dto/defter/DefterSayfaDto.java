package tr.bays.dto.defter;

import java.sql.Time;
import java.util.Date;

import lombok.Data;
import lombok.EqualsAndHashCode;
import tr.bays.dto.yalin.defter.DefterSayfaYalinDto;
import tr.bays.dto.yalin.defter.DefterYalinDto;

@SuppressWarnings("serial")
@Data
@EqualsAndHashCode(callSuper = false)
public class DefterSayfaDto extends DefterSayfaYalinDto {
	private int tur;
	private String sayfa_no;
	private DefterYalinDto defter_id;
}

