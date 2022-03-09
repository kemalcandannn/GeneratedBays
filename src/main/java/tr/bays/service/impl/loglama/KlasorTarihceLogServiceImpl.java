package tr.bays.service.impl.loglama;

import java.util.List;
import java.util.Map;

import org.primefaces.model.SortMeta;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import tr.bays.common.QueryResult;
import tr.bays.customdao.loglama.CustomKlasorTarihceLogRepository;
import tr.bays.dao.BaseRepository;
import tr.bays.dto.loglama.KlasorTarihceLogDto;
import tr.bays.entity.loglama.KlasorTarihceLog;
import tr.bays.mapper.DtoMapper;
import tr.bays.service.impl.BaseCrudServiceImpl;
import tr.bays.service.loglama.KlasorTarihceLogService;

@SuppressWarnings("serial")
@Service
public class KlasorTarihceLogServiceImpl extends BaseCrudServiceImpl<KlasorTarihceLogDto, KlasorTarihceLog> implements KlasorTarihceLogService {

	private CustomKlasorTarihceLogRepository customKlasorTarihceLogRepository;
	
	public KlasorTarihceLogServiceImpl(BaseRepository<KlasorTarihceLog> repo, DtoMapper<KlasorTarihceLogDto, KlasorTarihceLog> dtoMapper, CustomKlasorTarihceLogRepository customKlasorTarihceLogRepository) {
		super(repo, dtoMapper);

		this.customKlasorTarihceLogRepository = customKlasorTarihceLogRepository;
	}

	@Override
	@Transactional(readOnly = true)
	public QueryResult<KlasorTarihceLogDto> listele(int firstRecord, int pageSize, Map<String, SortMeta> sort, KlasorTarihceLogDto sorguKriteri, String sorgu) {
		int pageNumber = firstRecord / pageSize;

		Page<KlasorTarihceLog> page = listeleInternal(pageSize, pageNumber, sort, sorguKriteri, sorgu);
		return new QueryResult<KlasorTarihceLogDto>(dtoMapper.entityListToDtoList(page.getContent()), (int) page.getTotalElements());
	}

	private Page<KlasorTarihceLog> listeleInternal(int pageSize, int pageNumber, Map<String, SortMeta> sort, KlasorTarihceLogDto sorguKriteri, String sorgu) {
		return customKlasorTarihceLogRepository.sorgula(pageSize, pageNumber, sort, dtoMapper.dtoToEntity(sorguKriteri), sorgu);
	}

	@Override
	public List<KlasorTarihceLogDto> combo(String query) {
		return dtoMapper.entityListToDtoList(customKlasorTarihceLogRepository.combo(query));
	}

}
