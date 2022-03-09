package tr.bays.service.impl.calismaGrubu;

import java.util.List;
import java.util.Map;

import org.primefaces.model.SortMeta;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import tr.bays.common.QueryResult;
import tr.bays.customdao.calismaGrubu.CustomCalismaGrubuUyeRepository;
import tr.bays.dao.BaseRepository;
import tr.bays.dto.calismaGrubu.CalismaGrubuUyeDto;
import tr.bays.entity.calismaGrubu.CalismaGrubuUye;
import tr.bays.mapper.DtoMapper;
import tr.bays.service.impl.BaseCrudServiceImpl;
import tr.bays.service.calismaGrubu.CalismaGrubuUyeService;

@SuppressWarnings("serial")
@Service
public class CalismaGrubuUyeServiceImpl extends BaseCrudServiceImpl<CalismaGrubuUyeDto, CalismaGrubuUye> implements CalismaGrubuUyeService {

	private CustomCalismaGrubuUyeRepository customCalismaGrubuUyeRepository;
	
	public CalismaGrubuUyeServiceImpl(BaseRepository<CalismaGrubuUye> repo, DtoMapper<CalismaGrubuUyeDto, CalismaGrubuUye> dtoMapper, CustomCalismaGrubuUyeRepository customCalismaGrubuUyeRepository) {
		super(repo, dtoMapper);

		this.customCalismaGrubuUyeRepository = customCalismaGrubuUyeRepository;
	}

	@Override
	@Transactional(readOnly = true)
	public QueryResult<CalismaGrubuUyeDto> listele(int firstRecord, int pageSize, Map<String, SortMeta> sort, CalismaGrubuUyeDto sorguKriteri, String sorgu) {
		int pageNumber = firstRecord / pageSize;

		Page<CalismaGrubuUye> page = listeleInternal(pageSize, pageNumber, sort, sorguKriteri, sorgu);
		return new QueryResult<CalismaGrubuUyeDto>(dtoMapper.entityListToDtoList(page.getContent()), (int) page.getTotalElements());
	}

	private Page<CalismaGrubuUye> listeleInternal(int pageSize, int pageNumber, Map<String, SortMeta> sort, CalismaGrubuUyeDto sorguKriteri, String sorgu) {
		return customCalismaGrubuUyeRepository.sorgula(pageSize, pageNumber, sort, dtoMapper.dtoToEntity(sorguKriteri), sorgu);
	}

	@Override
	public List<CalismaGrubuUyeDto> combo(String query) {
		return dtoMapper.entityListToDtoList(customCalismaGrubuUyeRepository.combo(query));
	}

}
