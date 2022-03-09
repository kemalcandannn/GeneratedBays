package tr.bays.customdao;

import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.primefaces.model.SortMeta;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Repository;

import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.core.types.dsl.PathBuilder;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;

import tr.bays.entity.Kullanici;
import tr.bays.entity.QKullanici;
import util.Util;

@Repository
public class CustomKullaniciRepository {

	@PersistenceContext
	private EntityManager em;

	public BooleanExpression kriter(Kullanici kullanici, String sorgu) {
		BooleanExpression booleanExpression = null;

		if(Util.notEmpty(kullanici.getAd_soyad())) {
			booleanExpression = Util.and(booleanExpression,QKullanici.kullanici.ad_soyad.likeIgnoreCase(kullanici.getAd_soyad()+"%"));
		}

		if(Util.notEmpty(kullanici.getEposta())) {
			booleanExpression = Util.and(booleanExpression,QKullanici.kullanici.eposta.likeIgnoreCase(kullanici.getEposta()+"%"));
		}

		if(Util.notEmpty(kullanici.getKurum_tel_no())) {
			booleanExpression = Util.and(booleanExpression,QKullanici.kullanici.kurum_tel_no.likeIgnoreCase(kullanici.getKurum_tel_no()+"%"));
		}

		if(Util.notEmpty(kullanici.getCep_tel_no())) {
			booleanExpression = Util.and(booleanExpression,QKullanici.kullanici.cep_tel_no.likeIgnoreCase(kullanici.getCep_tel_no()+"%"));
		}

		if(kullanici.getAktif() != 0) {
			booleanExpression = Util.and(booleanExpression,QKullanici.kullanici.aktif.eq(kullanici.getAktif()));
		}

		if(Util.notEmpty(sorgu)) {
			BooleanExpression booleanExpressionAdvancedSearch = null;
			booleanExpressionAdvancedSearch = Util.or(booleanExpressionAdvancedSearch, QKullanici.kullanici.ad_soyad.likeIgnoreCase("%" + sorgu + "%"));
			booleanExpressionAdvancedSearch = Util.or(booleanExpressionAdvancedSearch, QKullanici.kullanici.eposta.likeIgnoreCase("%" + sorgu + "%"));
			booleanExpressionAdvancedSearch = Util.or(booleanExpressionAdvancedSearch, QKullanici.kullanici.kurum_tel_no.likeIgnoreCase("%" + sorgu + "%"));
			booleanExpressionAdvancedSearch = Util.or(booleanExpressionAdvancedSearch, QKullanici.kullanici.cep_tel_no.likeIgnoreCase("%" + sorgu + "%"));

			if(booleanExpressionAdvancedSearch != null)
				booleanExpression = Util.and(booleanExpression, booleanExpressionAdvancedSearch);
		}

		return booleanExpression;
	}

	public Page<Kullanici> sorgula(int pageSize, int pageNumber, Map<String, SortMeta> sort, Kullanici kullanici, String sorgu) {
		BooleanExpression booleanExpression = kriter(kullanici, sorgu);

		JPAQueryFactory queryFactory = new JPAQueryFactory(em);
		JPAQuery<Kullanici> query = queryFactory.
				select(QKullanici.kullanici).
				from(QKullanici.kullanici);

		if (booleanExpression != null)
			query = query.where(booleanExpression);

		long total = query.fetchCount();

		if (sort != null) {
			for (Map.Entry<String, SortMeta> entry : sort.entrySet()) {
				PathBuilder<Kullanici> builder = new PathBuilder<Kullanici>(QKullanici.kullanici.getType(), QKullanici.kullanici.getMetadata());

				if (entry.getValue().getOrder().isAscending()) {
					query.orderBy(builder.getString(entry.getKey()).asc());
				} else {
					query.orderBy(builder.getString(entry.getKey()).desc());
				}
			}
		}

		query = query.offset(pageNumber * pageSize).limit(pageSize);
		List<Kullanici> list = (List<Kullanici>) query.fetch();

		return new PageImpl<>(list, PageRequest.of(pageNumber, pageSize), total);
	}

	public List<Kullanici> combo(String sorgu) {
		BooleanExpression booleanExpression = null;

		if(Util.notEmpty(sorgu)) {
			booleanExpression = Util.or(booleanExpression, QKullanici.kullanici.ad_soyad.likeIgnoreCase(sorgu+"%"));
		}

		JPAQueryFactory queryFactory = new JPAQueryFactory(em);
		JPAQuery<Kullanici> query = queryFactory.
				select(QKullanici.kullanici).
				from(QKullanici.kullanici).
				limit(Util.LIMIT);

		if (booleanExpression != null)
			query = query.where(booleanExpression);

		return (List<Kullanici>) query.fetch();
	}

	public Kullanici kullaniciAdiIleGetir(String kullaniciAdi) {
		if(Util.empty(kullaniciAdi)) {
			return null;
		}

		BooleanExpression booleanExpression = QKullanici.kullanici.kullanici_adi.equalsIgnoreCase(kullaniciAdi);

		JPAQueryFactory queryFactory = new JPAQueryFactory(em);
		JPAQuery<Kullanici> query = queryFactory.
				select(QKullanici.kullanici).
				from(QKullanici.kullanici).
				limit(1);

		if (booleanExpression != null)
			query = query.where(booleanExpression);

		List<Kullanici> sonuc = (List<Kullanici>) query.fetch();
		return sonuc.size() > 0 ? sonuc.get(0) : null;
	}

}

