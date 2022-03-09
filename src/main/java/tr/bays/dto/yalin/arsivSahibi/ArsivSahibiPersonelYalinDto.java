package tr.bays.dto.yalin.arsivSahibi;

import java.util.Date;
import java.sql.Time;
import java.text.SimpleDateFormat;

import tr.bays.common.base.VersionedDto;

import lombok.Data;

@Data
public class ArsivSahibiPersonelYalinDto extends VersionedDto{
	private String ad_soyad;
}
