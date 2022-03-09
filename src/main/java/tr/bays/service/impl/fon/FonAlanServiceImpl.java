package tr.bays.service.impl.fon;

import java.util.List;
import java.util.Map;

import org.primefaces.model.SortMeta;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import tr.bays.common.QueryResult;
import tr.bays.customdao.fon.CustomFonAlanRepository;
import tr.bays.dao.BaseRepository;
import tr.bays.dto.fon.FonAlanDto;
import tr.bays.entity.fon.FonAlan;
import tr.bays.mapper.DtoMapper;
import tr.bays.service.impl.BaseCrudServiceImpl;
import tr.bays.service.fon.FonAlanService;

@SuppressWarnings("serial")
@Service
public class FonAlanServiceImpl extends BaseCrudServiceImpl<FonAlanDto, FonAlan> implements FonAlanService {

	private CustomFonAlanRepository customFonAlanRepository;
	
	public FonAlanServiceImpl(BaseRepository<FonAlan> repo, DtoMapper<FonAlanDto, FonAlan> dtoMapper, CustomFonAlanRepository customFonAlanRepository) {
		super(repo, dtoMapper);

		this.customFonAlanRepository = customFonAlanRepository;
	}

	@Override
	@Transactional(readOnly = true)
	public QueryResult<FonAlanDto> listele(int firstRecord, int pageSize, Map<String, SortMeta> sort, FonAlanDto sorguKriteri, String sorgu) {
		int pageNumber = firstRecord / pageSize;

		Page<FonAlan> page = listeleInternal(pageSize, pageNumber, sort, sorguKriteri, sorgu);
		return new QueryResult<FonAlanDto>(dtoMapper.entityListToDtoList(page.getContent()), (int) page.getTotalElements());
	}

	private Page<FonAlan> listeleInternal(int pageSize, int pageNumber, Map<String, SortMeta> sort, FonAlanDto sorguKriteri, String sorgu) {
		return customFonAlanRepository.sorgula(pageSize, pageNumber, sort, dtoMapper.dtoToEntity(sorguKriteri), sorgu);
	}

	@Override
	public List<FonAlanDto> combo(String query) {
		return dtoMapper.entityListToDtoList(customFonAlanRepository.combo(query));
	}

}
