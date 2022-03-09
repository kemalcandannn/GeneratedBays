package tr.bays.service.impl.hamEvrak;

import java.util.List;
import java.util.Map;

import org.primefaces.model.SortMeta;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import tr.bays.common.QueryResult;
import tr.bays.customdao.hamEvrak.CustomHamEvrakBirimRepository;
import tr.bays.dao.BaseRepository;
import tr.bays.dto.hamEvrak.HamEvrakBirimDto;
import tr.bays.entity.hamEvrak.HamEvrakBirim;
import tr.bays.mapper.DtoMapper;
import tr.bays.service.impl.BaseCrudServiceImpl;
import tr.bays.service.hamEvrak.HamEvrakBirimService;

@SuppressWarnings("serial")
@Service
public class HamEvrakBirimServiceImpl extends BaseCrudServiceImpl<HamEvrakBirimDto, HamEvrakBirim> implements HamEvrakBirimService {

	private CustomHamEvrakBirimRepository customHamEvrakBirimRepository;
	
	public HamEvrakBirimServiceImpl(BaseRepository<HamEvrakBirim> repo, DtoMapper<HamEvrakBirimDto, HamEvrakBirim> dtoMapper, CustomHamEvrakBirimRepository customHamEvrakBirimRepository) {
		super(repo, dtoMapper);

		this.customHamEvrakBirimRepository = customHamEvrakBirimRepository;
	}

	@Override
	@Transactional(readOnly = true)
	public QueryResult<HamEvrakBirimDto> listele(int firstRecord, int pageSize, Map<String, SortMeta> sort, HamEvrakBirimDto sorguKriteri, String sorgu) {
		int pageNumber = firstRecord / pageSize;

		Page<HamEvrakBirim> page = listeleInternal(pageSize, pageNumber, sort, sorguKriteri, sorgu);
		return new QueryResult<HamEvrakBirimDto>(dtoMapper.entityListToDtoList(page.getContent()), (int) page.getTotalElements());
	}

	private Page<HamEvrakBirim> listeleInternal(int pageSize, int pageNumber, Map<String, SortMeta> sort, HamEvrakBirimDto sorguKriteri, String sorgu) {
		return customHamEvrakBirimRepository.sorgula(pageSize, pageNumber, sort, dtoMapper.dtoToEntity(sorguKriteri), sorgu);
	}

	@Override
	public List<HamEvrakBirimDto> combo(String query) {
		return dtoMapper.entityListToDtoList(customHamEvrakBirimRepository.combo(query));
	}

}
