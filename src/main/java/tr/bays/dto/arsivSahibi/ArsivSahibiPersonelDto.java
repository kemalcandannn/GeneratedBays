package tr.bays.dto.arsivSahibi;

import java.sql.Time;
import java.util.Date;

import lombok.Data;
import lombok.EqualsAndHashCode;
import tr.bays.dto.yalin.arsivSahibi.ArsivSahibiPersonelYalinDto;
import tr.bays.dto.yalin.arsivSahibi.ArsivSahibiYalinDto;

@SuppressWarnings("serial")
@Data
@EqualsAndHashCode(callSuper = false)
public class ArsivSahibiPersonelDto extends ArsivSahibiPersonelYalinDto {
	private String eposta;
	private String kurum_tel_no;
	private String cep_tel_no;
	private String gorev;
	private int aktif;
	private ArsivSahibiYalinDto arsiv_sahibi_id;
}

