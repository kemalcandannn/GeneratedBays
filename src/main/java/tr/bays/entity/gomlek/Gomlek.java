package tr.bays.entity.gomlek;

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

import tr.bays.entity.sozluk.SozlukMadde;
import tr.bays.entity.klasor.Klasor;

@SuppressWarnings("serial")
@Entity(name = "Gomlek")
@Data
@EqualsAndHashCode(callSuper = false)
@Table(name = "gomlek")
@Cache(region = "baysGomlekCache", usage = CacheConcurrencyStrategy.READ_WRITE)
public class Gomlek extends VersionedEntity {

	@Column(name="transkripsiyon")
	private String transkripsiyon;
	@Column(name="tarih_tur", nullable = false)
	private int tarih_tur;
	@Column(name="ay", nullable = false)
	private String ay;
	@Column(name="gun", nullable = false)
	private String gun;
	@Column(name="yil", nullable = false)
	private String yil;
	@Column(name="gizlilik", nullable = false)
	private int gizlilik;
	@Column(name="restorasyon_ihtiyaci", nullable = false)
	private int restorasyon_ihtiyaci;
	@Column(name="ozel_no")
	private String ozel_no;
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="ozel_no_turu_id")
	private SozlukMadde ozel_no_turu_id;
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="klasor_id", nullable = false)
	private Klasor klasor_id;
	public String toString() {
		String str = "{";

		str += "ID = [" + id + "], ";
		str += "Version = [" + version + "], ";
		str += "Transkripsiyon = [" + transkripsiyon + "], ";
		str += "Tarih Tur = [" + tarih_tur + "], ";
		str += "Ay = [" + ay + "], ";
		str += "Gun = [" + gun + "], ";
		str += "Yil = [" + yil + "], ";
		str += "Gizlilik = [" + gizlilik + "], ";
		str += "Restorasyon Ihtiyaci = [" + restorasyon_ihtiyaci + "], ";
		str += "Ozel No = [" + ozel_no + "], ";
		str += "Ozel No Turu Id = [" + ozel_no_turu_id + "], ";
		str += "Klasor Id = [" + klasor_id + "], ";

		str += "}";

		return str;
	}

}

