package tr.bays.service.impl.klasor;

import java.util.List;
import java.util.Map;

import org.primefaces.model.SortMeta;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import tr.bays.common.QueryResult;
import tr.bays.customdao.klasor.CustomKlasorEnvanterRepository;
import tr.bays.dao.BaseRepository;
import tr.bays.dto.klasor.KlasorEnvanterDto;
import tr.bays.entity.klasor.KlasorEnvanter;
import tr.bays.mapper.DtoMapper;
import tr.bays.service.impl.BaseCrudServiceImpl;
import tr.bays.service.klasor.KlasorEnvanterService;

@SuppressWarnings("serial")
@Service
public class KlasorEnvanterServiceImpl extends BaseCrudServiceImpl<KlasorEnvanterDto, KlasorEnvanter> implements KlasorEnvanterService {

	private CustomKlasorEnvanterRepository customKlasorEnvanterRepository;
	
	public KlasorEnvanterServiceImpl(BaseRepository<KlasorEnvanter> repo, DtoMapper<KlasorEnvanterDto, KlasorEnvanter> dtoMapper, CustomKlasorEnvanterRepository customKlasorEnvanterRepository) {
		super(repo, dtoMapper);

		this.customKlasorEnvanterRepository = customKlasorEnvanterRepository;
	}

	@Override
	@Transactional(readOnly = true)
	public QueryResult<KlasorEnvanterDto> listele(int firstRecord, int pageSize, Map<String, SortMeta> sort, KlasorEnvanterDto sorguKriteri, String sorgu) {
		int pageNumber = firstRecord / pageSize;

		Page<KlasorEnvanter> page = listeleInternal(pageSize, pageNumber, sort, sorguKriteri, sorgu);
		return new QueryResult<KlasorEnvanterDto>(dtoMapper.entityListToDtoList(page.getContent()), (int) page.getTotalElements());
	}

	private Page<KlasorEnvanter> listeleInternal(int pageSize, int pageNumber, Map<String, SortMeta> sort, KlasorEnvanterDto sorguKriteri, String sorgu) {
		return customKlasorEnvanterRepository.sorgula(pageSize, pageNumber, sort, dtoMapper.dtoToEntity(sorguKriteri), sorgu);
	}

	@Override
	public List<KlasorEnvanterDto> combo(String query) {
		return dtoMapper.entityListToDtoList(customKlasorEnvanterRepository.combo(query));
	}

}
