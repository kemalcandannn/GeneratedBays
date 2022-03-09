package tr.bays.service.impl.loglama;

import java.util.List;
import java.util.Map;

import org.primefaces.model.SortMeta;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import tr.bays.common.QueryResult;
import tr.bays.customdao.loglama.CustomGomlekTarihceLogRepository;
import tr.bays.dao.BaseRepository;
import tr.bays.dto.loglama.GomlekTarihceLogDto;
import tr.bays.entity.loglama.GomlekTarihceLog;
import tr.bays.mapper.DtoMapper;
import tr.bays.service.impl.BaseCrudServiceImpl;
import tr.bays.service.loglama.GomlekTarihceLogService;

@SuppressWarnings("serial")
@Service
public class GomlekTarihceLogServiceImpl extends BaseCrudServiceImpl<GomlekTarihceLogDto, GomlekTarihceLog> implements GomlekTarihceLogService {

	private CustomGomlekTarihceLogRepository customGomlekTarihceLogRepository;
	
	public GomlekTarihceLogServiceImpl(BaseRepository<GomlekTarihceLog> repo, DtoMapper<GomlekTarihceLogDto, GomlekTarihceLog> dtoMapper, CustomGomlekTarihceLogRepository customGomlekTarihceLogRepository) {
		super(repo, dtoMapper);

		this.customGomlekTarihceLogRepository = customGomlekTarihceLogRepository;
	}

	@Override
	@Transactional(readOnly = true)
	public QueryResult<GomlekTarihceLogDto> listele(int firstRecord, int pageSize, Map<String, SortMeta> sort, GomlekTarihceLogDto sorguKriteri, String sorgu) {
		int pageNumber = firstRecord / pageSize;

		Page<GomlekTarihceLog> page = listeleInternal(pageSize, pageNumber, sort, sorguKriteri, sorgu);
		return new QueryResult<GomlekTarihceLogDto>(dtoMapper.entityListToDtoList(page.getContent()), (int) page.getTotalElements());
	}

	private Page<GomlekTarihceLog> listeleInternal(int pageSize, int pageNumber, Map<String, SortMeta> sort, GomlekTarihceLogDto sorguKriteri, String sorgu) {
		return customGomlekTarihceLogRepository.sorgula(pageSize, pageNumber, sort, dtoMapper.dtoToEntity(sorguKriteri), sorgu);
	}

	@Override
	public List<GomlekTarihceLogDto> combo(String query) {
		return dtoMapper.entityListToDtoList(customGomlekTarihceLogRepository.combo(query));
	}

}
