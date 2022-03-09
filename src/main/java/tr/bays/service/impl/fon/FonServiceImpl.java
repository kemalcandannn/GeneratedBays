package tr.bays.service.impl.fon;

import java.util.List;
import java.util.Map;

import org.primefaces.model.SortMeta;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import tr.bays.common.QueryResult;
import tr.bays.customdao.fon.CustomFonRepository;
import tr.bays.dao.BaseRepository;
import tr.bays.dto.fon.FonDto;
import tr.bays.entity.fon.Fon;
import tr.bays.mapper.DtoMapper;
import tr.bays.service.impl.BaseCrudServiceImpl;
import tr.bays.service.fon.FonService;

@SuppressWarnings("serial")
@Service
public class FonServiceImpl extends BaseCrudServiceImpl<FonDto, Fon> implements FonService {

	private CustomFonRepository customFonRepository;
	
	public FonServiceImpl(BaseRepository<Fon> repo, DtoMapper<FonDto, Fon> dtoMapper, CustomFonRepository customFonRepository) {
		super(repo, dtoMapper);

		this.customFonRepository = customFonRepository;
	}

	@Override
	@Transactional(readOnly = true)
	public QueryResult<FonDto> listele(int firstRecord, int pageSize, Map<String, SortMeta> sort, FonDto sorguKriteri, String sorgu) {
		int pageNumber = firstRecord / pageSize;

		Page<Fon> page = listeleInternal(pageSize, pageNumber, sort, sorguKriteri, sorgu);
		return new QueryResult<FonDto>(dtoMapper.entityListToDtoList(page.getContent()), (int) page.getTotalElements());
	}

	private Page<Fon> listeleInternal(int pageSize, int pageNumber, Map<String, SortMeta> sort, FonDto sorguKriteri, String sorgu) {
		return customFonRepository.sorgula(pageSize, pageNumber, sort, dtoMapper.dtoToEntity(sorguKriteri), sorgu);
	}

	@Override
	public List<FonDto> combo(String query) {
		return dtoMapper.entityListToDtoList(customFonRepository.combo(query));
	}

}
