package tr.bays.dto.yalin.depoTalep;

import java.util.Date;
import java.sql.Time;
import java.text.SimpleDateFormat;

import tr.bays.common.base.VersionedDto;

import lombok.Data;

@Data
public class DepoTalepTarihceLogYalinDto extends VersionedDto{
	private int tur;
}
