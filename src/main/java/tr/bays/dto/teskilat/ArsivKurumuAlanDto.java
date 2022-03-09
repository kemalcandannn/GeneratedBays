package tr.bays.dto.teskilat;

import java.sql.Time;
import java.util.Date;

import lombok.Data;
import lombok.EqualsAndHashCode;
import tr.bays.dto.yalin.teskilat.ArsivKurumuAlanYalinDto;
import tr.bays.dto.yalin.teskilat.ArsivKurumuYalinDto;
import tr.bays.dto.yalin.metadata.AlanYalinDto;

@SuppressWarnings("serial")
@Data
@EqualsAndHashCode(callSuper = false)
public class ArsivKurumuAlanDto extends ArsivKurumuAlanYalinDto {
	private ArsivKurumuYalinDto arsiv_kurumu_id;
	private AlanYalinDto alan_id;
}

