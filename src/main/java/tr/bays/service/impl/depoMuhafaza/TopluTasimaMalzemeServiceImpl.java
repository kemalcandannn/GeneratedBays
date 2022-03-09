package tr.bays.service.impl.depoMuhafaza;

import java.util.List;
import java.util.Map;

import org.primefaces.model.SortMeta;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import tr.bays.common.QueryResult;
import tr.bays.customdao.depoMuhafaza.CustomTopluTasimaMalzemeRepository;
import tr.bays.dao.BaseRepository;
import tr.bays.dto.depoMuhafaza.TopluTasimaMalzemeDto;
import tr.bays.entity.depoMuhafaza.TopluTasimaMalzeme;
import tr.bays.mapper.DtoMapper;
import tr.bays.service.impl.BaseCrudServiceImpl;
import tr.bays.service.depoMuhafaza.TopluTasimaMalzemeService;

@SuppressWarnings("serial")
@Service
public class TopluTasimaMalzemeServiceImpl extends BaseCrudServiceImpl<TopluTasimaMalzemeDto, TopluTasimaMalzeme> implements TopluTasimaMalzemeService {

	private CustomTopluTasimaMalzemeRepository customTopluTasimaMalzemeRepository;
	
	public TopluTasimaMalzemeServiceImpl(BaseRepository<TopluTasimaMalzeme> repo, DtoMapper<TopluTasimaMalzemeDto, TopluTasimaMalzeme> dtoMapper, CustomTopluTasimaMalzemeRepository customTopluTasimaMalzemeRepository) {
		super(repo, dtoMapper);

		this.customTopluTasimaMalzemeRepository = customTopluTasimaMalzemeRepository;
	}

	@Override
	@Transactional(readOnly = true)
	public QueryResult<TopluTasimaMalzemeDto> listele(int firstRecord, int pageSize, Map<String, SortMeta> sort, TopluTasimaMalzemeDto sorguKriteri, String sorgu) {
		int pageNumber = firstRecord / pageSize;

		Page<TopluTasimaMalzeme> page = listeleInternal(pageSize, pageNumber, sort, sorguKriteri, sorgu);
		return new QueryResult<TopluTasimaMalzemeDto>(dtoMapper.entityListToDtoList(page.getContent()), (int) page.getTotalElements());
	}

	private Page<TopluTasimaMalzeme> listeleInternal(int pageSize, int pageNumber, Map<String, SortMeta> sort, TopluTasimaMalzemeDto sorguKriteri, String sorgu) {
		return customTopluTasimaMalzemeRepository.sorgula(pageSize, pageNumber, sort, dtoMapper.dtoToEntity(sorguKriteri), sorgu);
	}

	@Override
	public List<TopluTasimaMalzemeDto> combo(String query) {
		return dtoMapper.entityListToDtoList(customTopluTasimaMalzemeRepository.combo(query));
	}

}
