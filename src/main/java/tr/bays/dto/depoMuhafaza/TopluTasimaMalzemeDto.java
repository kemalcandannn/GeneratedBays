package tr.bays.dto.depoMuhafaza;

import java.sql.Time;
import java.util.Date;

import lombok.Data;
import lombok.EqualsAndHashCode;
import tr.bays.dto.yalin.depoMuhafaza.TopluTasimaMalzemeYalinDto;
import tr.bays.dto.yalin.klasor.KlasorYalinDto;
import tr.bays.dto.yalin.defter.DefterYalinDto;
import tr.bays.dto.yalin.arsivMateryali.ArsivMateryaliYalinDto;
import tr.bays.dto.yalin.arsivMateryali.IslenmemisKlasorYalinDto;
import tr.bays.dto.yalin.depoMuhafaza.TopluTasimaYalinDto;

@SuppressWarnings("serial")
@Data
@EqualsAndHashCode(callSuper = false)
public class TopluTasimaMalzemeDto extends TopluTasimaMalzemeYalinDto {
	private String yeni_konum;
	private int tur;
	private KlasorYalinDto klasor_id;
	private DefterYalinDto defter_id;
	private ArsivMateryaliYalinDto arsiv_materyali_id;
	private IslenmemisKlasorYalinDto islenmemis_klasor_id;
	private TopluTasimaYalinDto toplu_tasima_id;
}

