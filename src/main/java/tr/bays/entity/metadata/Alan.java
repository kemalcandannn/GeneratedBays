package tr.bays.entity.metadata;

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

import tr.bays.entity.metadata.MetadataSeti;

@SuppressWarnings("serial")
@Entity(name = "Alan")
@Data
@EqualsAndHashCode(callSuper = false)
@Table(name = "alan")
@Cache(region = "baysAlanCache", usage = CacheConcurrencyStrategy.READ_WRITE)
public class Alan extends VersionedEntity {

	@Column(name="ad", nullable = false)
	private String ad;
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="metadata_seti_id", nullable = false)
	private MetadataSeti metadata_seti_id;
	public String toString() {
		String str = "{";

		str += "ID = [" + id + "], ";
		str += "Version = [" + version + "], ";
		str += "Ad = [" + ad + "], ";
		str += "Metadata Seti Id = [" + metadata_seti_id + "], ";

		str += "}";

		return str;
	}

}

