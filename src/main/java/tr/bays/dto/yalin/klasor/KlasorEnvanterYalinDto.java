package tr.bays.dto.yalin.klasor;

import java.util.Date;
import java.sql.Time;
import java.text.SimpleDateFormat;

import tr.bays.common.base.VersionedDto;

import lombok.Data;

@Data
public class KlasorEnvanterYalinDto extends VersionedDto{
	private int tespit_edilmis_alt_klasor_sayisi;
}
