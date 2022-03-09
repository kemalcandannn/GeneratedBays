package tr.bays.dto.yalin.depoMuhafaza;

import java.util.Date;
import java.sql.Time;
import java.text.SimpleDateFormat;

import tr.bays.common.base.VersionedDto;

import lombok.Data;

@Data
public class TopluTasimaYalinDto extends VersionedDto{
	private Date tarih_saat;
}
