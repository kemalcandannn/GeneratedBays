package tr.bays.service.impl.gomlek;

import java.util.List;
import java.util.Map;

import org.primefaces.model.SortMeta;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import tr.bays.common.QueryResult;
import tr.bays.customdao.gomlek.CustomGomlekAlanRepository;
import tr.bays.dao.BaseRepository;
import tr.bays.dto.gomlek.GomlekAlanDto;
import tr.bays.entity.gomlek.GomlekAlan;
import tr.bays.mapper.DtoMapper;
import tr.bays.service.impl.BaseCrudServiceImpl;
import tr.bays.service.gomlek.GomlekAlanService;

@SuppressWarnings("serial")
@Service
public class GomlekAlanServiceImpl extends BaseCrudServiceImpl<GomlekAlanDto, GomlekAlan> implements GomlekAlanService {

	private CustomGomlekAlanRepository customGomlekAlanRepository;
	
	public GomlekAlanServiceImpl(BaseRepository<GomlekAlan> repo, DtoMapper<GomlekAlanDto, GomlekAlan> dtoMapper, CustomGomlekAlanRepository customGomlekAlanRepository) {
		super(repo, dtoMapper);

		this.customGomlekAlanRepository = customGomlekAlanRepository;
	}

	@Override
	@Transactional(readOnly = true)
	public QueryResult<GomlekAlanDto> listele(int firstRecord, int pageSize, Map<String, SortMeta> sort, GomlekAlanDto sorguKriteri, String sorgu) {
		int pageNumber = firstRecord / pageSize;

		Page<GomlekAlan> page = listeleInternal(pageSize, pageNumber, sort, sorguKriteri, sorgu);
		return new QueryResult<GomlekAlanDto>(dtoMapper.entityListToDtoList(page.getContent()), (int) page.getTotalElements());
	}

	private Page<GomlekAlan> listeleInternal(int pageSize, int pageNumber, Map<String, SortMeta> sort, GomlekAlanDto sorguKriteri, String sorgu) {
		return customGomlekAlanRepository.sorgula(pageSize, pageNumber, sort, dtoMapper.dtoToEntity(sorguKriteri), sorgu);
	}

	@Override
	public List<GomlekAlanDto> combo(String query) {
		return dtoMapper.entityListToDtoList(customGomlekAlanRepository.combo(query));
	}

}
