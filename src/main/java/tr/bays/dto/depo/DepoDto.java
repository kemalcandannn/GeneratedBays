package tr.bays.dto.depo;

import java.sql.Time;
import java.util.Date;

import lombok.Data;
import lombok.EqualsAndHashCode;
import tr.bays.dto.yalin.depo.DepoYalinDto;
import tr.bays.dto.yalin.teskilat.ArsivKurumuYalinDto;
import tr.bays.dto.yalin.depo.DepoYalinDto;

@SuppressWarnings("serial")
@Data
@EqualsAndHashCode(callSuper = false)
public class DepoDto extends DepoYalinDto {
	private String kisaltma;
	private String aciklama;
	private int aktif;
	private double kapasite;
	private int calisma_gozu;
	private int tasnifsiz_malzeme_sayisi;
	private ArsivKurumuYalinDto arsiv_kurumu_id;
	private DepoYalinDto ust_depo_id;
}

