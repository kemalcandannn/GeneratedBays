package tr.bays.service.impl.arsivMateryali;

import java.util.List;
import java.util.Map;

import org.primefaces.model.SortMeta;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import tr.bays.common.QueryResult;
import tr.bays.customdao.arsivMateryali.CustomArsivMateryaliAlanRepository;
import tr.bays.dao.BaseRepository;
import tr.bays.dto.arsivMateryali.ArsivMateryaliAlanDto;
import tr.bays.entity.arsivMateryali.ArsivMateryaliAlan;
import tr.bays.mapper.DtoMapper;
import tr.bays.service.impl.BaseCrudServiceImpl;
import tr.bays.service.arsivMateryali.ArsivMateryaliAlanService;

@SuppressWarnings("serial")
@Service
public class ArsivMateryaliAlanServiceImpl extends BaseCrudServiceImpl<ArsivMateryaliAlanDto, ArsivMateryaliAlan> implements ArsivMateryaliAlanService {

	private CustomArsivMateryaliAlanRepository customArsivMateryaliAlanRepository;
	
	public ArsivMateryaliAlanServiceImpl(BaseRepository<ArsivMateryaliAlan> repo, DtoMapper<ArsivMateryaliAlanDto, ArsivMateryaliAlan> dtoMapper, CustomArsivMateryaliAlanRepository customArsivMateryaliAlanRepository) {
		super(repo, dtoMapper);

		this.customArsivMateryaliAlanRepository = customArsivMateryaliAlanRepository;
	}

	@Override
	@Transactional(readOnly = true)
	public QueryResult<ArsivMateryaliAlanDto> listele(int firstRecord, int pageSize, Map<String, SortMeta> sort, ArsivMateryaliAlanDto sorguKriteri, String sorgu) {
		int pageNumber = firstRecord / pageSize;

		Page<ArsivMateryaliAlan> page = listeleInternal(pageSize, pageNumber, sort, sorguKriteri, sorgu);
		return new QueryResult<ArsivMateryaliAlanDto>(dtoMapper.entityListToDtoList(page.getContent()), (int) page.getTotalElements());
	}

	private Page<ArsivMateryaliAlan> listeleInternal(int pageSize, int pageNumber, Map<String, SortMeta> sort, ArsivMateryaliAlanDto sorguKriteri, String sorgu) {
		return customArsivMateryaliAlanRepository.sorgula(pageSize, pageNumber, sort, dtoMapper.dtoToEntity(sorguKriteri), sorgu);
	}

	@Override
	public List<ArsivMateryaliAlanDto> combo(String query) {
		return dtoMapper.entityListToDtoList(customArsivMateryaliAlanRepository.combo(query));
	}

}
