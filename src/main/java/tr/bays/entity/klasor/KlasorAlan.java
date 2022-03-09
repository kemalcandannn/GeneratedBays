package tr.bays.entity.klasor;

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

import tr.bays.entity.metadata.Alan;
import tr.bays.entity.klasor.Klasor;

@SuppressWarnings("serial")
@Entity(name = "KlasorAlan")
@Data
@EqualsAndHashCode(callSuper = false)
@Table(name = "klasor_alan")
@Cache(region = "baysKlasorAlanCache", usage = CacheConcurrencyStrategy.READ_WRITE)
public class KlasorAlan extends VersionedEntity {

	@Column(name="deger")
	private String deger;
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="alan_id", nullable = false)
	private Alan alan_id;
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="klasor_id", nullable = false)
	private Klasor klasor_id;
	public String toString() {
		String str = "{";

		str += "ID = [" + id + "], ";
		str += "Version = [" + version + "], ";
		str += "Deger = [" + deger + "], ";
		str += "Alan Id = [" + alan_id + "], ";
		str += "Klasor Id = [" + klasor_id + "], ";

		str += "}";

		return str;
	}

}

