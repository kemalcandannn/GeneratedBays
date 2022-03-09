package tr.bays.dto.yalin.metadata;

import java.util.Date;
import java.sql.Time;
import java.text.SimpleDateFormat;

import tr.bays.common.base.VersionedDto;

import lombok.Data;

@Data
public class MetadataSetiYalinDto extends VersionedDto{
	private String ad;
}
