package tr.bays.service.impl.genel;

import java.util.List;
import java.util.Map;

import org.primefaces.model.SortMeta;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import tr.bays.common.QueryResult;
import tr.bays.customdao.genel.CustomIslemRepository;
import tr.bays.dao.BaseRepository;
import tr.bays.dto.genel.IslemDto;
import tr.bays.entity.genel.Islem;
import tr.bays.mapper.DtoMapper;
import tr.bays.service.impl.BaseCrudServiceImpl;
import tr.bays.service.genel.IslemService;

@SuppressWarnings("serial")
@Service
public class IslemServiceImpl extends BaseCrudServiceImpl<IslemDto, Islem> implements IslemService {

	private CustomIslemRepository customIslemRepository;
	
	public IslemServiceImpl(BaseRepository<Islem> repo, DtoMapper<IslemDto, Islem> dtoMapper, CustomIslemRepository customIslemRepository) {
		super(repo, dtoMapper);

		this.customIslemRepository = customIslemRepository;
	}

	@Override
	@Transactional(readOnly = true)
	public QueryResult<IslemDto> listele(int firstRecord, int pageSize, Map<String, SortMeta> sort, IslemDto sorguKriteri, String sorgu) {
		int pageNumber = firstRecord / pageSize;

		Page<Islem> page = listeleInternal(pageSize, pageNumber, sort, sorguKriteri, sorgu);
		return new QueryResult<IslemDto>(dtoMapper.entityListToDtoList(page.getContent()), (int) page.getTotalElements());
	}

	private Page<Islem> listeleInternal(int pageSize, int pageNumber, Map<String, SortMeta> sort, IslemDto sorguKriteri, String sorgu) {
		return customIslemRepository.sorgula(pageSize, pageNumber, sort, dtoMapper.dtoToEntity(sorguKriteri), sorgu);
	}

	@Override
	public List<IslemDto> combo(String query) {
		return dtoMapper.entityListToDtoList(customIslemRepository.combo(query));
	}

}
