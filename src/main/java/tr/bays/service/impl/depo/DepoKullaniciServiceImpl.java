package tr.bays.service.impl.depo;

import java.util.List;
import java.util.Map;

import org.primefaces.model.SortMeta;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import tr.bays.common.QueryResult;
import tr.bays.customdao.depo.CustomDepoKullaniciRepository;
import tr.bays.dao.BaseRepository;
import tr.bays.dto.depo.DepoKullaniciDto;
import tr.bays.entity.depo.DepoKullanici;
import tr.bays.mapper.DtoMapper;
import tr.bays.service.impl.BaseCrudServiceImpl;
import tr.bays.service.depo.DepoKullaniciService;

@SuppressWarnings("serial")
@Service
public class DepoKullaniciServiceImpl extends BaseCrudServiceImpl<DepoKullaniciDto, DepoKullanici> implements DepoKullaniciService {

	private CustomDepoKullaniciRepository customDepoKullaniciRepository;
	
	public DepoKullaniciServiceImpl(BaseRepository<DepoKullanici> repo, DtoMapper<DepoKullaniciDto, DepoKullanici> dtoMapper, CustomDepoKullaniciRepository customDepoKullaniciRepository) {
		super(repo, dtoMapper);

		this.customDepoKullaniciRepository = customDepoKullaniciRepository;
	}

	@Override
	@Transactional(readOnly = true)
	public QueryResult<DepoKullaniciDto> listele(int firstRecord, int pageSize, Map<String, SortMeta> sort, DepoKullaniciDto sorguKriteri, String sorgu) {
		int pageNumber = firstRecord / pageSize;

		Page<DepoKullanici> page = listeleInternal(pageSize, pageNumber, sort, sorguKriteri, sorgu);
		return new QueryResult<DepoKullaniciDto>(dtoMapper.entityListToDtoList(page.getContent()), (int) page.getTotalElements());
	}

	private Page<DepoKullanici> listeleInternal(int pageSize, int pageNumber, Map<String, SortMeta> sort, DepoKullaniciDto sorguKriteri, String sorgu) {
		return customDepoKullaniciRepository.sorgula(pageSize, pageNumber, sort, dtoMapper.dtoToEntity(sorguKriteri), sorgu);
	}

	@Override
	public List<DepoKullaniciDto> combo(String query) {
		return dtoMapper.entityListToDtoList(customDepoKullaniciRepository.combo(query));
	}

}
