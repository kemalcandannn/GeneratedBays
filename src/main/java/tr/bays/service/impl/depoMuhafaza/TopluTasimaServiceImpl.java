package tr.bays.service.impl.depoMuhafaza;

import java.util.List;
import java.util.Map;

import org.primefaces.model.SortMeta;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import tr.bays.common.QueryResult;
import tr.bays.customdao.depoMuhafaza.CustomTopluTasimaRepository;
import tr.bays.dao.BaseRepository;
import tr.bays.dto.depoMuhafaza.TopluTasimaDto;
import tr.bays.entity.depoMuhafaza.TopluTasima;
import tr.bays.mapper.DtoMapper;
import tr.bays.service.impl.BaseCrudServiceImpl;
import tr.bays.service.depoMuhafaza.TopluTasimaService;

@SuppressWarnings("serial")
@Service
public class TopluTasimaServiceImpl extends BaseCrudServiceImpl<TopluTasimaDto, TopluTasima> implements TopluTasimaService {

	private CustomTopluTasimaRepository customTopluTasimaRepository;
	
	public TopluTasimaServiceImpl(BaseRepository<TopluTasima> repo, DtoMapper<TopluTasimaDto, TopluTasima> dtoMapper, CustomTopluTasimaRepository customTopluTasimaRepository) {
		super(repo, dtoMapper);

		this.customTopluTasimaRepository = customTopluTasimaRepository;
	}

	@Override
	@Transactional(readOnly = true)
	public QueryResult<TopluTasimaDto> listele(int firstRecord, int pageSize, Map<String, SortMeta> sort, TopluTasimaDto sorguKriteri, String sorgu) {
		int pageNumber = firstRecord / pageSize;

		Page<TopluTasima> page = listeleInternal(pageSize, pageNumber, sort, sorguKriteri, sorgu);
		return new QueryResult<TopluTasimaDto>(dtoMapper.entityListToDtoList(page.getContent()), (int) page.getTotalElements());
	}

	private Page<TopluTasima> listeleInternal(int pageSize, int pageNumber, Map<String, SortMeta> sort, TopluTasimaDto sorguKriteri, String sorgu) {
		return customTopluTasimaRepository.sorgula(pageSize, pageNumber, sort, dtoMapper.dtoToEntity(sorguKriteri), sorgu);
	}

	@Override
	public List<TopluTasimaDto> combo(String query) {
		return dtoMapper.entityListToDtoList(customTopluTasimaRepository.combo(query));
	}

}
