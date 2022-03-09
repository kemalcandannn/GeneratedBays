package tr.bays.entity.loglama;

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

import tr.bays.entity.teskilat.Kullanici;
import tr.bays.entity.teskilat.Kullanici;
import tr.bays.entity.klasor.Klasor;

@SuppressWarnings("serial")
@Entity(name = "KlasorTarihceLog")
@Data
@EqualsAndHashCode(callSuper = false)
@Table(name = "klasor_tarihce_log")
@Cache(region = "baysKlasorTarihceLogCache", usage = CacheConcurrencyStrategy.READ_WRITE)
public class KlasorTarihceLog extends VersionedEntity {

	@Column(name="tarih_saat", nullable = false)
	private Date tarih_saat;
	@Column(name="ip", nullable = false)
	private String ip;
	@Column(name="durum", nullable = false)
	private int durum;
	@Column(name="veri")
	private String veri;
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="kullanici_id", nullable = false)
	private Kullanici kullanici_id;
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="teslim_alan_depo_sorumlusu_id")
	private Kullanici teslim_alan_depo_sorumlusu_id;
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="klasor_id", nullable = false)
	private Klasor klasor_id;
	public String toString() {
		String str = "{";

		str += "ID = [" + id + "], ";
		str += "Version = [" + version + "], ";
		str += "Tarih Saat = [" + tarih_saat + "], ";
		str += "Ip = [" + ip + "], ";
		str += "Durum = [" + durum + "], ";
		str += "Veri = [" + veri + "], ";
		str += "Kullanici Id = [" + kullanici_id + "], ";
		str += "Teslim Alan Depo Sorumlusu Id = [" + teslim_alan_depo_sorumlusu_id + "], ";
		str += "Klasor Id = [" + klasor_id + "], ";

		str += "}";

		return str;
	}

}

