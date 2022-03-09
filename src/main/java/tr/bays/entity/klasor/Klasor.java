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

import tr.bays.entity.fon.Fon;
import tr.bays.entity.klasor.Klasor;
import tr.bays.entity.depo.Depo;

@SuppressWarnings("serial")
@Entity(name = "Klasor")
@Data
@EqualsAndHashCode(callSuper = false)
@Table(name = "klasor")
@Cache(region = "baysKlasorCache", usage = CacheConcurrencyStrategy.READ_WRITE)
public class Klasor extends VersionedEntity {

	@Column(name="klasor_no", nullable = false)
	private String klasor_no;
	@Column(name="klasor_ek_no")
	private String klasor_ek_no;
	@Column(name="gizlilik", nullable = false)
	private int gizlilik;
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="fon_id")
	private Fon fon_id;
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="ust_klasor_id")
	private Klasor ust_klasor_id;
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="depo_id")
	private Depo depo_id;
	public String toString() {
		String str = "{";

		str += "ID = [" + id + "], ";
		str += "Version = [" + version + "], ";
		str += "Klasor No = [" + klasor_no + "], ";
		str += "Klasor Ek No = [" + klasor_ek_no + "], ";
		str += "Gizlilik = [" + gizlilik + "], ";
		str += "Fon Id = [" + fon_id + "], ";
		str += "Ust Klasor Id = [" + ust_klasor_id + "], ";
		str += "Depo Id = [" + depo_id + "], ";

		str += "}";

		return str;
	}

}

