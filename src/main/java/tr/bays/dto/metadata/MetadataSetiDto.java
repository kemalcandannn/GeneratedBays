package tr.bays.dto.metadata;

import java.sql.Time;
import java.util.Date;

import lombok.Data;
import lombok.EqualsAndHashCode;
import tr.bays.dto.yalin.metadata.MetadataSetiYalinDto;

@SuppressWarnings("serial")
@Data
@EqualsAndHashCode(callSuper = false)
public class MetadataSetiDto extends MetadataSetiYalinDto {
	private int tur;
}

