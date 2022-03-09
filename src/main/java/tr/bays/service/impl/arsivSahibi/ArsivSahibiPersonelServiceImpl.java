package tr.bays.service.impl.arsivSahibi;

import java.util.List;
import java.util.Map;

import org.primefaces.model.SortMeta;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import tr.bays.common.QueryResult;
import tr.bays.customdao.arsivSahibi.CustomArsivSahibiPersonelRepository;
import tr.bays.dao.BaseRepository;
import tr.bays.dto.arsivSahibi.ArsivSahibiPersonelDto;
import tr.bays.entity.arsivSahibi.ArsivSahibiPersonel;
import tr.bays.mapper.DtoMapper;
import tr.bays.service.impl.BaseCrudServiceImpl;
import tr.bays.service.arsivSahibi.ArsivSahibiPersonelService;

@SuppressWarnings("serial")
@Service
public class ArsivSahibiPersonelServiceImpl extends BaseCrudServiceImpl<ArsivSahibiPersonelDto, ArsivSahibiPersonel> implements ArsivSahibiPersonelService {

	private CustomArsivSahibiPersonelRepository customArsivSahibiPersonelRepository;
	
	public ArsivSahibiPersonelServiceImpl(BaseRepository<ArsivSahibiPersonel> repo, DtoMapper<ArsivSahibiPersonelDto, ArsivSahibiPersonel> dtoMapper, CustomArsivSahibiPersonelRepository customArsivSahibiPersonelRepository) {
		super(repo, dtoMapper);

		this.customArsivSahibiPersonelRepository = customArsivSahibiPersonelRepository;
	}

	@Override
	@Transactional(readOnly = true)
	public QueryResult<ArsivSahibiPersonelDto> listele(int firstRecord, int pageSize, Map<String, SortMeta> sort, ArsivSahibiPersonelDto sorguKriteri, String sorgu) {
		int pageNumber = firstRecord / pageSize;

		Page<ArsivSahibiPersonel> page = listeleInternal(pageSize, pageNumber, sort, sorguKriteri, sorgu);
		return new QueryResult<ArsivSahibiPersonelDto>(dtoMapper.entityListToDtoList(page.getContent()), (int) page.getTotalElements());
	}

	private Page<ArsivSahibiPersonel> listeleInternal(int pageSize, int pageNumber, Map<String, SortMeta> sort, ArsivSahibiPersonelDto sorguKriteri, String sorgu) {
		return customArsivSahibiPersonelRepository.sorgula(pageSize, pageNumber, sort, dtoMapper.dtoToEntity(sorguKriteri), sorgu);
	}

	@Override
	public List<ArsivSahibiPersonelDto> combo(String query) {
		return dtoMapper.entityListToDtoList(customArsivSahibiPersonelRepository.combo(query));
	}

}
