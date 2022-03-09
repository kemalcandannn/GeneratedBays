package tr.bays.entity.arsivMateryali;

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
import tr.bays.entity.depo.Depo;

@SuppressWarnings("serial")
@Entity(name = "ArsivMateryali")
@Data
@EqualsAndHashCode(callSuper = false)
@Table(name = "arsiv_materyali")
@Cache(region = "baysArsivMateryaliCache", usage = CacheConcurrencyStrategy.READ_WRITE)
public class ArsivMateryali extends VersionedEntity {

	@Column(name="tur", nullable = false)
	private int tur;
	@Column(name="materyal_no", nullable = false)
	private String materyal_no;
	@Column(name="ek_no")
	private String ek_no;
	@Column(name="gizlilik", nullable = false)
	private int gizlilik;
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="fon_id", nullable = false)
	private Fon fon_id;
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="depo_id", nullable = false)
	private Depo depo_id;
	public String toString() {
		String str = "{";

		str += "ID = [" + id + "], ";
		str += "Version = [" + version + "], ";
		str += "Tur = [" + tur + "], ";
		str += "Materyal No = [" + materyal_no + "], ";
		str += "Ek No = [" + ek_no + "], ";
		str += "Gizlilik = [" + gizlilik + "], ";
		str += "Fon Id = [" + fon_id + "], ";
		str += "Depo Id = [" + depo_id + "], ";

		str += "}";

		return str;
	}

}

