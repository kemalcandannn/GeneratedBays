package tr.bays.entity.calismaGrubu;

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

import tr.bays.entity.fon.Fon;

@SuppressWarnings("serial")
@Entity(name = "CalismaGrubu")
@Data
@EqualsAndHashCode(callSuper = false)
@Table(name = "calisma_grubu")
@Cache(region = "baysCalismaGrubuCache", usage = CacheConcurrencyStrategy.READ_WRITE)
public class CalismaGrubu extends VersionedEntity {

	@Column(name="ad", nullable = false, unique = true)
	private String ad;
	@Column(name="tarih_saat", nullable = false)
	private Date tarih_saat;
	@Column(name="durum", nullable = false)
	private int durum;
	@Column(name="aciklama")
	private String aciklama;
	@Column(name="resmi_yazi_url", nullable = false)
	private String resmi_yazi_url;
	@Column(name="sonuc_url")
	private String sonuc_url;
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="fon_id", nullable = false)
	private Fon fon_id;
	public String toString() {
		String str = "{";

		str += "ID = [" + id + "], ";
		str += "Version = [" + version + "], ";
		str += "Ad = [" + ad + "], ";
		str += "Tarih Saat = [" + tarih_saat + "], ";
		str += "Durum = [" + durum + "], ";
		str += "Aciklama = [" + aciklama + "], ";
		str += "Resmi Yazi Url = [" + resmi_yazi_url + "], ";
		str += "Sonuc Url = [" + sonuc_url + "], ";
		str += "Fon Id = [" + fon_id + "], ";

		str += "}";

		return str;
	}

}

