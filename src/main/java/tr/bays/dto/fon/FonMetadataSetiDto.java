package tr.bays.dto.fon;

import java.sql.Time;
import java.util.Date;

import lombok.Data;
import lombok.EqualsAndHashCode;
import tr.bays.dto.yalin.fon.FonMetadataSetiYalinDto;
import tr.bays.dto.yalin.metadata.MetadataSetiYalinDto;
import tr.bays.dto.yalin.fon.FonYalinDto;

@SuppressWarnings("serial")
@Data
@EqualsAndHashCode(callSuper = false)
public class FonMetadataSetiDto extends FonMetadataSetiYalinDto {
	private MetadataSetiYalinDto metadata_seti_id;
	private FonYalinDto fon_id;
}

