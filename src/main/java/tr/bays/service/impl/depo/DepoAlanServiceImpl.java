package tr.bays.service.impl.depo;

import java.util.List;
import java.util.Map;

import org.primefaces.model.SortMeta;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import tr.bays.common.QueryResult;
import tr.bays.customdao.depo.CustomDepoAlanRepository;
import tr.bays.dao.BaseRepository;
import tr.bays.dto.depo.DepoAlanDto;
import tr.bays.entity.depo.DepoAlan;
import tr.bays.mapper.DtoMapper;
import tr.bays.service.impl.BaseCrudServiceImpl;
import tr.bays.service.depo.DepoAlanService;

@SuppressWarnings("serial")
@Service
public class DepoAlanServiceImpl extends BaseCrudServiceImpl<DepoAlanDto, DepoAlan> implements DepoAlanService {

	private CustomDepoAlanRepository customDepoAlanRepository;
	
	public DepoAlanServiceImpl(BaseRepository<DepoAlan> repo, DtoMapper<DepoAlanDto, DepoAlan> dtoMapper, CustomDepoAlanRepository customDepoAlanRepository) {
		super(repo, dtoMapper);

		this.customDepoAlanRepository = customDepoAlanRepository;
	}

	@Override
	@Transactional(readOnly = true)
	public QueryResult<DepoAlanDto> listele(int firstRecord, int pageSize, Map<String, SortMeta> sort, DepoAlanDto sorguKriteri, String sorgu) {
		int pageNumber = firstRecord / pageSize;

		Page<DepoAlan> page = listeleInternal(pageSize, pageNumber, sort, sorguKriteri, sorgu);
		return new QueryResult<DepoAlanDto>(dtoMapper.entityListToDtoList(page.getContent()), (int) page.getTotalElements());
	}

	private Page<DepoAlan> listeleInternal(int pageSize, int pageNumber, Map<String, SortMeta> sort, DepoAlanDto sorguKriteri, String sorgu) {
		return customDepoAlanRepository.sorgula(pageSize, pageNumber, sort, dtoMapper.dtoToEntity(sorguKriteri), sorgu);
	}

	@Override
	public List<DepoAlanDto> combo(String query) {
		return dtoMapper.entityListToDtoList(customDepoAlanRepository.combo(query));
	}

}
