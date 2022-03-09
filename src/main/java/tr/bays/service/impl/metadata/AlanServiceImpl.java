package tr.bays.service.impl.metadata;

import java.util.List;
import java.util.Map;

import org.primefaces.model.SortMeta;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import tr.bays.common.QueryResult;
import tr.bays.customdao.metadata.CustomAlanRepository;
import tr.bays.dao.BaseRepository;
import tr.bays.dto.metadata.AlanDto;
import tr.bays.entity.metadata.Alan;
import tr.bays.mapper.DtoMapper;
import tr.bays.service.impl.BaseCrudServiceImpl;
import tr.bays.service.metadata.AlanService;

@SuppressWarnings("serial")
@Service
public class AlanServiceImpl extends BaseCrudServiceImpl<AlanDto, Alan> implements AlanService {

	private CustomAlanRepository customAlanRepository;
	
	public AlanServiceImpl(BaseRepository<Alan> repo, DtoMapper<AlanDto, Alan> dtoMapper, CustomAlanRepository customAlanRepository) {
		super(repo, dtoMapper);

		this.customAlanRepository = customAlanRepository;
	}

	@Override
	@Transactional(readOnly = true)
	public QueryResult<AlanDto> listele(int firstRecord, int pageSize, Map<String, SortMeta> sort, AlanDto sorguKriteri, String sorgu) {
		int pageNumber = firstRecord / pageSize;

		Page<Alan> page = listeleInternal(pageSize, pageNumber, sort, sorguKriteri, sorgu);
		return new QueryResult<AlanDto>(dtoMapper.entityListToDtoList(page.getContent()), (int) page.getTotalElements());
	}

	private Page<Alan> listeleInternal(int pageSize, int pageNumber, Map<String, SortMeta> sort, AlanDto sorguKriteri, String sorgu) {
		return customAlanRepository.sorgula(pageSize, pageNumber, sort, dtoMapper.dtoToEntity(sorguKriteri), sorgu);
	}

	@Override
	public List<AlanDto> combo(String query) {
		return dtoMapper.entityListToDtoList(customAlanRepository.combo(query));
	}

}
