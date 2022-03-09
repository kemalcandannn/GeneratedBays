package tr.bays.service.impl.hamEvrak;

import java.util.List;
import java.util.Map;

import org.primefaces.model.SortMeta;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import tr.bays.common.QueryResult;
import tr.bays.customdao.hamEvrak.CustomDepoIciTasimaRepository;
import tr.bays.dao.BaseRepository;
import tr.bays.dto.hamEvrak.DepoIciTasimaDto;
import tr.bays.entity.hamEvrak.DepoIciTasima;
import tr.bays.mapper.DtoMapper;
import tr.bays.service.impl.BaseCrudServiceImpl;
import tr.bays.service.hamEvrak.DepoIciTasimaService;

@SuppressWarnings("serial")
@Service
public class DepoIciTasimaServiceImpl extends BaseCrudServiceImpl<DepoIciTasimaDto, DepoIciTasima> implements DepoIciTasimaService {

	private CustomDepoIciTasimaRepository customDepoIciTasimaRepository;
	
	public DepoIciTasimaServiceImpl(BaseRepository<DepoIciTasima> repo, DtoMapper<DepoIciTasimaDto, DepoIciTasima> dtoMapper, CustomDepoIciTasimaRepository customDepoIciTasimaRepository) {
		super(repo, dtoMapper);

		this.customDepoIciTasimaRepository = customDepoIciTasimaRepository;
	}

	@Override
	@Transactional(readOnly = true)
	public QueryResult<DepoIciTasimaDto> listele(int firstRecord, int pageSize, Map<String, SortMeta> sort, DepoIciTasimaDto sorguKriteri, String sorgu) {
		int pageNumber = firstRecord / pageSize;

		Page<DepoIciTasima> page = listeleInternal(pageSize, pageNumber, sort, sorguKriteri, sorgu);
		return new QueryResult<DepoIciTasimaDto>(dtoMapper.entityListToDtoList(page.getContent()), (int) page.getTotalElements());
	}

	private Page<DepoIciTasima> listeleInternal(int pageSize, int pageNumber, Map<String, SortMeta> sort, DepoIciTasimaDto sorguKriteri, String sorgu) {
		return customDepoIciTasimaRepository.sorgula(pageSize, pageNumber, sort, dtoMapper.dtoToEntity(sorguKriteri), sorgu);
	}

	@Override
	public List<DepoIciTasimaDto> combo(String query) {
		return dtoMapper.entityListToDtoList(customDepoIciTasimaRepository.combo(query));
	}

}
