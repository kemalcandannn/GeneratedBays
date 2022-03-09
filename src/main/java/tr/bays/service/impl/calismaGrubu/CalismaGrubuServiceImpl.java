package tr.bays.service.impl.calismaGrubu;

import java.util.List;
import java.util.Map;

import org.primefaces.model.SortMeta;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import tr.bays.common.QueryResult;
import tr.bays.customdao.calismaGrubu.CustomCalismaGrubuRepository;
import tr.bays.dao.BaseRepository;
import tr.bays.dto.calismaGrubu.CalismaGrubuDto;
import tr.bays.entity.calismaGrubu.CalismaGrubu;
import tr.bays.mapper.DtoMapper;
import tr.bays.service.impl.BaseCrudServiceImpl;
import tr.bays.service.calismaGrubu.CalismaGrubuService;

@SuppressWarnings("serial")
@Service
public class CalismaGrubuServiceImpl extends BaseCrudServiceImpl<CalismaGrubuDto, CalismaGrubu> implements CalismaGrubuService {

	private CustomCalismaGrubuRepository customCalismaGrubuRepository;
	
	public CalismaGrubuServiceImpl(BaseRepository<CalismaGrubu> repo, DtoMapper<CalismaGrubuDto, CalismaGrubu> dtoMapper, CustomCalismaGrubuRepository customCalismaGrubuRepository) {
		super(repo, dtoMapper);

		this.customCalismaGrubuRepository = customCalismaGrubuRepository;
	}

	@Override
	@Transactional(readOnly = true)
	public QueryResult<CalismaGrubuDto> listele(int firstRecord, int pageSize, Map<String, SortMeta> sort, CalismaGrubuDto sorguKriteri, String sorgu) {
		int pageNumber = firstRecord / pageSize;

		Page<CalismaGrubu> page = listeleInternal(pageSize, pageNumber, sort, sorguKriteri, sorgu);
		return new QueryResult<CalismaGrubuDto>(dtoMapper.entityListToDtoList(page.getContent()), (int) page.getTotalElements());
	}

	private Page<CalismaGrubu> listeleInternal(int pageSize, int pageNumber, Map<String, SortMeta> sort, CalismaGrubuDto sorguKriteri, String sorgu) {
		return customCalismaGrubuRepository.sorgula(pageSize, pageNumber, sort, dtoMapper.dtoToEntity(sorguKriteri), sorgu);
	}

	@Override
	public List<CalismaGrubuDto> combo(String query) {
		return dtoMapper.entityListToDtoList(customCalismaGrubuRepository.combo(query));
	}

}
