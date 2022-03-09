package tr.bays.entity.defter;

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

import tr.bays.entity.defter.Defter;

@SuppressWarnings("serial")
@Entity(name = "DefterSayfa")
@Data
@EqualsAndHashCode(callSuper = false)
@Table(name = "defter_sayfa")
@Cache(region = "baysDefterSayfaCache", usage = CacheConcurrencyStrategy.READ_WRITE)
public class DefterSayfa extends VersionedEntity {

	@Column(name="gizlilik", nullable = false)
	private int gizlilik;
	@Column(name="tur", nullable = false)
	private int tur;
	@Column(name="sayfa_no", nullable = false)
	private String sayfa_no;
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="defter_id", nullable = false)
	private Defter defter_id;
	public String toString() {
		String str = "{";

		str += "ID = [" + id + "], ";
		str += "Version = [" + version + "], ";
		str += "Gizlilik = [" + gizlilik + "], ";
		str += "Tur = [" + tur + "], ";
		str += "Sayfa No = [" + sayfa_no + "], ";
		str += "Defter Id = [" + defter_id + "], ";

		str += "}";

		return str;
	}

}

