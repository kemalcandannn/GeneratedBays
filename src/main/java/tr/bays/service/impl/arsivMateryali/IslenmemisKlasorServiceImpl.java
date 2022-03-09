package tr.bays.service.impl.arsivMateryali;

import java.util.List;
import java.util.Map;

import org.primefaces.model.SortMeta;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import tr.bays.common.QueryResult;
import tr.bays.customdao.arsivMateryali.CustomIslenmemisKlasorRepository;
import tr.bays.dao.BaseRepository;
import tr.bays.dto.arsivMateryali.IslenmemisKlasorDto;
import tr.bays.entity.arsivMateryali.IslenmemisKlasor;
import tr.bays.mapper.DtoMapper;
import tr.bays.service.impl.BaseCrudServiceImpl;
import tr.bays.service.arsivMateryali.IslenmemisKlasorService;

@SuppressWarnings("serial")
@Service
public class IslenmemisKlasorServiceImpl extends BaseCrudServiceImpl<IslenmemisKlasorDto, IslenmemisKlasor> implements IslenmemisKlasorService {

	private CustomIslenmemisKlasorRepository customIslenmemisKlasorRepository;
	
	public IslenmemisKlasorServiceImpl(BaseRepository<IslenmemisKlasor> repo, DtoMapper<IslenmemisKlasorDto, IslenmemisKlasor> dtoMapper, CustomIslenmemisKlasorRepository customIslenmemisKlasorRepository) {
		super(repo, dtoMapper);

		this.customIslenmemisKlasorRepository = customIslenmemisKlasorRepository;
	}

	@Override
	@Transactional(readOnly = true)
	public QueryResult<IslenmemisKlasorDto> listele(int firstRecord, int pageSize, Map<String, SortMeta> sort, IslenmemisKlasorDto sorguKriteri, String sorgu) {
		int pageNumber = firstRecord / pageSize;

		Page<IslenmemisKlasor> page = listeleInternal(pageSize, pageNumber, sort, sorguKriteri, sorgu);
		return new QueryResult<IslenmemisKlasorDto>(dtoMapper.entityListToDtoList(page.getContent()), (int) page.getTotalElements());
	}

	private Page<IslenmemisKlasor> listeleInternal(int pageSize, int pageNumber, Map<String, SortMeta> sort, IslenmemisKlasorDto sorguKriteri, String sorgu) {
		return customIslenmemisKlasorRepository.sorgula(pageSize, pageNumber, sort, dtoMapper.dtoToEntity(sorguKriteri), sorgu);
	}

	@Override
	public List<IslenmemisKlasorDto> combo(String query) {
		return dtoMapper.entityListToDtoList(customIslenmemisKlasorRepository.combo(query));
	}

}
