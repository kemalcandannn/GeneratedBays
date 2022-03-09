package tr.bays.service.impl.depoTalep;

import java.util.List;
import java.util.Map;

import org.primefaces.model.SortMeta;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import tr.bays.common.QueryResult;
import tr.bays.customdao.depoTalep.CustomDepoTalepTarihceLogRepository;
import tr.bays.dao.BaseRepository;
import tr.bays.dto.depoTalep.DepoTalepTarihceLogDto;
import tr.bays.entity.depoTalep.DepoTalepTarihceLog;
import tr.bays.mapper.DtoMapper;
import tr.bays.service.impl.BaseCrudServiceImpl;
import tr.bays.service.depoTalep.DepoTalepTarihceLogService;

@SuppressWarnings("serial")
@Service
public class DepoTalepTarihceLogServiceImpl extends BaseCrudServiceImpl<DepoTalepTarihceLogDto, DepoTalepTarihceLog> implements DepoTalepTarihceLogService {

	private CustomDepoTalepTarihceLogRepository customDepoTalepTarihceLogRepository;
	
	public DepoTalepTarihceLogServiceImpl(BaseRepository<DepoTalepTarihceLog> repo, DtoMapper<DepoTalepTarihceLogDto, DepoTalepTarihceLog> dtoMapper, CustomDepoTalepTarihceLogRepository customDepoTalepTarihceLogRepository) {
		super(repo, dtoMapper);

		this.customDepoTalepTarihceLogRepository = customDepoTalepTarihceLogRepository;
	}

	@Override
	@Transactional(readOnly = true)
	public QueryResult<DepoTalepTarihceLogDto> listele(int firstRecord, int pageSize, Map<String, SortMeta> sort, DepoTalepTarihceLogDto sorguKriteri, String sorgu) {
		int pageNumber = firstRecord / pageSize;

		Page<DepoTalepTarihceLog> page = listeleInternal(pageSize, pageNumber, sort, sorguKriteri, sorgu);
		return new QueryResult<DepoTalepTarihceLogDto>(dtoMapper.entityListToDtoList(page.getContent()), (int) page.getTotalElements());
	}

	private Page<DepoTalepTarihceLog> listeleInternal(int pageSize, int pageNumber, Map<String, SortMeta> sort, DepoTalepTarihceLogDto sorguKriteri, String sorgu) {
		return customDepoTalepTarihceLogRepository.sorgula(pageSize, pageNumber, sort, dtoMapper.dtoToEntity(sorguKriteri), sorgu);
	}

	@Override
	public List<DepoTalepTarihceLogDto> combo(String query) {
		return dtoMapper.entityListToDtoList(customDepoTalepTarihceLogRepository.combo(query));
	}

}
