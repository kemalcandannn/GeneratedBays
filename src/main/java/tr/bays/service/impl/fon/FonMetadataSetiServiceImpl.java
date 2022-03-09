package tr.bays.service.impl.fon;

import java.util.List;
import java.util.Map;

import org.primefaces.model.SortMeta;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import tr.bays.common.QueryResult;
import tr.bays.customdao.fon.CustomFonMetadataSetiRepository;
import tr.bays.dao.BaseRepository;
import tr.bays.dto.fon.FonMetadataSetiDto;
import tr.bays.entity.fon.FonMetadataSeti;
import tr.bays.mapper.DtoMapper;
import tr.bays.service.impl.BaseCrudServiceImpl;
import tr.bays.service.fon.FonMetadataSetiService;

@SuppressWarnings("serial")
@Service
public class FonMetadataSetiServiceImpl extends BaseCrudServiceImpl<FonMetadataSetiDto, FonMetadataSeti> implements FonMetadataSetiService {

	private CustomFonMetadataSetiRepository customFonMetadataSetiRepository;
	
	public FonMetadataSetiServiceImpl(BaseRepository<FonMetadataSeti> repo, DtoMapper<FonMetadataSetiDto, FonMetadataSeti> dtoMapper, CustomFonMetadataSetiRepository customFonMetadataSetiRepository) {
		super(repo, dtoMapper);

		this.customFonMetadataSetiRepository = customFonMetadataSetiRepository;
	}

	@Override
	@Transactional(readOnly = true)
	public QueryResult<FonMetadataSetiDto> listele(int firstRecord, int pageSize, Map<String, SortMeta> sort, FonMetadataSetiDto sorguKriteri, String sorgu) {
		int pageNumber = firstRecord / pageSize;

		Page<FonMetadataSeti> page = listeleInternal(pageSize, pageNumber, sort, sorguKriteri, sorgu);
		return new QueryResult<FonMetadataSetiDto>(dtoMapper.entityListToDtoList(page.getContent()), (int) page.getTotalElements());
	}

	private Page<FonMetadataSeti> listeleInternal(int pageSize, int pageNumber, Map<String, SortMeta> sort, FonMetadataSetiDto sorguKriteri, String sorgu) {
		return customFonMetadataSetiRepository.sorgula(pageSize, pageNumber, sort, dtoMapper.dtoToEntity(sorguKriteri), sorgu);
	}

	@Override
	public List<FonMetadataSetiDto> combo(String query) {
		return dtoMapper.entityListToDtoList(customFonMetadataSetiRepository.combo(query));
	}

}
