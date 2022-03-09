package tr.bays.dto.yalin.gomlek;

import java.util.Date;
import java.sql.Time;
import java.text.SimpleDateFormat;

import tr.bays.common.base.VersionedDto;

import lombok.Data;

@Data
public class GomlekEnvanterYalinDto extends VersionedDto{
	private int tespit_edilmis_belge_sayisi;
}
