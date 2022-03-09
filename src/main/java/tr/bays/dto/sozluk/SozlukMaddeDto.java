package tr.bays.dto.sozluk;

import java.sql.Time;
import java.util.Date;

import lombok.Data;
import lombok.EqualsAndHashCode;
import tr.bays.dto.yalin.sozluk.SozlukMaddeYalinDto;
import tr.bays.dto.yalin.sozluk.SozlukYalinDto;

@SuppressWarnings("serial")
@Data
@EqualsAndHashCode(callSuper = false)
public class SozlukMaddeDto extends SozlukMaddeYalinDto {
	private String kisaltma;
	private String aciklama;
	private String liste_aciklama;
	private int aktif;
	private SozlukYalinDto sozluk_id;
}

