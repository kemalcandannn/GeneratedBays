package tr.bays.dto.yalin.fon;

import java.util.Date;
import java.sql.Time;
import java.text.SimpleDateFormat;

import tr.bays.common.base.VersionedDto;

import lombok.Data;

@Data
public class FonOnculVeriLogYalinDto extends VersionedDto{
	private String oncul_veri;
}
