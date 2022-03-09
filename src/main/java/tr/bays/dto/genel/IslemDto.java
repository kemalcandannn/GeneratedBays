package tr.bays.dto.genel;

import java.sql.Time;
import java.util.Date;

import lombok.Data;
import lombok.EqualsAndHashCode;
import tr.bays.dto.yalin.genel.IslemYalinDto;
import tr.bays.dto.yalin.genel.IslemYalinDto;

@SuppressWarnings("serial")
@Data
@EqualsAndHashCode(callSuper = false)
public class IslemDto extends IslemYalinDto {
	private String aciklama;
	private boolean agacta_goster;
	private IslemYalinDto ust_islem_id;
}

