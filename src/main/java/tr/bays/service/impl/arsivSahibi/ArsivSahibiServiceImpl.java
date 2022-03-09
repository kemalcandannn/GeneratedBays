package tr.bays.service.impl.arsivSahibi;

import java.util.List;
import java.util.Map;

import org.primefaces.model.SortMeta;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import tr.bays.common.QueryResult;
import tr.bays.customdao.arsivSahibi.CustomArsivSahibiRepository;
import tr.bays.dao.BaseRepository;
import tr.bays.dto.arsivSahibi.ArsivSahibiDto;
import tr.bays.entity.arsivSahibi.ArsivSahibi;
import tr.bays.mapper.DtoMapper;
import tr.bays.service.impl.BaseCrudServiceImpl;
import tr.bays.service.arsivSahibi.ArsivSahibiService;

@SuppressWarnings("serial")
@Service
public class ArsivSahibiServiceImpl extends BaseCrudServiceImpl<ArsivSahibiDto, ArsivSahibi> implements ArsivSahibiService {

	private CustomArsivSahibiRepository customArsivSahibiRepository;
	
	public ArsivSahibiServiceImpl(BaseRepository<ArsivSahibi> repo, DtoMapper<ArsivSahibiDto, ArsivSahibi> dtoMapper, CustomArsivSahibiRepository customArsivSahibiRepository) {
		super(repo, dtoMapper);

		this.customArsivSahibiRepository = customArsivSahibiRepository;
	}

	@Override
	@Transactional(readOnly = true)
	public QueryResult<ArsivSahibiDto> listele(int firstRecord, int pageSize, Map<String, SortMeta> sort, ArsivSahibiDto sorguKriteri, String sorgu) {
		int pageNumber = firstRecord / pageSize;

		Page<ArsivSahibi> page = listeleInternal(pageSize, pageNumber, sort, sorguKriteri, sorgu);
		return new QueryResult<ArsivSahibiDto>(dtoMapper.entityListToDtoList(page.getContent()), (int) page.getTotalElements());
	}

	private Page<ArsivSahibi> listeleInternal(int pageSize, int pageNumber, Map<String, SortMeta> sort, ArsivSahibiDto sorguKriteri, String sorgu) {
		return customArsivSahibiRepository.sorgula(pageSize, pageNumber, sort, dtoMapper.dtoToEntity(sorguKriteri), sorgu);
	}

	@Override
	public List<ArsivSahibiDto> combo(String query) {
		return dtoMapper.entityListToDtoList(customArsivSahibiRepository.combo(query));
	}

}
