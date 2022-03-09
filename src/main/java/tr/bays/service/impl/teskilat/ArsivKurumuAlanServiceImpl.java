package tr.bays.service.impl.teskilat;

import java.util.List;
import java.util.Map;

import org.primefaces.model.SortMeta;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import tr.bays.common.QueryResult;
import tr.bays.customdao.teskilat.CustomArsivKurumuAlanRepository;
import tr.bays.dao.BaseRepository;
import tr.bays.dto.teskilat.ArsivKurumuAlanDto;
import tr.bays.entity.teskilat.ArsivKurumuAlan;
import tr.bays.mapper.DtoMapper;
import tr.bays.service.impl.BaseCrudServiceImpl;
import tr.bays.service.teskilat.ArsivKurumuAlanService;

@SuppressWarnings("serial")
@Service
public class ArsivKurumuAlanServiceImpl extends BaseCrudServiceImpl<ArsivKurumuAlanDto, ArsivKurumuAlan> implements ArsivKurumuAlanService {

	private CustomArsivKurumuAlanRepository customArsivKurumuAlanRepository;
	
	public ArsivKurumuAlanServiceImpl(BaseRepository<ArsivKurumuAlan> repo, DtoMapper<ArsivKurumuAlanDto, ArsivKurumuAlan> dtoMapper, CustomArsivKurumuAlanRepository customArsivKurumuAlanRepository) {
		super(repo, dtoMapper);

		this.customArsivKurumuAlanRepository = customArsivKurumuAlanRepository;
	}

	@Override
	@Transactional(readOnly = true)
	public QueryResult<ArsivKurumuAlanDto> listele(int firstRecord, int pageSize, Map<String, SortMeta> sort, ArsivKurumuAlanDto sorguKriteri, String sorgu) {
		int pageNumber = firstRecord / pageSize;

		Page<ArsivKurumuAlan> page = listeleInternal(pageSize, pageNumber, sort, sorguKriteri, sorgu);
		return new QueryResult<ArsivKurumuAlanDto>(dtoMapper.entityListToDtoList(page.getContent()), (int) page.getTotalElements());
	}

	private Page<ArsivKurumuAlan> listeleInternal(int pageSize, int pageNumber, Map<String, SortMeta> sort, ArsivKurumuAlanDto sorguKriteri, String sorgu) {
		return customArsivKurumuAlanRepository.sorgula(pageSize, pageNumber, sort, dtoMapper.dtoToEntity(sorguKriteri), sorgu);
	}

	@Override
	public List<ArsivKurumuAlanDto> combo(String query) {
		return dtoMapper.entityListToDtoList(customArsivKurumuAlanRepository.combo(query));
	}

}
