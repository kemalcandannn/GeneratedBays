package tr.bays.dto.yalin.arsivMateryali;

import java.util.Date;
import java.sql.Time;
import java.text.SimpleDateFormat;

import tr.bays.common.base.VersionedDto;

import lombok.Data;

@Data
public class IslenmemisKlasorYalinDto extends VersionedDto{
	private String klasor_no;
}
