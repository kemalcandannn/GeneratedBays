package tr.bays.service.impl.genel;

import java.util.List;
import java.util.Map;

import org.primefaces.model.SortMeta;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import tr.bays.common.QueryResult;
import tr.bays.customdao.genel.CustomRolRepository;
import tr.bays.dao.BaseRepository;
import tr.bays.dto.genel.RolDto;
import tr.bays.entity.genel.Rol;
import tr.bays.mapper.DtoMapper;
import tr.bays.service.impl.BaseCrudServiceImpl;
import tr.bays.service.genel.RolService;

@SuppressWarnings("serial")
@Service
public class RolServiceImpl extends BaseCrudServiceImpl<RolDto, Rol> implements RolService {

	private CustomRolRepository customRolRepository;
	
	public RolServiceImpl(BaseRepository<Rol> repo, DtoMapper<RolDto, Rol> dtoMapper, CustomRolRepository customRolRepository) {
		super(repo, dtoMapper);

		this.customRolRepository = customRolRepository;
	}

	@Override
	@Transactional(readOnly = true)
	public QueryResult<RolDto> listele(int firstRecord, int pageSize, Map<String, SortMeta> sort, RolDto sorguKriteri, String sorgu) {
		int pageNumber = firstRecord / pageSize;

		Page<Rol> page = listeleInternal(pageSize, pageNumber, sort, sorguKriteri, sorgu);
		return new QueryResult<RolDto>(dtoMapper.entityListToDtoList(page.getContent()), (int) page.getTotalElements());
	}

	private Page<Rol> listeleInternal(int pageSize, int pageNumber, Map<String, SortMeta> sort, RolDto sorguKriteri, String sorgu) {
		return customRolRepository.sorgula(pageSize, pageNumber, sort, dtoMapper.dtoToEntity(sorguKriteri), sorgu);
	}

	@Override
	public List<RolDto> combo(String query) {
		return dtoMapper.entityListToDtoList(customRolRepository.combo(query));
	}

}
