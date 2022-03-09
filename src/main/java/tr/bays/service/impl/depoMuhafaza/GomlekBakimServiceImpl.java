package tr.bays.service.impl.depoMuhafaza;

import java.util.List;
import java.util.Map;

import org.primefaces.model.SortMeta;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import tr.bays.common.QueryResult;
import tr.bays.customdao.depoMuhafaza.CustomGomlekBakimRepository;
import tr.bays.dao.BaseRepository;
import tr.bays.dto.depoMuhafaza.GomlekBakimDto;
import tr.bays.entity.depoMuhafaza.GomlekBakim;
import tr.bays.mapper.DtoMapper;
import tr.bays.service.impl.BaseCrudServiceImpl;
import tr.bays.service.depoMuhafaza.GomlekBakimService;

@SuppressWarnings("serial")
@Service
public class GomlekBakimServiceImpl extends BaseCrudServiceImpl<GomlekBakimDto, GomlekBakim> implements GomlekBakimService {

	private CustomGomlekBakimRepository customGomlekBakimRepository;
	
	public GomlekBakimServiceImpl(BaseRepository<GomlekBakim> repo, DtoMapper<GomlekBakimDto, GomlekBakim> dtoMapper, CustomGomlekBakimRepository customGomlekBakimRepository) {
		super(repo, dtoMapper);

		this.customGomlekBakimRepository = customGomlekBakimRepository;
	}

	@Override
	@Transactional(readOnly = true)
	public QueryResult<GomlekBakimDto> listele(int firstRecord, int pageSize, Map<String, SortMeta> sort, GomlekBakimDto sorguKriteri, String sorgu) {
		int pageNumber = firstRecord / pageSize;

		Page<GomlekBakim> page = listeleInternal(pageSize, pageNumber, sort, sorguKriteri, sorgu);
		return new QueryResult<GomlekBakimDto>(dtoMapper.entityListToDtoList(page.getContent()), (int) page.getTotalElements());
	}

	private Page<GomlekBakim> listeleInternal(int pageSize, int pageNumber, Map<String, SortMeta> sort, GomlekBakimDto sorguKriteri, String sorgu) {
		return customGomlekBakimRepository.sorgula(pageSize, pageNumber, sort, dtoMapper.dtoToEntity(sorguKriteri), sorgu);
	}

	@Override
	public List<GomlekBakimDto> combo(String query) {
		return dtoMapper.entityListToDtoList(customGomlekBakimRepository.combo(query));
	}

}
