package tr.bays.service.impl.gomlek;

import java.util.List;
import java.util.Map;

import org.primefaces.model.SortMeta;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import tr.bays.common.QueryResult;
import tr.bays.customdao.gomlek.CustomGomlekRepository;
import tr.bays.dao.BaseRepository;
import tr.bays.dto.gomlek.GomlekDto;
import tr.bays.entity.gomlek.Gomlek;
import tr.bays.mapper.DtoMapper;
import tr.bays.service.impl.BaseCrudServiceImpl;
import tr.bays.service.gomlek.GomlekService;

@SuppressWarnings("serial")
@Service
public class GomlekServiceImpl extends BaseCrudServiceImpl<GomlekDto, Gomlek> implements GomlekService {

	private CustomGomlekRepository customGomlekRepository;
	
	public GomlekServiceImpl(BaseRepository<Gomlek> repo, DtoMapper<GomlekDto, Gomlek> dtoMapper, CustomGomlekRepository customGomlekRepository) {
		super(repo, dtoMapper);

		this.customGomlekRepository = customGomlekRepository;
	}

	@Override
	@Transactional(readOnly = true)
	public QueryResult<GomlekDto> listele(int firstRecord, int pageSize, Map<String, SortMeta> sort, GomlekDto sorguKriteri, String sorgu) {
		int pageNumber = firstRecord / pageSize;

		Page<Gomlek> page = listeleInternal(pageSize, pageNumber, sort, sorguKriteri, sorgu);
		return new QueryResult<GomlekDto>(dtoMapper.entityListToDtoList(page.getContent()), (int) page.getTotalElements());
	}

	private Page<Gomlek> listeleInternal(int pageSize, int pageNumber, Map<String, SortMeta> sort, GomlekDto sorguKriteri, String sorgu) {
		return customGomlekRepository.sorgula(pageSize, pageNumber, sort, dtoMapper.dtoToEntity(sorguKriteri), sorgu);
	}

	@Override
	public List<GomlekDto> combo(String query) {
		return dtoMapper.entityListToDtoList(customGomlekRepository.combo(query));
	}

}
