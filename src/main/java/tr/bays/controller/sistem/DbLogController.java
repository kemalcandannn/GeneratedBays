package tr.bays.controller.sistem;

import lombok.Data;
import lombok.EqualsAndHashCode;
import tr.bays.BaysJsfController;
import tr.bays.common.base.BaseCrudController;
import tr.bays.dto.sistem.DbLogDto;
import tr.bays.service.sistem.DbLogService;

@BaysJsfController
@Data
@EqualsAndHashCode(callSuper = false)
public class DbLogController extends BaseCrudController<DbLogDto> {

	private DbLogService dbLogService;

	public DbLogController(DbLogService dbLogService) {
		super(dbLogService);

		this.dbLogService = dbLogService;
	}

	@Override
	protected DbLogDto createNewModel() {
		return new DbLogDto();
	}

}

