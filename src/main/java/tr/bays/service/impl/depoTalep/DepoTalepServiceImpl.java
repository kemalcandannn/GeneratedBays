package tr.bays.service.impl.depoTalep;

import java.util.List;
import java.util.Map;

import org.primefaces.model.SortMeta;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import tr.bays.common.QueryResult;
import tr.bays.customdao.depoTalep.CustomDepoTalepRepository;
import tr.bays.dao.BaseRepository;
import tr.bays.dto.depoTalep.DepoTalepDto;
import tr.bays.entity.depoTalep.DepoTalep;
import tr.bays.mapper.DtoMapper;
import tr.bays.service.impl.BaseCrudServiceImpl;
import tr.bays.service.depoTalep.DepoTalepService;

@SuppressWarnings("serial")
@Service
public class DepoTalepServiceImpl extends BaseCrudServiceImpl<DepoTalepDto, DepoTalep> implements DepoTalepService {

	private CustomDepoTalepRepository customDepoTalepRepository;
	
	public DepoTalepServiceImpl(BaseRepository<DepoTalep> repo, DtoMapper<DepoTalepDto, DepoTalep> dtoMapper, CustomDepoTalepRepository customDepoTalepRepository) {
		super(repo, dtoMapper);

		this.customDepoTalepRepository = customDepoTalepRepository;
	}

	@Override
	@Transactional(readOnly = true)
	public QueryResult<DepoTalepDto> listele(int firstRecord, int pageSize, Map<String, SortMeta> sort, DepoTalepDto sorguKriteri, String sorgu) {
		int pageNumber = firstRecord / pageSize;

		Page<DepoTalep> page = listeleInternal(pageSize, pageNumber, sort, sorguKriteri, sorgu);
		return new QueryResult<DepoTalepDto>(dtoMapper.entityListToDtoList(page.getContent()), (int) page.getTotalElements());
	}

	private Page<DepoTalep> listeleInternal(int pageSize, int pageNumber, Map<String, SortMeta> sort, DepoTalepDto sorguKriteri, String sorgu) {
		return customDepoTalepRepository.sorgula(pageSize, pageNumber, sort, dtoMapper.dtoToEntity(sorguKriteri), sorgu);
	}

	@Override
	public List<DepoTalepDto> combo(String query) {
		return dtoMapper.entityListToDtoList(customDepoTalepRepository.combo(query));
	}

}
