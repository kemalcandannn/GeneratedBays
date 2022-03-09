package tr.bays.service.impl.teskilat;

import java.util.List;
import java.util.Map;

import org.primefaces.model.SortMeta;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import tr.bays.common.QueryResult;
import tr.bays.customdao.teskilat.CustomBirimRepository;
import tr.bays.dao.BaseRepository;
import tr.bays.dto.teskilat.BirimDto;
import tr.bays.entity.teskilat.Birim;
import tr.bays.mapper.DtoMapper;
import tr.bays.service.impl.BaseCrudServiceImpl;
import tr.bays.service.teskilat.BirimService;

@SuppressWarnings("serial")
@Service
public class BirimServiceImpl extends BaseCrudServiceImpl<BirimDto, Birim> implements BirimService {

	private CustomBirimRepository customBirimRepository;
	
	public BirimServiceImpl(BaseRepository<Birim> repo, DtoMapper<BirimDto, Birim> dtoMapper, CustomBirimRepository customBirimRepository) {
		super(repo, dtoMapper);

		this.customBirimRepository = customBirimRepository;
	}

	@Override
	@Transactional(readOnly = true)
	public QueryResult<BirimDto> listele(int firstRecord, int pageSize, Map<String, SortMeta> sort, BirimDto sorguKriteri, String sorgu) {
		int pageNumber = firstRecord / pageSize;

		Page<Birim> page = listeleInternal(pageSize, pageNumber, sort, sorguKriteri, sorgu);
		return new QueryResult<BirimDto>(dtoMapper.entityListToDtoList(page.getContent()), (int) page.getTotalElements());
	}

	private Page<Birim> listeleInternal(int pageSize, int pageNumber, Map<String, SortMeta> sort, BirimDto sorguKriteri, String sorgu) {
		return customBirimRepository.sorgula(pageSize, pageNumber, sort, dtoMapper.dtoToEntity(sorguKriteri), sorgu);
	}

	@Override
	public List<BirimDto> combo(String query) {
		return dtoMapper.entityListToDtoList(customBirimRepository.combo(query));
	}

}
