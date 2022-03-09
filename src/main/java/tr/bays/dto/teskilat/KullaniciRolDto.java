package tr.bays.dto.teskilat;

import java.sql.Time;
import java.util.Date;

import lombok.Data;
import lombok.EqualsAndHashCode;
import tr.bays.dto.yalin.teskilat.KullaniciRolYalinDto;
import tr.bays.dto.yalin.genel.RolYalinDto;
import tr.bays.dto.yalin.teskilat.KullaniciYalinDto;

@SuppressWarnings("serial")
@Data
@EqualsAndHashCode(callSuper = false)
public class KullaniciRolDto extends KullaniciRolYalinDto {
	private RolYalinDto rol_id;
	private KullaniciYalinDto kullanici_id;
}

