package tr.bays.service.impl.depoTalep;

import java.util.List;
import java.util.Map;

import org.primefaces.model.SortMeta;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import tr.bays.common.QueryResult;
import tr.bays.customdao.depoTalep.CustomDepoTalepMalzemeRepository;
import tr.bays.dao.BaseRepository;
import tr.bays.dto.depoTalep.DepoTalepMalzemeDto;
import tr.bays.entity.depoTalep.DepoTalepMalzeme;
import tr.bays.mapper.DtoMapper;
import tr.bays.service.impl.BaseCrudServiceImpl;
import tr.bays.service.depoTalep.DepoTalepMalzemeService;

@SuppressWarnings("serial")
@Service
public class DepoTalepMalzemeServiceImpl extends BaseCrudServiceImpl<DepoTalepMalzemeDto, DepoTalepMalzeme> implements DepoTalepMalzemeService {

	private CustomDepoTalepMalzemeRepository customDepoTalepMalzemeRepository;
	
	public DepoTalepMalzemeServiceImpl(BaseRepository<DepoTalepMalzeme> repo, DtoMapper<DepoTalepMalzemeDto, DepoTalepMalzeme> dtoMapper, CustomDepoTalepMalzemeRepository customDepoTalepMalzemeRepository) {
		super(repo, dtoMapper);

		this.customDepoTalepMalzemeRepository = customDepoTalepMalzemeRepository;
	}

	@Override
	@Transactional(readOnly = true)
	public QueryResult<DepoTalepMalzemeDto> listele(int firstRecord, int pageSize, Map<String, SortMeta> sort, DepoTalepMalzemeDto sorguKriteri, String sorgu) {
		int pageNumber = firstRecord / pageSize;

		Page<DepoTalepMalzeme> page = listeleInternal(pageSize, pageNumber, sort, sorguKriteri, sorgu);
		return new QueryResult<DepoTalepMalzemeDto>(dtoMapper.entityListToDtoList(page.getContent()), (int) page.getTotalElements());
	}

	private Page<DepoTalepMalzeme> listeleInternal(int pageSize, int pageNumber, Map<String, SortMeta> sort, DepoTalepMalzemeDto sorguKriteri, String sorgu) {
		return customDepoTalepMalzemeRepository.sorgula(pageSize, pageNumber, sort, dtoMapper.dtoToEntity(sorguKriteri), sorgu);
	}

	@Override
	public List<DepoTalepMalzemeDto> combo(String query) {
		return dtoMapper.entityListToDtoList(customDepoTalepMalzemeRepository.combo(query));
	}

}
