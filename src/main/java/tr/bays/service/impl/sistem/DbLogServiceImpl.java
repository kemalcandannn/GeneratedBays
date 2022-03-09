package tr.bays.service.impl.sistem;

import java.util.List;
import java.util.Map;

import org.primefaces.model.SortMeta;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import tr.bays.common.QueryResult;
import tr.bays.customdao.sistem.CustomDbLogRepository;
import tr.bays.dao.BaseRepository;
import tr.bays.dto.sistem.DbLogDto;
import tr.bays.entity.sistem.DbLog;
import tr.bays.mapper.DtoMapper;
import tr.bays.service.impl.BaseCrudServiceImpl;
import tr.bays.service.sistem.DbLogService;

@SuppressWarnings("serial")
@Service
public class DbLogServiceImpl extends BaseCrudServiceImpl<DbLogDto, DbLog> implements DbLogService {

	private CustomDbLogRepository customDbLogRepository;
	
	public DbLogServiceImpl(BaseRepository<DbLog> repo, DtoMapper<DbLogDto, DbLog> dtoMapper, CustomDbLogRepository customDbLogRepository) {
		super(repo, dtoMapper);

		this.customDbLogRepository = customDbLogRepository;
	}

	@Override
	@Transactional(readOnly = true)
	public QueryResult<DbLogDto> listele(int firstRecord, int pageSize, Map<String, SortMeta> sort, DbLogDto sorguKriteri, String sorgu) {
		int pageNumber = firstRecord / pageSize;

		Page<DbLog> page = listeleInternal(pageSize, pageNumber, sort, sorguKriteri, sorgu);
		return new QueryResult<DbLogDto>(dtoMapper.entityListToDtoList(page.getContent()), (int) page.getTotalElements());
	}

	private Page<DbLog> listeleInternal(int pageSize, int pageNumber, Map<String, SortMeta> sort, DbLogDto sorguKriteri, String sorgu) {
		return customDbLogRepository.sorgula(pageSize, pageNumber, sort, dtoMapper.dtoToEntity(sorguKriteri), sorgu);
	}

	@Override
	public List<DbLogDto> combo(String query) {
		return dtoMapper.entityListToDtoList(customDbLogRepository.combo(query));
	}

}
