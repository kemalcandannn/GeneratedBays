package tr.bays.service.impl;

import java.util.List;
import java.util.Map;

import org.primefaces.model.SortMeta;
import org.springframework.data.domain.Page;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import tr.bays.common.QueryResult;
import tr.bays.customdao.CustomKullaniciRepository;
import tr.bays.dao.BaseRepository;
import tr.bays.dto.KullaniciDto;
import tr.bays.entity.Kullanici;
import tr.bays.mapper.DtoMapper;
import tr.bays.service.KullaniciService;

@SuppressWarnings("serial")
@Service
public class KullaniciServiceImpl extends BaseCrudServiceImpl<KullaniciDto, Kullanici> implements KullaniciService {

	private CustomKullaniciRepository customKullaniciRepository;
	
	public KullaniciServiceImpl(BaseRepository<Kullanici> repo, DtoMapper<KullaniciDto, Kullanici> dtoMapper, CustomKullaniciRepository customKullaniciRepository) {
		super(repo, dtoMapper);

		this.customKullaniciRepository = customKullaniciRepository;
	}

	@Override
	@Transactional(readOnly = true)
	public QueryResult<KullaniciDto> listele(int firstRecord, int pageSize, Map<String, SortMeta> sort, KullaniciDto sorguKriteri, String sorgu) {
		int pageNumber = firstRecord / pageSize;

		Page<Kullanici> page = listeleInternal(pageSize, pageNumber, sort, sorguKriteri, sorgu);
		return new QueryResult<KullaniciDto>(dtoMapper.entityListToDtoList(page.getContent()), (int) page.getTotalElements());
	}

	private Page<Kullanici> listeleInternal(int pageSize, int pageNumber, Map<String, SortMeta> sort, KullaniciDto sorguKriteri, String sorgu) {
		return customKullaniciRepository.sorgula(pageSize, pageNumber, sort, dtoMapper.dtoToEntity(sorguKriteri), sorgu);
	}

	@Override
	public List<KullaniciDto> combo(String query) {
		return dtoMapper.entityListToDtoList(customKullaniciRepository.combo(query));
	}

	@Override
	public KullaniciDto kullaniciAdiIleGetir(String kullaniciAdi) {
		return dtoMapper.entityToDto(customKullaniciRepository.kullaniciAdiIleGetir(kullaniciAdi));
	}

	@Override
	public boolean parolamiUnuttum(PasswordEncoder passwordEncoder, KullaniciDto kullaniciDto) {
		try {
			parolaGuncelle(kullaniciDto, passwordEncoder.encode("candan"));
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	@Transactional
	public KullaniciDto parolaGuncelle(KullaniciDto kullaniciDto, String parola) {
		kullaniciDto.setParola(parola);
		kullaniciDto = kaydet(kullaniciDto);
		
		return kullaniciDto;
	}
}
