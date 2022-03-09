package tr.bays.service.impl.gomlek;

import java.util.List;
import java.util.Map;

import org.primefaces.model.SortMeta;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import tr.bays.common.QueryResult;
import tr.bays.customdao.gomlek.CustomGomlekEnvanterRepository;
import tr.bays.dao.BaseRepository;
import tr.bays.dto.gomlek.GomlekEnvanterDto;
import tr.bays.entity.gomlek.GomlekEnvanter;
import tr.bays.mapper.DtoMapper;
import tr.bays.service.impl.BaseCrudServiceImpl;
import tr.bays.service.gomlek.GomlekEnvanterService;

@SuppressWarnings("serial")
@Service
public class GomlekEnvanterServiceImpl extends BaseCrudServiceImpl<GomlekEnvanterDto, GomlekEnvanter> implements GomlekEnvanterService {

	private CustomGomlekEnvanterRepository customGomlekEnvanterRepository;
	
	public GomlekEnvanterServiceImpl(BaseRepository<GomlekEnvanter> repo, DtoMapper<GomlekEnvanterDto, GomlekEnvanter> dtoMapper, CustomGomlekEnvanterRepository customGomlekEnvanterRepository) {
		super(repo, dtoMapper);

		this.customGomlekEnvanterRepository = customGomlekEnvanterRepository;
	}

	@Override
	@Transactional(readOnly = true)
	public QueryResult<GomlekEnvanterDto> listele(int firstRecord, int pageSize, Map<String, SortMeta> sort, GomlekEnvanterDto sorguKriteri, String sorgu) {
		int pageNumber = firstRecord / pageSize;

		Page<GomlekEnvanter> page = listeleInternal(pageSize, pageNumber, sort, sorguKriteri, sorgu);
		return new QueryResult<GomlekEnvanterDto>(dtoMapper.entityListToDtoList(page.getContent()), (int) page.getTotalElements());
	}

	private Page<GomlekEnvanter> listeleInternal(int pageSize, int pageNumber, Map<String, SortMeta> sort, GomlekEnvanterDto sorguKriteri, String sorgu) {
		return customGomlekEnvanterRepository.sorgula(pageSize, pageNumber, sort, dtoMapper.dtoToEntity(sorguKriteri), sorgu);
	}

	@Override
	public List<GomlekEnvanterDto> combo(String query) {
		return dtoMapper.entityListToDtoList(customGomlekEnvanterRepository.combo(query));
	}

}
