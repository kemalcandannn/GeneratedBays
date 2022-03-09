package tr.bays.service.impl.arsivMateryali;

import java.util.List;
import java.util.Map;

import org.primefaces.model.SortMeta;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import tr.bays.common.QueryResult;
import tr.bays.customdao.arsivMateryali.CustomIslenmemisKlasorAlanRepository;
import tr.bays.dao.BaseRepository;
import tr.bays.dto.arsivMateryali.IslenmemisKlasorAlanDto;
import tr.bays.entity.arsivMateryali.IslenmemisKlasorAlan;
import tr.bays.mapper.DtoMapper;
import tr.bays.service.impl.BaseCrudServiceImpl;
import tr.bays.service.arsivMateryali.IslenmemisKlasorAlanService;

@SuppressWarnings("serial")
@Service
public class IslenmemisKlasorAlanServiceImpl extends BaseCrudServiceImpl<IslenmemisKlasorAlanDto, IslenmemisKlasorAlan> implements IslenmemisKlasorAlanService {

	private CustomIslenmemisKlasorAlanRepository customIslenmemisKlasorAlanRepository;
	
	public IslenmemisKlasorAlanServiceImpl(BaseRepository<IslenmemisKlasorAlan> repo, DtoMapper<IslenmemisKlasorAlanDto, IslenmemisKlasorAlan> dtoMapper, CustomIslenmemisKlasorAlanRepository customIslenmemisKlasorAlanRepository) {
		super(repo, dtoMapper);

		this.customIslenmemisKlasorAlanRepository = customIslenmemisKlasorAlanRepository;
	}

	@Override
	@Transactional(readOnly = true)
	public QueryResult<IslenmemisKlasorAlanDto> listele(int firstRecord, int pageSize, Map<String, SortMeta> sort, IslenmemisKlasorAlanDto sorguKriteri, String sorgu) {
		int pageNumber = firstRecord / pageSize;

		Page<IslenmemisKlasorAlan> page = listeleInternal(pageSize, pageNumber, sort, sorguKriteri, sorgu);
		return new QueryResult<IslenmemisKlasorAlanDto>(dtoMapper.entityListToDtoList(page.getContent()), (int) page.getTotalElements());
	}

	private Page<IslenmemisKlasorAlan> listeleInternal(int pageSize, int pageNumber, Map<String, SortMeta> sort, IslenmemisKlasorAlanDto sorguKriteri, String sorgu) {
		return customIslenmemisKlasorAlanRepository.sorgula(pageSize, pageNumber, sort, dtoMapper.dtoToEntity(sorguKriteri), sorgu);
	}

	@Override
	public List<IslenmemisKlasorAlanDto> combo(String query) {
		return dtoMapper.entityListToDtoList(customIslenmemisKlasorAlanRepository.combo(query));
	}

}
