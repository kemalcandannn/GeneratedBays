package tr.bays.dto.yalin.loglama;

import java.util.Date;
import java.sql.Time;
import java.text.SimpleDateFormat;

import tr.bays.common.base.VersionedDto;

import lombok.Data;

@Data
public class DefterTarihceLogYalinDto extends VersionedDto{
	private Date tarih_saat;
}
