package tr.bays.service.impl.defter;

import java.util.List;
import java.util.Map;

import org.primefaces.model.SortMeta;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import tr.bays.common.QueryResult;
import tr.bays.customdao.defter.CustomDefterAlanRepository;
import tr.bays.dao.BaseRepository;
import tr.bays.dto.defter.DefterAlanDto;
import tr.bays.entity.defter.DefterAlan;
import tr.bays.mapper.DtoMapper;
import tr.bays.service.impl.BaseCrudServiceImpl;
import tr.bays.service.defter.DefterAlanService;

@SuppressWarnings("serial")
@Service
public class DefterAlanServiceImpl extends BaseCrudServiceImpl<DefterAlanDto, DefterAlan> implements DefterAlanService {

	private CustomDefterAlanRepository customDefterAlanRepository;
	
	public DefterAlanServiceImpl(BaseRepository<DefterAlan> repo, DtoMapper<DefterAlanDto, DefterAlan> dtoMapper, CustomDefterAlanRepository customDefterAlanRepository) {
		super(repo, dtoMapper);

		this.customDefterAlanRepository = customDefterAlanRepository;
	}

	@Override
	@Transactional(readOnly = true)
	public QueryResult<DefterAlanDto> listele(int firstRecord, int pageSize, Map<String, SortMeta> sort, DefterAlanDto sorguKriteri, String sorgu) {
		int pageNumber = firstRecord / pageSize;

		Page<DefterAlan> page = listeleInternal(pageSize, pageNumber, sort, sorguKriteri, sorgu);
		return new QueryResult<DefterAlanDto>(dtoMapper.entityListToDtoList(page.getContent()), (int) page.getTotalElements());
	}

	private Page<DefterAlan> listeleInternal(int pageSize, int pageNumber, Map<String, SortMeta> sort, DefterAlanDto sorguKriteri, String sorgu) {
		return customDefterAlanRepository.sorgula(pageSize, pageNumber, sort, dtoMapper.dtoToEntity(sorguKriteri), sorgu);
	}

	@Override
	public List<DefterAlanDto> combo(String query) {
		return dtoMapper.entityListToDtoList(customDefterAlanRepository.combo(query));
	}

}
