package tr.bays.service.impl.genel;

import java.util.List;
import java.util.Map;

import org.primefaces.model.SortMeta;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import tr.bays.common.QueryResult;
import tr.bays.customdao.genel.CustomRolIslemRepository;
import tr.bays.dao.BaseRepository;
import tr.bays.dto.genel.RolIslemDto;
import tr.bays.entity.genel.RolIslem;
import tr.bays.mapper.DtoMapper;
import tr.bays.service.impl.BaseCrudServiceImpl;
import tr.bays.service.genel.RolIslemService;

@SuppressWarnings("serial")
@Service
public class RolIslemServiceImpl extends BaseCrudServiceImpl<RolIslemDto, RolIslem> implements RolIslemService {

	private CustomRolIslemRepository customRolIslemRepository;
	
	public RolIslemServiceImpl(BaseRepository<RolIslem> repo, DtoMapper<RolIslemDto, RolIslem> dtoMapper, CustomRolIslemRepository customRolIslemRepository) {
		super(repo, dtoMapper);

		this.customRolIslemRepository = customRolIslemRepository;
	}

	@Override
	@Transactional(readOnly = true)
	public QueryResult<RolIslemDto> listele(int firstRecord, int pageSize, Map<String, SortMeta> sort, RolIslemDto sorguKriteri, String sorgu) {
		int pageNumber = firstRecord / pageSize;

		Page<RolIslem> page = listeleInternal(pageSize, pageNumber, sort, sorguKriteri, sorgu);
		return new QueryResult<RolIslemDto>(dtoMapper.entityListToDtoList(page.getContent()), (int) page.getTotalElements());
	}

	private Page<RolIslem> listeleInternal(int pageSize, int pageNumber, Map<String, SortMeta> sort, RolIslemDto sorguKriteri, String sorgu) {
		return customRolIslemRepository.sorgula(pageSize, pageNumber, sort, dtoMapper.dtoToEntity(sorguKriteri), sorgu);
	}

	@Override
	public List<RolIslemDto> combo(String query) {
		return dtoMapper.entityListToDtoList(customRolIslemRepository.combo(query));
	}

}
