package tr.bays.service.impl.metadata;

import java.util.List;
import java.util.Map;

import org.primefaces.model.SortMeta;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import tr.bays.common.QueryResult;
import tr.bays.customdao.metadata.CustomMetadataSetiRepository;
import tr.bays.dao.BaseRepository;
import tr.bays.dto.metadata.MetadataSetiDto;
import tr.bays.entity.metadata.MetadataSeti;
import tr.bays.mapper.DtoMapper;
import tr.bays.service.impl.BaseCrudServiceImpl;
import tr.bays.service.metadata.MetadataSetiService;

@SuppressWarnings("serial")
@Service
public class MetadataSetiServiceImpl extends BaseCrudServiceImpl<MetadataSetiDto, MetadataSeti> implements MetadataSetiService {

	private CustomMetadataSetiRepository customMetadataSetiRepository;
	
	public MetadataSetiServiceImpl(BaseRepository<MetadataSeti> repo, DtoMapper<MetadataSetiDto, MetadataSeti> dtoMapper, CustomMetadataSetiRepository customMetadataSetiRepository) {
		super(repo, dtoMapper);

		this.customMetadataSetiRepository = customMetadataSetiRepository;
	}

	@Override
	@Transactional(readOnly = true)
	public QueryResult<MetadataSetiDto> listele(int firstRecord, int pageSize, Map<String, SortMeta> sort, MetadataSetiDto sorguKriteri, String sorgu) {
		int pageNumber = firstRecord / pageSize;

		Page<MetadataSeti> page = listeleInternal(pageSize, pageNumber, sort, sorguKriteri, sorgu);
		return new QueryResult<MetadataSetiDto>(dtoMapper.entityListToDtoList(page.getContent()), (int) page.getTotalElements());
	}

	private Page<MetadataSeti> listeleInternal(int pageSize, int pageNumber, Map<String, SortMeta> sort, MetadataSetiDto sorguKriteri, String sorgu) {
		return customMetadataSetiRepository.sorgula(pageSize, pageNumber, sort, dtoMapper.dtoToEntity(sorguKriteri), sorgu);
	}

	@Override
	public List<MetadataSetiDto> combo(String query) {
		return dtoMapper.entityListToDtoList(customMetadataSetiRepository.combo(query));
	}

}
