package tr.bays.entity.sozluk;

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

import tr.bays.entity.sozluk.Sozluk;

@SuppressWarnings("serial")
@Entity(name = "SozlukMadde")
@Data
@EqualsAndHashCode(callSuper = false)
@Table(name = "sozluk_madde")
@Cache(region = "baysSozlukMaddeCache", usage = CacheConcurrencyStrategy.READ_WRITE)
public class SozlukMadde extends VersionedEntity {

	@Column(name="ad", nullable = false)
	private String ad;
	@Column(name="kisaltma")
	private String kisaltma;
	@Column(name="aciklama")
	private String aciklama;
	@Column(name="liste_aciklama")
	private String liste_aciklama;
	@Column(name="aktif", nullable = false)
	private int aktif;
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="sozluk_id", nullable = false)
	private Sozluk sozluk_id;
	public String toString() {
		String str = "{";

		str += "ID = [" + id + "], ";
		str += "Version = [" + version + "], ";
		str += "Ad = [" + ad + "], ";
		str += "Kisaltma = [" + kisaltma + "], ";
		str += "Aciklama = [" + aciklama + "], ";
		str += "Liste Aciklama = [" + liste_aciklama + "], ";
		str += "Aktif = [" + aktif + "], ";
		str += "Sozluk Id = [" + sozluk_id + "], ";

		str += "}";

		return str;
	}

}

