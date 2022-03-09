package tr.bays.entity;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.persistence.Version;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@SuppressWarnings("serial")
@Data
@EqualsAndHashCode(callSuper = true)
@MappedSuperclass
@ToString
public class VersionedEntity extends BaseEntity {

	@Version
	@Column(name = "VERSION")
	protected Long version;

}
