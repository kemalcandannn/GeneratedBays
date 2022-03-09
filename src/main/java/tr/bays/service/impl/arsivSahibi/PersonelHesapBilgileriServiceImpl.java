package tr.bays.service.impl.arsivSahibi;

import java.util.List;
import java.util.Map;

import org.primefaces.model.SortMeta;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import tr.bays.common.QueryResult;
import tr.bays.customdao.arsivSahibi.CustomPersonelHesapBilgileriRepository;
import tr.bays.dao.BaseRepository;
import tr.bays.dto.arsivSahibi.PersonelHesapBilgileriDto;
import tr.bays.entity.arsivSahibi.PersonelHesapBilgileri;
import tr.bays.mapper.DtoMapper;
import tr.bays.service.impl.BaseCrudServiceImpl;
import tr.bays.service.arsivSahibi.PersonelHesapBilgileriService;

@SuppressWarnings("serial")
@Service
public class PersonelHesapBilgileriServiceImpl extends BaseCrudServiceImpl<PersonelHesapBilgileriDto, PersonelHesapBilgileri> implements PersonelHesapBilgileriService {

	private CustomPersonelHesapBilgileriRepository customPersonelHesapBilgileriRepository;
	
	public PersonelHesapBilgileriServiceImpl(BaseRepository<PersonelHesapBilgileri> repo, DtoMapper<PersonelHesapBilgileriDto, PersonelHesapBilgileri> dtoMapper, CustomPersonelHesapBilgileriRepository customPersonelHesapBilgileriRepository) {
		super(repo, dtoMapper);

		this.customPersonelHesapBilgileriRepository = customPersonelHesapBilgileriRepository;
	}

	@Override
	@Transactional(readOnly = true)
	public QueryResult<PersonelHesapBilgileriDto> listele(int firstRecord, int pageSize, Map<String, SortMeta> sort, PersonelHesapBilgileriDto sorguKriteri, String sorgu) {
		int pageNumber = firstRecord / pageSize;

		Page<PersonelHesapBilgileri> page = listeleInternal(pageSize, pageNumber, sort, sorguKriteri, sorgu);
		return new QueryResult<PersonelHesapBilgileriDto>(dtoMapper.entityListToDtoList(page.getContent()), (int) page.getTotalElements());
	}

	private Page<PersonelHesapBilgileri> listeleInternal(int pageSize, int pageNumber, Map<String, SortMeta> sort, PersonelHesapBilgileriDto sorguKriteri, String sorgu) {
		return customPersonelHesapBilgileriRepository.sorgula(pageSize, pageNumber, sort, dtoMapper.dtoToEntity(sorguKriteri), sorgu);
	}

	@Override
	public List<PersonelHesapBilgileriDto> combo(String query) {
		return dtoMapper.entityListToDtoList(customPersonelHesapBilgileriRepository.combo(query));
	}

}
