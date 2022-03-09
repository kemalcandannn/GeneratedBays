package tr.bays.service.impl.defter;

import java.util.List;
import java.util.Map;

import org.primefaces.model.SortMeta;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import tr.bays.common.QueryResult;
import tr.bays.customdao.defter.CustomDefterSayfaAlanRepository;
import tr.bays.dao.BaseRepository;
import tr.bays.dto.defter.DefterSayfaAlanDto;
import tr.bays.entity.defter.DefterSayfaAlan;
import tr.bays.mapper.DtoMapper;
import tr.bays.service.impl.BaseCrudServiceImpl;
import tr.bays.service.defter.DefterSayfaAlanService;

@SuppressWarnings("serial")
@Service
public class DefterSayfaAlanServiceImpl extends BaseCrudServiceImpl<DefterSayfaAlanDto, DefterSayfaAlan> implements DefterSayfaAlanService {

	private CustomDefterSayfaAlanRepository customDefterSayfaAlanRepository;
	
	public DefterSayfaAlanServiceImpl(BaseRepository<DefterSayfaAlan> repo, DtoMapper<DefterSayfaAlanDto, DefterSayfaAlan> dtoMapper, CustomDefterSayfaAlanRepository customDefterSayfaAlanRepository) {
		super(repo, dtoMapper);

		this.customDefterSayfaAlanRepository = customDefterSayfaAlanRepository;
	}

	@Override
	@Transactional(readOnly = true)
	public QueryResult<DefterSayfaAlanDto> listele(int firstRecord, int pageSize, Map<String, SortMeta> sort, DefterSayfaAlanDto sorguKriteri, String sorgu) {
		int pageNumber = firstRecord / pageSize;

		Page<DefterSayfaAlan> page = listeleInternal(pageSize, pageNumber, sort, sorguKriteri, sorgu);
		return new QueryResult<DefterSayfaAlanDto>(dtoMapper.entityListToDtoList(page.getContent()), (int) page.getTotalElements());
	}

	private Page<DefterSayfaAlan> listeleInternal(int pageSize, int pageNumber, Map<String, SortMeta> sort, DefterSayfaAlanDto sorguKriteri, String sorgu) {
		return customDefterSayfaAlanRepository.sorgula(pageSize, pageNumber, sort, dtoMapper.dtoToEntity(sorguKriteri), sorgu);
	}

	@Override
	public List<DefterSayfaAlanDto> combo(String query) {
		return dtoMapper.entityListToDtoList(customDefterSayfaAlanRepository.combo(query));
	}

}
