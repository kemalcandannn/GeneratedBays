package tr.bays.entity.sistem;

import java.sql.Time;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import lombok.Data;
import lombok.EqualsAndHashCode;
import tr.bays.entity.VersionedEntity;


@SuppressWarnings("serial")
@Entity(name = "DbLog")
@Data
@EqualsAndHashCode(callSuper = false)
@Table(name = "db_log")
@Cache(region = "baysDbLogCache", usage = CacheConcurrencyStrategy.READ_WRITE)
public class DbLog extends VersionedEntity {

	@Column(name="ad")
	private String ad;
	@Column(name="ip")
	private String ip;
	@Column(name="tarih")
	private Date tarih;
	@Column(name="saat")
	private Time saat;
	@Column(name="tur")
	private int tur;
	@Column(name="aciklama")
	private String aciklama;
	@Column(name="ek1")
	private String ek1;
	@Column(name="ek2")
	private String ek2;
	@Column(name="puk")
	private String puk;
	@Column(name="sinif")
	private String sinif;
	@Column(name="metod")
	private String metod;
	@Column(name="islem")
	private String islem;
	public String toString() {
		String str = "{";

		str += "ID = [" + id + "], ";
		str += "Version = [" + version + "], ";
		str += "Ad = [" + ad + "], ";
		str += "Ip = [" + ip + "], ";
		str += "Tarih = [" + tarih + "], ";
		str += "Saat = [" + saat + "], ";
		str += "Tur = [" + tur + "], ";
		str += "Aciklama = [" + aciklama + "], ";
		str += "Ek1 = [" + ek1 + "], ";
		str += "Ek2 = [" + ek2 + "], ";
		str += "Puk = [" + puk + "], ";
		str += "Sinif = [" + sinif + "], ";
		str += "Metod = [" + metod + "], ";
		str += "Islem = [" + islem + "], ";

		str += "}";

		return str;
	}

}

