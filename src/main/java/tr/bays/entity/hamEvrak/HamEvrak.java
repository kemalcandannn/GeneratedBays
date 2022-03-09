package tr.bays.entity.hamEvrak;

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
import tr.bays.entity.depo.Depo;

@SuppressWarnings("serial")
@Entity(name = "HamEvrak")
@Data
@EqualsAndHashCode(callSuper = false)
@Table(name = "ham_evrak")
@Cache(region = "baysHamEvrakCache", usage = CacheConcurrencyStrategy.READ_WRITE)
public class HamEvrak extends VersionedEntity {

	@Column(name="saklanma_turu", nullable = false)
	private int saklanma_turu;
	@Column(name="adet", nullable = false)
	private int adet;
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="fon_id", nullable = false)
	private Fon fon_id;
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="bas_depo_id", nullable = false)
	private Depo bas_depo_id;
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="bit_depo_id", nullable = false)
	private Depo bit_depo_id;
	public String toString() {
		String str = "{";

		str += "ID = [" + id + "], ";
		str += "Version = [" + version + "], ";
		str += "Saklanma Turu = [" + saklanma_turu + "], ";
		str += "Adet = [" + adet + "], ";
		str += "Fon Id = [" + fon_id + "], ";
		str += "Bas Depo Id = [" + bas_depo_id + "], ";
		str += "Bit Depo Id = [" + bit_depo_id + "], ";

		str += "}";

		return str;
	}

}

