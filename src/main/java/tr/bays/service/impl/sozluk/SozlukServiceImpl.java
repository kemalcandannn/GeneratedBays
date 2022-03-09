package tr.bays.service.impl.sozluk;

import java.util.List;
import java.util.Map;

import org.primefaces.model.SortMeta;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import tr.bays.common.QueryResult;
import tr.bays.customdao.sozluk.CustomSozlukRepository;
import tr.bays.dao.BaseRepository;
import tr.bays.dto.sozluk.SozlukDto;
import tr.bays.entity.sozluk.Sozluk;
import tr.bays.mapper.DtoMapper;
import tr.bays.service.impl.BaseCrudServiceImpl;
import tr.bays.service.sozluk.SozlukService;

@SuppressWarnings("serial")
@Service
public class SozlukServiceImpl extends BaseCrudServiceImpl<SozlukDto, Sozluk> implements SozlukService {

	private CustomSozlukRepository customSozlukRepository;
	
	public SozlukServiceImpl(BaseRepository<Sozluk> repo, DtoMapper<SozlukDto, Sozluk> dtoMapper, CustomSozlukRepository customSozlukRepository) {
		super(repo, dtoMapper);

		this.customSozlukRepository = customSozlukRepository;
	}

	@Override
	@Transactional(readOnly = true)
	public QueryResult<SozlukDto> listele(int firstRecord, int pageSize, Map<String, SortMeta> sort, SozlukDto sorguKriteri, String sorgu) {
		int pageNumber = firstRecord / pageSize;

		Page<Sozluk> page = listeleInternal(pageSize, pageNumber, sort, sorguKriteri, sorgu);
		return new QueryResult<SozlukDto>(dtoMapper.entityListToDtoList(page.getContent()), (int) page.getTotalElements());
	}

	private Page<Sozluk> listeleInternal(int pageSize, int pageNumber, Map<String, SortMeta> sort, SozlukDto sorguKriteri, String sorgu) {
		return customSozlukRepository.sorgula(pageSize, pageNumber, sort, dtoMapper.dtoToEntity(sorguKriteri), sorgu);
	}

	@Override
	public List<SozlukDto> combo(String query) {
		return dtoMapper.entityListToDtoList(customSozlukRepository.combo(query));
	}

}
