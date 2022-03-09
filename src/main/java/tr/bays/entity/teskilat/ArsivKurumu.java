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
@Entity(name = "ArsivKurumu")
@Data
@EqualsAndHashCode(callSuper = false)
@Table(name = "arsiv_kurumu")
@Cache(region = "baysArsivKurumuCache", usage = CacheConcurrencyStrategy.READ_WRITE)
public class ArsivKurumu extends VersionedEntity {

	@Column(name="ad", nullable = false, unique = true)
	private String ad;
	@Column(name="durum", nullable = false)
	private int durum;
	public String toString() {
		String str = "{";

		str += "ID = [" + id + "], ";
		str += "Version = [" + version + "], ";
		str += "Ad = [" + ad + "], ";
		str += "Durum = [" + durum + "], ";

		str += "}";

		return str;
	}

}

