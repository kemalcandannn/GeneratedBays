package tr.bays.customdao.arsivSahibi;

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

import tr.bays.entity.arsivSahibi.PersonelHesapBilgileri;
import tr.bays.entity.arsivSahibi.QPersonelHesapBilgileri;
import util.Util;

@Repository
public class CustomPersonelHesapBilgileriRepository {

	@PersistenceContext
	private EntityManager em;

	public BooleanExpression kriter(PersonelHesapBilgileri personelHesapBilgileri, String sorgu) {
		BooleanExpression booleanExpression = null;

		if(Util.notEmpty(personelHesapBilgileri.getKullanici_adi())) {
			booleanExpression = Util.and(booleanExpression,QPersonelHesapBilgileri.personelHesapBilgileri.kullanici_adi.likeIgnoreCase(personelHesapBilgileri.getKullanici_adi()+"%"));
		}

		if(Util.notEmpty(personelHesapBilgileri.getParola())) {
			booleanExpression = Util.and(booleanExpression,QPersonelHesapBilgileri.personelHesapBilgileri.parola.likeIgnoreCase(personelHesapBilgileri.getParola()+"%"));
		}

		if(personelHesapBilgileri.getAktif() != 0) {
			booleanExpression = Util.and(booleanExpression,QPersonelHesapBilgileri.personelHesapBilgileri.aktif.eq(personelHesapBilgileri.getAktif()));
		}

		if(personelHesapBilgileri.getOnay_durumu() != 0) {
			booleanExpression = Util.and(booleanExpression,QPersonelHesapBilgileri.personelHesapBilgileri.onay_durumu.eq(personelHesapBilgileri.getOnay_durumu()));
		}

		if(personelHesapBilgileri.getArsiv_sahibi_personel_id() != null && personelHesapBilgileri.getArsiv_sahibi_personel_id().getId() != null && personelHesapBilgileri.getArsiv_sahibi_personel_id().getId() != 0) {
			booleanExpression = Util.and(booleanExpression,QPersonelHesapBilgileri.personelHesapBilgileri.arsiv_sahibi_personel_id.id.eq(personelHesapBilgileri.getArsiv_sahibi_personel_id().getId()));
		}

		if(Util.notEmpty(sorgu)) {
			BooleanExpression booleanExpressionAdvancedSearch = null;
			booleanExpressionAdvancedSearch = Util.or(booleanExpressionAdvancedSearch, QPersonelHesapBilgileri.personelHesapBilgileri.kullanici_adi.likeIgnoreCase("%" + sorgu + "%"));
			booleanExpressionAdvancedSearch = Util.or(booleanExpressionAdvancedSearch, QPersonelHesapBilgileri.personelHesapBilgileri.parola.likeIgnoreCase("%" + sorgu + "%"));

			if(booleanExpressionAdvancedSearch != null)
				booleanExpression = Util.and(booleanExpression, booleanExpressionAdvancedSearch);
		}

		return booleanExpression;
	}

	public Page<PersonelHesapBilgileri> sorgula(int pageSize, int pageNumber, Map<String, SortMeta> sort, PersonelHesapBilgileri personelHesapBilgileri, String sorgu) {
		BooleanExpression booleanExpression = kriter(personelHesapBilgileri, sorgu);

		JPAQueryFactory queryFactory = new JPAQueryFactory(em);
		JPAQuery<PersonelHesapBilgileri> query = queryFactory.
				select(QPersonelHesapBilgileri.personelHesapBilgileri).
				from(QPersonelHesapBilgileri.personelHesapBilgileri);

		if (booleanExpression != null)
			query = query.where(booleanExpression);

		long total = query.fetchCount();

		if (sort != null) {
			for (Map.Entry<String, SortMeta> entry : sort.entrySet()) {
				PathBuilder<PersonelHesapBilgileri> builder = new PathBuilder<PersonelHesapBilgileri>(QPersonelHesapBilgileri.personelHesapBilgileri.getType(), QPersonelHesapBilgileri.personelHesapBilgileri.getMetadata());

				if (entry.getValue().getOrder().isAscending()) {
					query.orderBy(builder.getString(entry.getKey()).asc());
				} else {
					query.orderBy(builder.getString(entry.getKey()).desc());
				}
			}
		}

		query = query.offset(pageNumber * pageSize).limit(pageSize);
		List<PersonelHesapBilgileri> list = (List<PersonelHesapBilgileri>) query.fetch();

		return new PageImpl<>(list, PageRequest.of(pageNumber, pageSize), total);
	}

	public List<PersonelHesapBilgileri> combo(String sorgu) {
		BooleanExpression booleanExpression = null;

		if(Util.notEmpty(sorgu)) {
			booleanExpression = Util.or(booleanExpression, QPersonelHesapBilgileri.personelHesapBilgileri.kullanici_adi.likeIgnoreCase(sorgu+"%"));
		}

		JPAQueryFactory queryFactory = new JPAQueryFactory(em);
		JPAQuery<PersonelHesapBilgileri> query = queryFactory.
				select(QPersonelHesapBilgileri.personelHesapBilgileri).
				from(QPersonelHesapBilgileri.personelHesapBilgileri).
				limit(Util.LIMIT);

		if (booleanExpression != null)
			query = query.where(booleanExpression);

		return (List<PersonelHesapBilgileri>) query.fetch();
	}

}

