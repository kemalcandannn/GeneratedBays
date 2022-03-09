package tr.bays.entity.teskilat;

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
@Entity(name = "Kullanici")
@Data
@EqualsAndHashCode(callSuper = false)
@Table(name = "kullanici")
@Cache(region = "baysKullaniciCache", usage = CacheConcurrencyStrategy.READ_WRITE)
public class Kullanici extends VersionedEntity {

	@Column(name="kullanici_adi", nullable = false)
	private String kullanici_adi;
	@Column(name="parola", nullable = false)
	private String parola;
	@Column(name="ad_soyad", nullable = false)
	private String ad_soyad;
	@Column(name="eposta", nullable = false)
	private String eposta;
	@Column(name="kurum_tel_no")
	private String kurum_tel_no;
	@Column(name="cep_tel_no")
	private String cep_tel_no;
	@Column(name="aktif", nullable = false)
	private int aktif;
	public String toString() {
		String str = "{";

		str += "ID = [" + id + "], ";
		str += "Version = [" + version + "], ";
		str += "Ad Soyad = [" + ad_soyad + "], ";
		str += "Eposta = [" + eposta + "], ";
		str += "Kurum Tel No = [" + kurum_tel_no + "], ";
		str += "Cep Tel No = [" + cep_tel_no + "], ";
		str += "Aktif = [" + aktif + "], ";

		str += "}";

		return str;
	}

}

