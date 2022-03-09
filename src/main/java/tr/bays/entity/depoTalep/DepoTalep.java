package tr.bays.entity.depoTalep;

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
import tr.bays.entity.teskilat.Kullanici;
import tr.bays.entity.depo.Depo;

@SuppressWarnings("serial")
@Entity(name = "DepoTalep")
@Data
@EqualsAndHashCode(callSuper = false)
@Table(name = "depo_talep")
@Cache(region = "baysDepoTalepCache", usage = CacheConcurrencyStrategy.READ_WRITE)
public class DepoTalep extends VersionedEntity {

	@Column(name="tarih_saat", nullable = false)
	private Date tarih_saat;
	@Column(name="durum", nullable = false)
	private int durum;
	@Column(name="ip", nullable = false)
	private String ip;
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="talep_eden_birim_id", nullable = false)
	private Birim talep_eden_birim_id;
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="talep_eden_kullanici_id", nullable = false)
	private Kullanici talep_eden_kullanici_id;
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="teslim_alan_id")
	private Kullanici teslim_alan_id;
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="depo_gorevlisi_id")
	private Kullanici depo_gorevlisi_id;
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="depo_id", nullable = false)
	private Depo depo_id;
	public String toString() {
		String str = "{";

		str += "ID = [" + id + "], ";
		str += "Version = [" + version + "], ";
		str += "Tarih Saat = [" + tarih_saat + "], ";
		str += "Durum = [" + durum + "], ";
		str += "Ip = [" + ip + "], ";
		str += "Talep Eden Birim Id = [" + talep_eden_birim_id + "], ";
		str += "Talep Eden Kullanici Id = [" + talep_eden_kullanici_id + "], ";
		str += "Teslim Alan Id = [" + teslim_alan_id + "], ";
		str += "Depo Gorevlisi Id = [" + depo_gorevlisi_id + "], ";
		str += "Depo Id = [" + depo_id + "], ";

		str += "}";

		return str;
	}

}

