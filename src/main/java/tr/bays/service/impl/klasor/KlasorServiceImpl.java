package tr.bays.service.impl.klasor;

import java.util.List;
import java.util.Map;

import org.primefaces.model.SortMeta;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import tr.bays.common.QueryResult;
import tr.bays.customdao.klasor.CustomKlasorRepository;
import tr.bays.dao.BaseRepository;
import tr.bays.dto.klasor.KlasorDto;
import tr.bays.entity.klasor.Klasor;
import tr.bays.mapper.DtoMapper;
import tr.bays.service.impl.BaseCrudServiceImpl;
import tr.bays.service.klasor.KlasorService;

@SuppressWarnings("serial")
@Service
public class KlasorServiceImpl extends BaseCrudServiceImpl<KlasorDto, Klasor> implements KlasorService {

	private CustomKlasorRepository customKlasorRepository;
	
	public KlasorServiceImpl(BaseRepository<Klasor> repo, DtoMapper<KlasorDto, Klasor> dtoMapper, CustomKlasorRepository customKlasorRepository) {
		super(repo, dtoMapper);

		this.customKlasorRepository = customKlasorRepository;
	}

	@Override
	@Transactional(readOnly = true)
	public QueryResult<KlasorDto> listele(int firstRecord, int pageSize, Map<String, SortMeta> sort, KlasorDto sorguKriteri, String sorgu) {
		int pageNumber = firstRecord / pageSize;

		Page<Klasor> page = listeleInternal(pageSize, pageNumber, sort, sorguKriteri, sorgu);
		return new QueryResult<KlasorDto>(dtoMapper.entityListToDtoList(page.getContent()), (int) page.getTotalElements());
	}

	private Page<Klasor> listeleInternal(int pageSize, int pageNumber, Map<String, SortMeta> sort, KlasorDto sorguKriteri, String sorgu) {
		return customKlasorRepository.sorgula(pageSize, pageNumber, sort, dtoMapper.dtoToEntity(sorguKriteri), sorgu);
	}

	@Override
	public List<KlasorDto> combo(String query) {
		return dtoMapper.entityListToDtoList(customKlasorRepository.combo(query));
	}

}
