package tr.bays.service.impl.hamEvrak;

import java.util.List;
import java.util.Map;

import org.primefaces.model.SortMeta;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import tr.bays.common.QueryResult;
import tr.bays.customdao.hamEvrak.CustomHamEvrakRepository;
import tr.bays.dao.BaseRepository;
import tr.bays.dto.hamEvrak.HamEvrakDto;
import tr.bays.entity.hamEvrak.HamEvrak;
import tr.bays.mapper.DtoMapper;
import tr.bays.service.impl.BaseCrudServiceImpl;
import tr.bays.service.hamEvrak.HamEvrakService;

@SuppressWarnings("serial")
@Service
public class HamEvrakServiceImpl extends BaseCrudServiceImpl<HamEvrakDto, HamEvrak> implements HamEvrakService {

	private CustomHamEvrakRepository customHamEvrakRepository;
	
	public HamEvrakServiceImpl(BaseRepository<HamEvrak> repo, DtoMapper<HamEvrakDto, HamEvrak> dtoMapper, CustomHamEvrakRepository customHamEvrakRepository) {
		super(repo, dtoMapper);

		this.customHamEvrakRepository = customHamEvrakRepository;
	}

	@Override
	@Transactional(readOnly = true)
	public QueryResult<HamEvrakDto> listele(int firstRecord, int pageSize, Map<String, SortMeta> sort, HamEvrakDto sorguKriteri, String sorgu) {
		int pageNumber = firstRecord / pageSize;

		Page<HamEvrak> page = listeleInternal(pageSize, pageNumber, sort, sorguKriteri, sorgu);
		return new QueryResult<HamEvrakDto>(dtoMapper.entityListToDtoList(page.getContent()), (int) page.getTotalElements());
	}

	private Page<HamEvrak> listeleInternal(int pageSize, int pageNumber, Map<String, SortMeta> sort, HamEvrakDto sorguKriteri, String sorgu) {
		return customHamEvrakRepository.sorgula(pageSize, pageNumber, sort, dtoMapper.dtoToEntity(sorguKriteri), sorgu);
	}

	@Override
	public List<HamEvrakDto> combo(String query) {
		return dtoMapper.entityListToDtoList(customHamEvrakRepository.combo(query));
	}

}
