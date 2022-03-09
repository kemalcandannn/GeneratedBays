package tr.bays.dto.calismaGrubu;

import java.sql.Time;
import java.util.Date;

import lombok.Data;
import lombok.EqualsAndHashCode;
import tr.bays.dto.yalin.calismaGrubu.CalismaGrubuUyeYalinDto;
import tr.bays.dto.yalin.teskilat.KullaniciYalinDto;
import tr.bays.dto.yalin.arsivSahibi.ArsivSahibiPersonelYalinDto;
import tr.bays.dto.yalin.calismaGrubu.CalismaGrubuYalinDto;

@SuppressWarnings("serial")
@Data
@EqualsAndHashCode(callSuper = false)
public class CalismaGrubuUyeDto extends CalismaGrubuUyeYalinDto {
	private KullaniciYalinDto kullanici_id;
	private ArsivSahibiPersonelYalinDto arsiv_sahibi_personel_id;
	private CalismaGrubuYalinDto calisma_grubu_id;
}

