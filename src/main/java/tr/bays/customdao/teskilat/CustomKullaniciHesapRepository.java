package tr.bays.customdao.teskilat;

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

import tr.bays.entity.teskilat.KullaniciHesap;
import tr.bays.entity.teskilat.QKullaniciHesap;
import util.Util;

@Repository
public class CustomKullaniciHesapRepository {

	@PersistenceContext
	private EntityManager em;

	public BooleanExpression kriter(KullaniciHesap kullaniciHesap, String sorgu) {
		BooleanExpression booleanExpression = null;

		if(Util.notEmpty(kullaniciHesap.getKullanici_adi())) {
			booleanExpression = Util.and(booleanExpression,QKullaniciHesap.kullaniciHesap.kullanici_adi.likeIgnoreCase(kullaniciHesap.getKullanici_adi()+"%"));
		}

		if(Util.notEmpty(kullaniciHesap.getParola())) {
			booleanExpression = Util.and(booleanExpression,QKullaniciHesap.kullaniciHesap.parola.likeIgnoreCase(kullaniciHesap.getParola()+"%"));
		}

		if(kullaniciHesap.getAktif() != 0) {
			booleanExpression = Util.and(booleanExpression,QKullaniciHesap.kullaniciHesap.aktif.eq(kullaniciHesap.getAktif()));
		}

		if(kullaniciHesap.getOnay_durumu() != 0) {
			booleanExpression = Util.and(booleanExpression,QKullaniciHesap.kullaniciHesap.onay_durumu.eq(kullaniciHesap.getOnay_durumu()));
		}

		if(kullaniciHesap.getKullanici_id() != null && kullaniciHesap.getKullanici_id().getId() != null && kullaniciHesap.getKullanici_id().getId() != 0) {
			booleanExpression = Util.and(booleanExpression,QKullaniciHesap.kullaniciHesap.kullanici_id.id.eq(kullaniciHesap.getKullanici_id().getId()));
		}

		if(Util.notEmpty(sorgu)) {
			BooleanExpression booleanExpressionAdvancedSearch = null;
			booleanExpressionAdvancedSearch = Util.or(booleanExpressionAdvancedSearch, QKullaniciHesap.kullaniciHesap.kullanici_adi.likeIgnoreCase("%" + sorgu + "%"));
			booleanExpressionAdvancedSearch = Util.or(booleanExpressionAdvancedSearch, QKullaniciHesap.kullaniciHesap.parola.likeIgnoreCase("%" + sorgu + "%"));

			if(booleanExpressionAdvancedSearch != null)
				booleanExpression = Util.and(booleanExpression, booleanExpressionAdvancedSearch);
		}

		return booleanExpression;
	}

	public Page<KullaniciHesap> sorgula(int pageSize, int pageNumber, Map<String, SortMeta> sort, KullaniciHesap kullaniciHesap, String sorgu) {
		BooleanExpression booleanExpression = kriter(kullaniciHesap, sorgu);

		JPAQueryFactory queryFactory = new JPAQueryFactory(em);
		JPAQuery<KullaniciHesap> query = queryFactory.
				select(QKullaniciHesap.kullaniciHesap).
				from(QKullaniciHesap.kullaniciHesap);

		if (booleanExpression != null)
			query = query.where(booleanExpression);

		long total = query.fetchCount();

		if (sort != null) {
			for (Map.Entry<String, SortMeta> entry : sort.entrySet()) {
				PathBuilder<KullaniciHesap> builder = new PathBuilder<KullaniciHesap>(QKullaniciHesap.kullaniciHesap.getType(), QKullaniciHesap.kullaniciHesap.getMetadata());

				if (entry.getValue().getOrder().isAscending()) {
					query.orderBy(builder.getString(entry.getKey()).asc());
				} else {
					query.orderBy(builder.getString(entry.getKey()).desc());
				}
			}
		}

		query = query.offset(pageNumber * pageSize).limit(pageSize);
		List<KullaniciHesap> list = (List<KullaniciHesap>) query.fetch();

		return new PageImpl<>(list, PageRequest.of(pageNumber, pageSize), total);
	}

	public List<KullaniciHesap> combo(String sorgu) {
		BooleanExpression booleanExpression = null;

		if(Util.notEmpty(sorgu)) {
			booleanExpression = Util.or(booleanExpression, QKullaniciHesap.kullaniciHesap.kullanici_adi.likeIgnoreCase(sorgu+"%"));
		}

		JPAQueryFactory queryFactory = new JPAQueryFactory(em);
		JPAQuery<KullaniciHesap> query = queryFactory.
				select(QKullaniciHesap.kullaniciHesap).
				from(QKullaniciHesap.kullaniciHesap).
				limit(Util.LIMIT);

		if (booleanExpression != null)
			query = query.where(booleanExpression);

		return (List<KullaniciHesap>) query.fetch();
	}

}

