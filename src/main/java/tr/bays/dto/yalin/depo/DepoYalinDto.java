package tr.bays.dto.yalin.depo;

import java.util.Date;
import java.sql.Time;
import java.text.SimpleDateFormat;

import tr.bays.common.base.VersionedDto;

import lombok.Data;

@Data
public class DepoYalinDto extends VersionedDto{
	private String ad;
}
