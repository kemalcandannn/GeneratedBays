package tr.bays.service.impl.teskilat;

import java.util.List;
import java.util.Map;

import org.primefaces.model.SortMeta;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import tr.bays.common.QueryResult;
import tr.bays.customdao.teskilat.CustomArsivKurumuRepository;
import tr.bays.dao.BaseRepository;
import tr.bays.dto.teskilat.ArsivKurumuDto;
import tr.bays.entity.teskilat.ArsivKurumu;
import tr.bays.mapper.DtoMapper;
import tr.bays.service.impl.BaseCrudServiceImpl;
import tr.bays.service.teskilat.ArsivKurumuService;

@SuppressWarnings("serial")
@Service
public class ArsivKurumuServiceImpl extends BaseCrudServiceImpl<ArsivKurumuDto, ArsivKurumu> implements ArsivKurumuService {

	private CustomArsivKurumuRepository customArsivKurumuRepository;
	
	public ArsivKurumuServiceImpl(BaseRepository<ArsivKurumu> repo, DtoMapper<ArsivKurumuDto, ArsivKurumu> dtoMapper, CustomArsivKurumuRepository customArsivKurumuRepository) {
		super(repo, dtoMapper);

		this.customArsivKurumuRepository = customArsivKurumuRepository;
	}

	@Override
	@Transactional(readOnly = true)
	public QueryResult<ArsivKurumuDto> listele(int firstRecord, int pageSize, Map<String, SortMeta> sort, ArsivKurumuDto sorguKriteri, String sorgu) {
		int pageNumber = firstRecord / pageSize;

		Page<ArsivKurumu> page = listeleInternal(pageSize, pageNumber, sort, sorguKriteri, sorgu);
		return new QueryResult<ArsivKurumuDto>(dtoMapper.entityListToDtoList(page.getContent()), (int) page.getTotalElements());
	}

	private Page<ArsivKurumu> listeleInternal(int pageSize, int pageNumber, Map<String, SortMeta> sort, ArsivKurumuDto sorguKriteri, String sorgu) {
		return customArsivKurumuRepository.sorgula(pageSize, pageNumber, sort, dtoMapper.dtoToEntity(sorguKriteri), sorgu);
	}

	@Override
	public List<ArsivKurumuDto> combo(String query) {
		return dtoMapper.entityListToDtoList(customArsivKurumuRepository.combo(query));
	}

}
