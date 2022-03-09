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

import tr.bays.entity.metadata.Alan;
import tr.bays.entity.arsivMateryali.IslenmemisKlasor;

@SuppressWarnings("serial")
@Entity(name = "IslenmemisKlasorAlan")
@Data
@EqualsAndHashCode(callSuper = false)
@Table(name = "islenmemis_klasor_alan")
@Cache(region = "baysIslenmemisKlasorAlanCache", usage = CacheConcurrencyStrategy.READ_WRITE)
public class IslenmemisKlasorAlan extends VersionedEntity {

	@Column(name="deger")
	private String deger;
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="alan_id", nullable = false)
	private Alan alan_id;
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="islenmemis_klasor_id", nullable = false)
	private IslenmemisKlasor islenmemis_klasor_id;
	public String toString() {
		String str = "{";

		str += "ID = [" + id + "], ";
		str += "Version = [" + version + "], ";
		str += "Deger = [" + deger + "], ";
		str += "Alan Id = [" + alan_id + "], ";
		str += "Islenmemis Klasor Id = [" + islenmemis_klasor_id + "], ";

		str += "}";

		return str;
	}

}

