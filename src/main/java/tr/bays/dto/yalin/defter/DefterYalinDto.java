package tr.bays.dto.yalin.defter;

import java.util.Date;
import java.sql.Time;
import java.text.SimpleDateFormat;

import tr.bays.common.base.VersionedDto;

import lombok.Data;

@Data
public class DefterYalinDto extends VersionedDto{
	private String defter_no;
}
