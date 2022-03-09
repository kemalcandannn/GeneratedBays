package tr.bays.service.impl.loglama;

import java.util.List;
import java.util.Map;

import org.primefaces.model.SortMeta;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import tr.bays.common.QueryResult;
import tr.bays.customdao.loglama.CustomArsivSahibiPersonelOnayLogRepository;
import tr.bays.dao.BaseRepository;
import tr.bays.dto.loglama.ArsivSahibiPersonelOnayLogDto;
import tr.bays.entity.loglama.ArsivSahibiPersonelOnayLog;
import tr.bays.mapper.DtoMapper;
import tr.bays.service.impl.BaseCrudServiceImpl;
import tr.bays.service.loglama.ArsivSahibiPersonelOnayLogService;

@SuppressWarnings("serial")
@Service
public class ArsivSahibiPersonelOnayLogServiceImpl extends BaseCrudServiceImpl<ArsivSahibiPersonelOnayLogDto, ArsivSahibiPersonelOnayLog> implements ArsivSahibiPersonelOnayLogService {

	private CustomArsivSahibiPersonelOnayLogRepository customArsivSahibiPersonelOnayLogRepository;
	
	public ArsivSahibiPersonelOnayLogServiceImpl(BaseRepository<ArsivSahibiPersonelOnayLog> repo, DtoMapper<ArsivSahibiPersonelOnayLogDto, ArsivSahibiPersonelOnayLog> dtoMapper, CustomArsivSahibiPersonelOnayLogRepository customArsivSahibiPersonelOnayLogRepository) {
		super(repo, dtoMapper);

		this.customArsivSahibiPersonelOnayLogRepository = customArsivSahibiPersonelOnayLogRepository;
	}

	@Override
	@Transactional(readOnly = true)
	public QueryResult<ArsivSahibiPersonelOnayLogDto> listele(int firstRecord, int pageSize, Map<String, SortMeta> sort, ArsivSahibiPersonelOnayLogDto sorguKriteri, String sorgu) {
		int pageNumber = firstRecord / pageSize;

		Page<ArsivSahibiPersonelOnayLog> page = listeleInternal(pageSize, pageNumber, sort, sorguKriteri, sorgu);
		return new QueryResult<ArsivSahibiPersonelOnayLogDto>(dtoMapper.entityListToDtoList(page.getContent()), (int) page.getTotalElements());
	}

	private Page<ArsivSahibiPersonelOnayLog> listeleInternal(int pageSize, int pageNumber, Map<String, SortMeta> sort, ArsivSahibiPersonelOnayLogDto sorguKriteri, String sorgu) {
		return customArsivSahibiPersonelOnayLogRepository.sorgula(pageSize, pageNumber, sort, dtoMapper.dtoToEntity(sorguKriteri), sorgu);
	}

	@Override
	public List<ArsivSahibiPersonelOnayLogDto> combo(String query) {
		return dtoMapper.entityListToDtoList(customArsivSahibiPersonelOnayLogRepository.combo(query));
	}

}
