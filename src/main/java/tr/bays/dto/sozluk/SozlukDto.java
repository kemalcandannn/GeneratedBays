package tr.bays.dto.sozluk;

import java.sql.Time;
import java.util.Date;

import lombok.Data;
import lombok.EqualsAndHashCode;
import tr.bays.dto.yalin.sozluk.SozlukYalinDto;

@SuppressWarnings("serial")
@Data
@EqualsAndHashCode(callSuper = false)
public class SozlukDto extends SozlukYalinDto {
	private String kisaltma;
	private String aciklama;
	private String liste_aciklama;
	private int kullanim_sekli;
	private int aktif;
}

