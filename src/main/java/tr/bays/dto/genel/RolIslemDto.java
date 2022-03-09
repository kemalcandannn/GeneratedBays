package tr.bays.dto.genel;

import java.sql.Time;
import java.util.Date;

import lombok.Data;
import lombok.EqualsAndHashCode;
import tr.bays.dto.yalin.genel.RolIslemYalinDto;
import tr.bays.dto.yalin.genel.RolYalinDto;
import tr.bays.dto.yalin.genel.IslemYalinDto;

@SuppressWarnings("serial")
@Data
@EqualsAndHashCode(callSuper = false)
public class RolIslemDto extends RolIslemYalinDto {
	private RolYalinDto rol_id;
	private IslemYalinDto islem_id;
}

