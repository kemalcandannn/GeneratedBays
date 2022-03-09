package tr.bays.common.base;

import lombok.Data;

@SuppressWarnings("serial")
@Data
public class VersionedDto extends BaseDto {
	private Long version;
}
