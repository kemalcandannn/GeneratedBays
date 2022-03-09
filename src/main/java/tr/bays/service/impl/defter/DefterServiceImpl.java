package tr.bays.service.impl.defter;

import java.util.List;
import java.util.Map;

import org.primefaces.model.SortMeta;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import tr.bays.common.QueryResult;
import tr.bays.customdao.defter.CustomDefterRepository;
import tr.bays.dao.BaseRepository;
import tr.bays.dto.defter.DefterDto;
import tr.bays.entity.defter.Defter;
import tr.bays.mapper.DtoMapper;
import tr.bays.service.impl.BaseCrudServiceImpl;
import tr.bays.service.defter.DefterService;

@SuppressWarnings("serial")
@Service
public class DefterServiceImpl extends BaseCrudServiceImpl<DefterDto, Defter> implements DefterService {

	private CustomDefterRepository customDefterRepository;
	
	public DefterServiceImpl(BaseRepository<Defter> repo, DtoMapper<DefterDto, Defter> dtoMapper, CustomDefterRepository customDefterRepository) {
		super(repo, dtoMapper);

		this.customDefterRepository = customDefterRepository;
	}

	@Override
	@Transactional(readOnly = true)
	public QueryResult<DefterDto> listele(int firstRecord, int pageSize, Map<String, SortMeta> sort, DefterDto sorguKriteri, String sorgu) {
		int pageNumber = firstRecord / pageSize;

		Page<Defter> page = listeleInternal(pageSize, pageNumber, sort, sorguKriteri, sorgu);
		return new QueryResult<DefterDto>(dtoMapper.entityListToDtoList(page.getContent()), (int) page.getTotalElements());
	}

	private Page<Defter> listeleInternal(int pageSize, int pageNumber, Map<String, SortMeta> sort, DefterDto sorguKriteri, String sorgu) {
		return customDefterRepository.sorgula(pageSize, pageNumber, sort, dtoMapper.dtoToEntity(sorguKriteri), sorgu);
	}

	@Override
	public List<DefterDto> combo(String query) {
		return dtoMapper.entityListToDtoList(customDefterRepository.combo(query));
	}

}
