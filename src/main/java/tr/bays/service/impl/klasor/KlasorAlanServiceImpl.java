package tr.bays.service.impl.klasor;

import java.util.List;
import java.util.Map;

import org.primefaces.model.SortMeta;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import tr.bays.common.QueryResult;
import tr.bays.customdao.klasor.CustomKlasorAlanRepository;
import tr.bays.dao.BaseRepository;
import tr.bays.dto.klasor.KlasorAlanDto;
import tr.bays.entity.klasor.KlasorAlan;
import tr.bays.mapper.DtoMapper;
import tr.bays.service.impl.BaseCrudServiceImpl;
import tr.bays.service.klasor.KlasorAlanService;

@SuppressWarnings("serial")
@Service
public class KlasorAlanServiceImpl extends BaseCrudServiceImpl<KlasorAlanDto, KlasorAlan> implements KlasorAlanService {

	private CustomKlasorAlanRepository customKlasorAlanRepository;
	
	public KlasorAlanServiceImpl(BaseRepository<KlasorAlan> repo, DtoMapper<KlasorAlanDto, KlasorAlan> dtoMapper, CustomKlasorAlanRepository customKlasorAlanRepository) {
		super(repo, dtoMapper);

		this.customKlasorAlanRepository = customKlasorAlanRepository;
	}

	@Override
	@Transactional(readOnly = true)
	public QueryResult<KlasorAlanDto> listele(int firstRecord, int pageSize, Map<String, SortMeta> sort, KlasorAlanDto sorguKriteri, String sorgu) {
		int pageNumber = firstRecord / pageSize;

		Page<KlasorAlan> page = listeleInternal(pageSize, pageNumber, sort, sorguKriteri, sorgu);
		return new QueryResult<KlasorAlanDto>(dtoMapper.entityListToDtoList(page.getContent()), (int) page.getTotalElements());
	}

	private Page<KlasorAlan> listeleInternal(int pageSize, int pageNumber, Map<String, SortMeta> sort, KlasorAlanDto sorguKriteri, String sorgu) {
		return customKlasorAlanRepository.sorgula(pageSize, pageNumber, sort, dtoMapper.dtoToEntity(sorguKriteri), sorgu);
	}

	@Override
	public List<KlasorAlanDto> combo(String query) {
		return dtoMapper.entityListToDtoList(customKlasorAlanRepository.combo(query));
	}

}
