package tr.bays.dto.defter;

import java.sql.Time;
import java.util.Date;

import lombok.Data;
import lombok.EqualsAndHashCode;
import tr.bays.dto.yalin.defter.DefterYalinDto;
import tr.bays.dto.yalin.sozluk.SozlukMaddeYalinDto;
import tr.bays.dto.yalin.fon.FonYalinDto;
import tr.bays.dto.yalin.depo.DepoYalinDto;

@SuppressWarnings("serial")
@Data
@EqualsAndHashCode(callSuper = false)
public class DefterDto extends DefterYalinDto {
	private String ek_no;
	private int gizlilik;
	private SozlukMaddeYalinDto defter_turu_id;
	private FonYalinDto fon_id;
	private DepoYalinDto depo_id;
}

