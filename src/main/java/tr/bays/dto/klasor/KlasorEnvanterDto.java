package tr.bays.dto.klasor;

import java.sql.Time;
import java.util.Date;

import lombok.Data;
import lombok.EqualsAndHashCode;
import tr.bays.dto.yalin.klasor.KlasorEnvanterYalinDto;
import tr.bays.dto.yalin.klasor.KlasorYalinDto;

@SuppressWarnings("serial")
@Data
@EqualsAndHashCode(callSuper = false)
public class KlasorEnvanterDto extends KlasorEnvanterYalinDto {
	private int tespit_edilmis_gomlek_sayisi;
	private int kayitli_alt_klasor_sayisi;
	private int kayitli_gomlek_sayisi;
	private KlasorYalinDto klasor_id;
}

