package tr.bays.service.impl.depoMuhafaza;

import java.util.List;
import java.util.Map;

import org.primefaces.model.SortMeta;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import tr.bays.common.QueryResult;
import tr.bays.customdao.depoMuhafaza.CustomGomlekBakimKlasorRepository;
import tr.bays.dao.BaseRepository;
import tr.bays.dto.depoMuhafaza.GomlekBakimKlasorDto;
import tr.bays.entity.depoMuhafaza.GomlekBakimKlasor;
import tr.bays.mapper.DtoMapper;
import tr.bays.service.impl.BaseCrudServiceImpl;
import tr.bays.service.depoMuhafaza.GomlekBakimKlasorService;

@SuppressWarnings("serial")
@Service
public class GomlekBakimKlasorServiceImpl extends BaseCrudServiceImpl<GomlekBakimKlasorDto, GomlekBakimKlasor> implements GomlekBakimKlasorService {

	private CustomGomlekBakimKlasorRepository customGomlekBakimKlasorRepository;
	
	public GomlekBakimKlasorServiceImpl(BaseRepository<GomlekBakimKlasor> repo, DtoMapper<GomlekBakimKlasorDto, GomlekBakimKlasor> dtoMapper, CustomGomlekBakimKlasorRepository customGomlekBakimKlasorRepository) {
		super(repo, dtoMapper);

		this.customGomlekBakimKlasorRepository = customGomlekBakimKlasorRepository;
	}

	@Override
	@Transactional(readOnly = true)
	public QueryResult<GomlekBakimKlasorDto> listele(int firstRecord, int pageSize, Map<String, SortMeta> sort, GomlekBakimKlasorDto sorguKriteri, String sorgu) {
		int pageNumber = firstRecord / pageSize;

		Page<GomlekBakimKlasor> page = listeleInternal(pageSize, pageNumber, sort, sorguKriteri, sorgu);
		return new QueryResult<GomlekBakimKlasorDto>(dtoMapper.entityListToDtoList(page.getContent()), (int) page.getTotalElements());
	}

	private Page<GomlekBakimKlasor> listeleInternal(int pageSize, int pageNumber, Map<String, SortMeta> sort, GomlekBakimKlasorDto sorguKriteri, String sorgu) {
		return customGomlekBakimKlasorRepository.sorgula(pageSize, pageNumber, sort, dtoMapper.dtoToEntity(sorguKriteri), sorgu);
	}

	@Override
	public List<GomlekBakimKlasorDto> combo(String query) {
		return dtoMapper.entityListToDtoList(customGomlekBakimKlasorRepository.combo(query));
	}

}
