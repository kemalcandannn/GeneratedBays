package tr.bays.service.impl.teskilat;

import java.util.List;
import java.util.Map;

import org.primefaces.model.SortMeta;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import tr.bays.common.QueryResult;
import tr.bays.customdao.teskilat.CustomKullaniciRolRepository;
import tr.bays.dao.BaseRepository;
import tr.bays.dto.teskilat.KullaniciRolDto;
import tr.bays.entity.teskilat.KullaniciRol;
import tr.bays.mapper.DtoMapper;
import tr.bays.service.impl.BaseCrudServiceImpl;
import tr.bays.service.teskilat.KullaniciRolService;

@SuppressWarnings("serial")
@Service
public class KullaniciRolServiceImpl extends BaseCrudServiceImpl<KullaniciRolDto, KullaniciRol> implements KullaniciRolService {

	private CustomKullaniciRolRepository customKullaniciRolRepository;
	
	public KullaniciRolServiceImpl(BaseRepository<KullaniciRol> repo, DtoMapper<KullaniciRolDto, KullaniciRol> dtoMapper, CustomKullaniciRolRepository customKullaniciRolRepository) {
		super(repo, dtoMapper);

		this.customKullaniciRolRepository = customKullaniciRolRepository;
	}

	@Override
	@Transactional(readOnly = true)
	public QueryResult<KullaniciRolDto> listele(int firstRecord, int pageSize, Map<String, SortMeta> sort, KullaniciRolDto sorguKriteri, String sorgu) {
		int pageNumber = firstRecord / pageSize;

		Page<KullaniciRol> page = listeleInternal(pageSize, pageNumber, sort, sorguKriteri, sorgu);
		return new QueryResult<KullaniciRolDto>(dtoMapper.entityListToDtoList(page.getContent()), (int) page.getTotalElements());
	}

	private Page<KullaniciRol> listeleInternal(int pageSize, int pageNumber, Map<String, SortMeta> sort, KullaniciRolDto sorguKriteri, String sorgu) {
		return customKullaniciRolRepository.sorgula(pageSize, pageNumber, sort, dtoMapper.dtoToEntity(sorguKriteri), sorgu);
	}

	@Override
	public List<KullaniciRolDto> combo(String query) {
		return dtoMapper.entityListToDtoList(customKullaniciRolRepository.combo(query));
	}

}
