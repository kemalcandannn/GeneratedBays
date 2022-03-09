package tr.bays.service.impl.fon;

import java.util.List;
import java.util.Map;

import org.primefaces.model.SortMeta;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import tr.bays.common.QueryResult;
import tr.bays.customdao.fon.CustomFonOnculVeriLogRepository;
import tr.bays.dao.BaseRepository;
import tr.bays.dto.fon.FonOnculVeriLogDto;
import tr.bays.entity.fon.FonOnculVeriLog;
import tr.bays.mapper.DtoMapper;
import tr.bays.service.impl.BaseCrudServiceImpl;
import tr.bays.service.fon.FonOnculVeriLogService;

@SuppressWarnings("serial")
@Service
public class FonOnculVeriLogServiceImpl extends BaseCrudServiceImpl<FonOnculVeriLogDto, FonOnculVeriLog> implements FonOnculVeriLogService {

	private CustomFonOnculVeriLogRepository customFonOnculVeriLogRepository;
	
	public FonOnculVeriLogServiceImpl(BaseRepository<FonOnculVeriLog> repo, DtoMapper<FonOnculVeriLogDto, FonOnculVeriLog> dtoMapper, CustomFonOnculVeriLogRepository customFonOnculVeriLogRepository) {
		super(repo, dtoMapper);

		this.customFonOnculVeriLogRepository = customFonOnculVeriLogRepository;
	}

	@Override
	@Transactional(readOnly = true)
	public QueryResult<FonOnculVeriLogDto> listele(int firstRecord, int pageSize, Map<String, SortMeta> sort, FonOnculVeriLogDto sorguKriteri, String sorgu) {
		int pageNumber = firstRecord / pageSize;

		Page<FonOnculVeriLog> page = listeleInternal(pageSize, pageNumber, sort, sorguKriteri, sorgu);
		return new QueryResult<FonOnculVeriLogDto>(dtoMapper.entityListToDtoList(page.getContent()), (int) page.getTotalElements());
	}

	private Page<FonOnculVeriLog> listeleInternal(int pageSize, int pageNumber, Map<String, SortMeta> sort, FonOnculVeriLogDto sorguKriteri, String sorgu) {
		return customFonOnculVeriLogRepository.sorgula(pageSize, pageNumber, sort, dtoMapper.dtoToEntity(sorguKriteri), sorgu);
	}

	@Override
	public List<FonOnculVeriLogDto> combo(String query) {
		return dtoMapper.entityListToDtoList(customFonOnculVeriLogRepository.combo(query));
	}

}
