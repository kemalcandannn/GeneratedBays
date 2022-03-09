package tr.bays.service.impl.depo;

import java.util.List;
import java.util.Map;

import org.primefaces.model.SortMeta;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import tr.bays.common.QueryResult;
import tr.bays.customdao.depo.CustomDepoRepository;
import tr.bays.dao.BaseRepository;
import tr.bays.dto.depo.DepoDto;
import tr.bays.entity.depo.Depo;
import tr.bays.mapper.DtoMapper;
import tr.bays.service.impl.BaseCrudServiceImpl;
import tr.bays.service.depo.DepoService;

@SuppressWarnings("serial")
@Service
public class DepoServiceImpl extends BaseCrudServiceImpl<DepoDto, Depo> implements DepoService {

	private CustomDepoRepository customDepoRepository;
	
	public DepoServiceImpl(BaseRepository<Depo> repo, DtoMapper<DepoDto, Depo> dtoMapper, CustomDepoRepository customDepoRepository) {
		super(repo, dtoMapper);

		this.customDepoRepository = customDepoRepository;
	}

	@Override
	@Transactional(readOnly = true)
	public QueryResult<DepoDto> listele(int firstRecord, int pageSize, Map<String, SortMeta> sort, DepoDto sorguKriteri, String sorgu) {
		int pageNumber = firstRecord / pageSize;

		Page<Depo> page = listeleInternal(pageSize, pageNumber, sort, sorguKriteri, sorgu);
		return new QueryResult<DepoDto>(dtoMapper.entityListToDtoList(page.getContent()), (int) page.getTotalElements());
	}

	private Page<Depo> listeleInternal(int pageSize, int pageNumber, Map<String, SortMeta> sort, DepoDto sorguKriteri, String sorgu) {
		return customDepoRepository.sorgula(pageSize, pageNumber, sort, dtoMapper.dtoToEntity(sorguKriteri), sorgu);
	}

	@Override
	public List<DepoDto> combo(String query) {
		return dtoMapper.entityListToDtoList(customDepoRepository.combo(query));
	}

}
