package tr.bays.dto.calismaGrubu;

import java.sql.Time;
import java.util.Date;

import lombok.Data;
import lombok.EqualsAndHashCode;
import tr.bays.dto.yalin.calismaGrubu.CalismaGrubuYalinDto;
import tr.bays.dto.yalin.fon.FonYalinDto;

@SuppressWarnings("serial")
@Data
@EqualsAndHashCode(callSuper = false)
public class CalismaGrubuDto extends CalismaGrubuYalinDto {
	private Date tarih_saat;
	private int durum;
	private String aciklama;
	private String resmi_yazi_url;
	private String sonuc_url;
	private FonYalinDto fon_id;
}

