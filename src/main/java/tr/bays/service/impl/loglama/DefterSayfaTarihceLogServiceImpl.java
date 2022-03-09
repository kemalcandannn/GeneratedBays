package tr.bays.service.impl.loglama;

import java.util.List;
import java.util.Map;

import org.primefaces.model.SortMeta;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import tr.bays.common.QueryResult;
import tr.bays.customdao.loglama.CustomDefterSayfaTarihceLogRepository;
import tr.bays.dao.BaseRepository;
import tr.bays.dto.loglama.DefterSayfaTarihceLogDto;
import tr.bays.entity.loglama.DefterSayfaTarihceLog;
import tr.bays.mapper.DtoMapper;
import tr.bays.service.impl.BaseCrudServiceImpl;
import tr.bays.service.loglama.DefterSayfaTarihceLogService;

@SuppressWarnings("serial")
@Service
public class DefterSayfaTarihceLogServiceImpl extends BaseCrudServiceImpl<DefterSayfaTarihceLogDto, DefterSayfaTarihceLog> implements DefterSayfaTarihceLogService {

	private CustomDefterSayfaTarihceLogRepository customDefterSayfaTarihceLogRepository;
	
	public DefterSayfaTarihceLogServiceImpl(BaseRepository<DefterSayfaTarihceLog> repo, DtoMapper<DefterSayfaTarihceLogDto, DefterSayfaTarihceLog> dtoMapper, CustomDefterSayfaTarihceLogRepository customDefterSayfaTarihceLogRepository) {
		super(repo, dtoMapper);

		this.customDefterSayfaTarihceLogRepository = customDefterSayfaTarihceLogRepository;
	}

	@Override
	@Transactional(readOnly = true)
	public QueryResult<DefterSayfaTarihceLogDto> listele(int firstRecord, int pageSize, Map<String, SortMeta> sort, DefterSayfaTarihceLogDto sorguKriteri, String sorgu) {
		int pageNumber = firstRecord / pageSize;

		Page<DefterSayfaTarihceLog> page = listeleInternal(pageSize, pageNumber, sort, sorguKriteri, sorgu);
		return new QueryResult<DefterSayfaTarihceLogDto>(dtoMapper.entityListToDtoList(page.getContent()), (int) page.getTotalElements());
	}

	private Page<DefterSayfaTarihceLog> listeleInternal(int pageSize, int pageNumber, Map<String, SortMeta> sort, DefterSayfaTarihceLogDto sorguKriteri, String sorgu) {
		return customDefterSayfaTarihceLogRepository.sorgula(pageSize, pageNumber, sort, dtoMapper.dtoToEntity(sorguKriteri), sorgu);
	}

	@Override
	public List<DefterSayfaTarihceLogDto> combo(String query) {
		return dtoMapper.entityListToDtoList(customDefterSayfaTarihceLogRepository.combo(query));
	}

}
