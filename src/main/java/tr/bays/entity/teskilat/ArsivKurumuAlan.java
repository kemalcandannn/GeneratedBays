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

import tr.bays.entity.teskilat.ArsivKurumu;
import tr.bays.entity.metadata.Alan;

@SuppressWarnings("serial")
@Entity(name = "ArsivKurumuAlan")
@Data
@EqualsAndHashCode(callSuper = false)
@Table(name = "arsiv_kurumu_alan")
@Cache(region = "baysArsivKurumuAlanCache", usage = CacheConcurrencyStrategy.READ_WRITE)
public class ArsivKurumuAlan extends VersionedEntity {

	@Column(name="deger")
	private String deger;
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="arsiv_kurumu_id", nullable = false)
	private ArsivKurumu arsiv_kurumu_id;
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="alan_id", nullable = false)
	private Alan alan_id;
	public String toString() {
		String str = "{";

		str += "ID = [" + id + "], ";
		str += "Version = [" + version + "], ";
		str += "Deger = [" + deger + "], ";
		str += "Arsiv Kurumu Id = [" + arsiv_kurumu_id + "], ";
		str += "Alan Id = [" + alan_id + "], ";

		str += "}";

		return str;
	}

}

