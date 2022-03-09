package tr.bays.dto.sistem;

import java.sql.Time;
import java.util.Date;

import lombok.Data;
import lombok.EqualsAndHashCode;
import tr.bays.dto.yalin.sistem.DbLogYalinDto;

@SuppressWarnings("serial")
@Data
@EqualsAndHashCode(callSuper = false)
public class DbLogDto extends DbLogYalinDto {
	private String ip;
	private Date tarih;
	private Time saat;
	private int tur;
	private String aciklama;
	private String ek1;
	private String ek2;
	private String puk;
	private String sinif;
	private String metod;
	private String islem;
}

