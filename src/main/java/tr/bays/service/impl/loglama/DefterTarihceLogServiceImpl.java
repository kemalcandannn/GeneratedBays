package tr.bays.service.impl.loglama;

import java.util.List;
import java.util.Map;

import org.primefaces.model.SortMeta;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import tr.bays.common.QueryResult;
import tr.bays.customdao.loglama.CustomDefterTarihceLogRepository;
import tr.bays.dao.BaseRepository;
import tr.bays.dto.loglama.DefterTarihceLogDto;
import tr.bays.entity.loglama.DefterTarihceLog;
import tr.bays.mapper.DtoMapper;
import tr.bays.service.impl.BaseCrudServiceImpl;
import tr.bays.service.loglama.DefterTarihceLogService;

@SuppressWarnings("serial")
@Service
public class DefterTarihceLogServiceImpl extends BaseCrudServiceImpl<DefterTarihceLogDto, DefterTarihceLog> implements DefterTarihceLogService {

	private CustomDefterTarihceLogRepository customDefterTarihceLogRepository;
	
	public DefterTarihceLogServiceImpl(BaseRepository<DefterTarihceLog> repo, DtoMapper<DefterTarihceLogDto, DefterTarihceLog> dtoMapper, CustomDefterTarihceLogRepository customDefterTarihceLogRepository) {
		super(repo, dtoMapper);

		this.customDefterTarihceLogRepository = customDefterTarihceLogRepository;
	}

	@Override
	@Transactional(readOnly = true)
	public QueryResult<DefterTarihceLogDto> listele(int firstRecord, int pageSize, Map<String, SortMeta> sort, DefterTarihceLogDto sorguKriteri, String sorgu) {
		int pageNumber = firstRecord / pageSize;

		Page<DefterTarihceLog> page = listeleInternal(pageSize, pageNumber, sort, sorguKriteri, sorgu);
		return new QueryResult<DefterTarihceLogDto>(dtoMapper.entityListToDtoList(page.getContent()), (int) page.getTotalElements());
	}

	private Page<DefterTarihceLog> listeleInternal(int pageSize, int pageNumber, Map<String, SortMeta> sort, DefterTarihceLogDto sorguKriteri, String sorgu) {
		return customDefterTarihceLogRepository.sorgula(pageSize, pageNumber, sort, dtoMapper.dtoToEntity(sorguKriteri), sorgu);
	}

	@Override
	public List<DefterTarihceLogDto> combo(String query) {
		return dtoMapper.entityListToDtoList(customDefterTarihceLogRepository.combo(query));
	}

}
