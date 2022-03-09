package tr.bays.service.impl.teskilat;

import java.util.List;
import java.util.Map;

import org.primefaces.model.SortMeta;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import tr.bays.common.QueryResult;
import tr.bays.customdao.teskilat.CustomKullaniciHesapRepository;
import tr.bays.dao.BaseRepository;
import tr.bays.dto.teskilat.KullaniciHesapDto;
import tr.bays.entity.teskilat.KullaniciHesap;
import tr.bays.mapper.DtoMapper;
import tr.bays.service.impl.BaseCrudServiceImpl;
import tr.bays.service.teskilat.KullaniciHesapService;

@SuppressWarnings("serial")
@Service
public class KullaniciHesapServiceImpl extends BaseCrudServiceImpl<KullaniciHesapDto, KullaniciHesap> implements KullaniciHesapService {

	private CustomKullaniciHesapRepository customKullaniciHesapRepository;
	
	public KullaniciHesapServiceImpl(BaseRepository<KullaniciHesap> repo, DtoMapper<KullaniciHesapDto, KullaniciHesap> dtoMapper, CustomKullaniciHesapRepository customKullaniciHesapRepository) {
		super(repo, dtoMapper);

		this.customKullaniciHesapRepository = customKullaniciHesapRepository;
	}

	@Override
	@Transactional(readOnly = true)
	public QueryResult<KullaniciHesapDto> listele(int firstRecord, int pageSize, Map<String, SortMeta> sort, KullaniciHesapDto sorguKriteri, String sorgu) {
		int pageNumber = firstRecord / pageSize;

		Page<KullaniciHesap> page = listeleInternal(pageSize, pageNumber, sort, sorguKriteri, sorgu);
		return new QueryResult<KullaniciHesapDto>(dtoMapper.entityListToDtoList(page.getContent()), (int) page.getTotalElements());
	}

	private Page<KullaniciHesap> listeleInternal(int pageSize, int pageNumber, Map<String, SortMeta> sort, KullaniciHesapDto sorguKriteri, String sorgu) {
		return customKullaniciHesapRepository.sorgula(pageSize, pageNumber, sort, dtoMapper.dtoToEntity(sorguKriteri), sorgu);
	}

	@Override
	public List<KullaniciHesapDto> combo(String query) {
		return dtoMapper.entityListToDtoList(customKullaniciHesapRepository.combo(query));
	}

}
