package tr.bays.dto.metadata;

import java.sql.Time;
import java.util.Date;

import lombok.Data;
import lombok.EqualsAndHashCode;
import tr.bays.dto.yalin.metadata.AlanYalinDto;
import tr.bays.dto.yalin.metadata.MetadataSetiYalinDto;

@SuppressWarnings("serial")
@Data
@EqualsAndHashCode(callSuper = false)
public class AlanDto extends AlanYalinDto {
	private MetadataSetiYalinDto metadata_seti_id;
}

