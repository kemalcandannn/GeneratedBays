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


@SuppressWarnings("serial")
@Entity(name = "Sozluk")
@Data
@EqualsAndHashCode(callSuper = false)
@Table(name = "sozluk")
@Cache(region = "baysSozlukCache", usage = CacheConcurrencyStrategy.READ_WRITE)
public class Sozluk extends VersionedEntity {

	@Column(name="ad", nullable = false, unique = true)
	private String ad;
	@Column(name="kisaltma")
	private String kisaltma;
	@Column(name="aciklama")
	private String aciklama;
	@Column(name="liste_aciklama")
	private String liste_aciklama;
	@Column(name="kullanim_sekli", nullable = false)
	private int kullanim_sekli;
	@Column(name="aktif", nullable = false)
	private int aktif;
	public String toString() {
		String str = "{";

		str += "ID = [" + id + "], ";
		str += "Version = [" + version + "], ";
		str += "Ad = [" + ad + "], ";
		str += "Kisaltma = [" + kisaltma + "], ";
		str += "Aciklama = [" + aciklama + "], ";
		str += "Liste Aciklama = [" + liste_aciklama + "], ";
		str += "Kullanim Sekli = [" + kullanim_sekli + "], ";
		str += "Aktif = [" + aktif + "], ";

		str += "}";

		return str;
	}

}

