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

import tr.bays.entity.sozluk.SozlukMadde;
import tr.bays.entity.fon.Fon;
import tr.bays.entity.depo.Depo;

@SuppressWarnings("serial")
@Entity(name = "Defter")
@Data
@EqualsAndHashCode(callSuper = false)
@Table(name = "defter")
@Cache(region = "baysDefterCache", usage = CacheConcurrencyStrategy.READ_WRITE)
public class Defter extends VersionedEntity {

	@Column(name="defter_no", nullable = false)
	private String defter_no;
	@Column(name="ek_no")
	private String ek_no;
	@Column(name="gizlilik", nullable = false)
	private int gizlilik;
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="defter_turu_id", nullable = false)
	private SozlukMadde defter_turu_id;
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="fon_id", nullable = false)
	private Fon fon_id;
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="depo_id")
	private Depo depo_id;
	public String toString() {
		String str = "{";

		str += "ID = [" + id + "], ";
		str += "Version = [" + version + "], ";
		str += "Defter No = [" + defter_no + "], ";
		str += "Ek No = [" + ek_no + "], ";
		str += "Gizlilik = [" + gizlilik + "], ";
		str += "Defter Turu Id = [" + defter_turu_id + "], ";
		str += "Fon Id = [" + fon_id + "], ";
		str += "Depo Id = [" + depo_id + "], ";

		str += "}";

		return str;
	}

}

