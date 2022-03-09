package tr.bays.service.impl.arsivMateryali;

import java.util.List;
import java.util.Map;

import org.primefaces.model.SortMeta;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import tr.bays.common.QueryResult;
import tr.bays.customdao.arsivMateryali.CustomArsivMateryaliRepository;
import tr.bays.dao.BaseRepository;
import tr.bays.dto.arsivMateryali.ArsivMateryaliDto;
import tr.bays.entity.arsivMateryali.ArsivMateryali;
import tr.bays.mapper.DtoMapper;
import tr.bays.service.impl.BaseCrudServiceImpl;
import tr.bays.service.arsivMateryali.ArsivMateryaliService;

@SuppressWarnings("serial")
@Service
public class ArsivMateryaliServiceImpl extends BaseCrudServiceImpl<ArsivMateryaliDto, ArsivMateryali> implements ArsivMateryaliService {

	private CustomArsivMateryaliRepository customArsivMateryaliRepository;
	
	public ArsivMateryaliServiceImpl(BaseRepository<ArsivMateryali> repo, DtoMapper<ArsivMateryaliDto, ArsivMateryali> dtoMapper, CustomArsivMateryaliRepository customArsivMateryaliRepository) {
		super(repo, dtoMapper);

		this.customArsivMateryaliRepository = customArsivMateryaliRepository;
	}

	@Override
	@Transactional(readOnly = true)
	public QueryResult<ArsivMateryaliDto> listele(int firstRecord, int pageSize, Map<String, SortMeta> sort, ArsivMateryaliDto sorguKriteri, String sorgu) {
		int pageNumber = firstRecord / pageSize;

		Page<ArsivMateryali> page = listeleInternal(pageSize, pageNumber, sort, sorguKriteri, sorgu);
		return new QueryResult<ArsivMateryaliDto>(dtoMapper.entityListToDtoList(page.getContent()), (int) page.getTotalElements());
	}

	private Page<ArsivMateryali> listeleInternal(int pageSize, int pageNumber, Map<String, SortMeta> sort, ArsivMateryaliDto sorguKriteri, String sorgu) {
		return customArsivMateryaliRepository.sorgula(pageSize, pageNumber, sort, dtoMapper.dtoToEntity(sorguKriteri), sorgu);
	}

	@Override
	public List<ArsivMateryaliDto> combo(String query) {
		return dtoMapper.entityListToDtoList(customArsivMateryaliRepository.combo(query));
	}

}
