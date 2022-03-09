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

import tr.bays.entity.teskilat.Birim;
import tr.bays.entity.teskilat.Kullanici;
import tr.bays.entity.teskilat.Kullanici;
import tr.bays.entity.fon.Fon;
import tr.bays.entity.hamEvrak.HamEvrak;

@SuppressWarnings("serial")
@Entity(name = "HamEvrakBirim")
@Data
@EqualsAndHashCode(callSuper = false)
@Table(name = "ham_evrak_birim")
@Cache(region = "baysHamEvrakBirimCache", usage = CacheConcurrencyStrategy.READ_WRITE)
public class HamEvrakBirim extends VersionedEntity {

	@Column(name="yon", nullable = false)
	private int yon;
	@Column(name="adet", nullable = false)
	private int adet;
	@Column(name="saklanma_turu", nullable = false)
	private int saklanma_turu;
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="birim_id", nullable = false)
	private Birim birim_id;
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="depo_sorumlusu_id", nullable = false)
	private Kullanici depo_sorumlusu_id;
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="birim_sorumlusu_id", nullable = false)
	private Kullanici birim_sorumlusu_id;
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="fon_id", nullable = false)
	private Fon fon_id;
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="ham_evrak_id")
	private HamEvrak ham_evrak_id;
	public String toString() {
		String str = "{";

		str += "ID = [" + id + "], ";
		str += "Version = [" + version + "], ";
		str += "Yon = [" + yon + "], ";
		str += "Adet = [" + adet + "], ";
		str += "Saklanma Turu = [" + saklanma_turu + "], ";
		str += "Birim Id = [" + birim_id + "], ";
		str += "Depo Sorumlusu Id = [" + depo_sorumlusu_id + "], ";
		str += "Birim Sorumlusu Id = [" + birim_sorumlusu_id + "], ";
		str += "Fon Id = [" + fon_id + "], ";
		str += "Ham Evrak Id = [" + ham_evrak_id + "], ";

		str += "}";

		return str;
	}

}

