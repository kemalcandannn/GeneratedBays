package tr.bays.service.impl.sozluk;

import java.util.List;
import java.util.Map;

import org.primefaces.model.SortMeta;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import tr.bays.common.QueryResult;
import tr.bays.customdao.sozluk.CustomSozlukMaddeRepository;
import tr.bays.dao.BaseRepository;
import tr.bays.dto.sozluk.SozlukMaddeDto;
import tr.bays.entity.sozluk.SozlukMadde;
import tr.bays.mapper.DtoMapper;
import tr.bays.service.impl.BaseCrudServiceImpl;
import tr.bays.service.sozluk.SozlukMaddeService;

@SuppressWarnings("serial")
@Service
public class SozlukMaddeServiceImpl extends BaseCrudServiceImpl<SozlukMaddeDto, SozlukMadde> implements SozlukMaddeService {

	private CustomSozlukMaddeRepository customSozlukMaddeRepository;
	
	public SozlukMaddeServiceImpl(BaseRepository<SozlukMadde> repo, DtoMapper<SozlukMaddeDto, SozlukMadde> dtoMapper, CustomSozlukMaddeRepository customSozlukMaddeRepository) {
		super(repo, dtoMapper);

		this.customSozlukMaddeRepository = customSozlukMaddeRepository;
	}

	@Override
	@Transactional(readOnly = true)
	public QueryResult<SozlukMaddeDto> listele(int firstRecord, int pageSize, Map<String, SortMeta> sort, SozlukMaddeDto sorguKriteri, String sorgu) {
		int pageNumber = firstRecord / pageSize;

		Page<SozlukMadde> page = listeleInternal(pageSize, pageNumber, sort, sorguKriteri, sorgu);
		return new QueryResult<SozlukMaddeDto>(dtoMapper.entityListToDtoList(page.getContent()), (int) page.getTotalElements());
	}

	private Page<SozlukMadde> listeleInternal(int pageSize, int pageNumber, Map<String, SortMeta> sort, SozlukMaddeDto sorguKriteri, String sorgu) {
		return customSozlukMaddeRepository.sorgula(pageSize, pageNumber, sort, dtoMapper.dtoToEntity(sorguKriteri), sorgu);
	}

	@Override
	public List<SozlukMaddeDto> combo(String query) {
		return dtoMapper.entityListToDtoList(customSozlukMaddeRepository.combo(query));
	}

}
