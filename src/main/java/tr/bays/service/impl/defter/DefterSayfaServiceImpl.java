package tr.bays.service.impl.defter;

import java.util.List;
import java.util.Map;

import org.primefaces.model.SortMeta;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import tr.bays.common.QueryResult;
import tr.bays.customdao.defter.CustomDefterSayfaRepository;
import tr.bays.dao.BaseRepository;
import tr.bays.dto.defter.DefterSayfaDto;
import tr.bays.entity.defter.DefterSayfa;
import tr.bays.mapper.DtoMapper;
import tr.bays.service.impl.BaseCrudServiceImpl;
import tr.bays.service.defter.DefterSayfaService;

@SuppressWarnings("serial")
@Service
public class DefterSayfaServiceImpl extends BaseCrudServiceImpl<DefterSayfaDto, DefterSayfa> implements DefterSayfaService {

	private CustomDefterSayfaRepository customDefterSayfaRepository;
	
	public DefterSayfaServiceImpl(BaseRepository<DefterSayfa> repo, DtoMapper<DefterSayfaDto, DefterSayfa> dtoMapper, CustomDefterSayfaRepository customDefterSayfaRepository) {
		super(repo, dtoMapper);

		this.customDefterSayfaRepository = customDefterSayfaRepository;
	}

	@Override
	@Transactional(readOnly = true)
	public QueryResult<DefterSayfaDto> listele(int firstRecord, int pageSize, Map<String, SortMeta> sort, DefterSayfaDto sorguKriteri, String sorgu) {
		int pageNumber = firstRecord / pageSize;

		Page<DefterSayfa> page = listeleInternal(pageSize, pageNumber, sort, sorguKriteri, sorgu);
		return new QueryResult<DefterSayfaDto>(dtoMapper.entityListToDtoList(page.getContent()), (int) page.getTotalElements());
	}

	private Page<DefterSayfa> listeleInternal(int pageSize, int pageNumber, Map<String, SortMeta> sort, DefterSayfaDto sorguKriteri, String sorgu) {
		return customDefterSayfaRepository.sorgula(pageSize, pageNumber, sort, dtoMapper.dtoToEntity(sorguKriteri), sorgu);
	}

	@Override
	public List<DefterSayfaDto> combo(String query) {
		return dtoMapper.entityListToDtoList(customDefterSayfaRepository.combo(query));
	}

}
