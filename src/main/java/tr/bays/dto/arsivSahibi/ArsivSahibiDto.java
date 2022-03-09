package tr.bays.dto.arsivSahibi;

import java.sql.Time;
import java.util.Date;

import lombok.Data;
import lombok.EqualsAndHashCode;
import tr.bays.dto.yalin.arsivSahibi.ArsivSahibiYalinDto;
import tr.bays.dto.yalin.arsivSahibi.ArsivSahibiYalinDto;
import tr.bays.dto.yalin.arsivSahibi.ArsivSahibiYalinDto;

@SuppressWarnings("serial")
@Data
@EqualsAndHashCode(callSuper = false)
public class ArsivSahibiDto extends ArsivSahibiYalinDto {
	private int tur;
	private int aktif;
	private int ust_kurum_iliski;
	private ArsivSahibiYalinDto oncul_versiyon_id;
	private ArsivSahibiYalinDto ust_arsiv_sahibi_id;
}

