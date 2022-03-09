package tr.bays.entity.depoMuhafaza;

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

import tr.bays.entity.klasor.Klasor;
import tr.bays.entity.depoMuhafaza.GomlekBakim;
import tr.bays.entity.depoMuhafaza.GomlekBakim;
import tr.bays.entity.depoMuhafaza.GomlekBakim;

@SuppressWarnings("serial")
@Entity(name = "GomlekBakimKlasor")
@Data
@EqualsAndHashCode(callSuper = false)
@Table(name = "gomlek_bakim_klasor")
@Cache(region = "baysGomlekBakimKlasorCache", usage = CacheConcurrencyStrategy.READ_WRITE)
public class GomlekBakimKlasor extends VersionedEntity {

	@Column(name="durum", nullable = false)
	private int durum;
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="klasor_id", nullable = false)
	private Klasor klasor_id;
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="planlanan_id", nullable = false)
	private GomlekBakim planlanan_id;
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="atanan_id")
	private GomlekBakim atanan_id;
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="yenilenen_id")
	private GomlekBakim yenilenen_id;
	public String toString() {
		String str = "{";

		str += "ID = [" + id + "], ";
		str += "Version = [" + version + "], ";
		str += "Durum = [" + durum + "], ";
		str += "Klasor Id = [" + klasor_id + "], ";
		str += "Planlanan Id = [" + planlanan_id + "], ";
		str += "Atanan Id = [" + atanan_id + "], ";
		str += "Yenilenen Id = [" + yenilenen_id + "], ";

		str += "}";

		return str;
	}

}

