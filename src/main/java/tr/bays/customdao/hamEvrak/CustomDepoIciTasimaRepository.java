package tr.bays.customdao.hamEvrak;

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

import tr.bays.entity.hamEvrak.DepoIciTasima;
import tr.bays.entity.hamEvrak.QDepoIciTasima;
import util.Util;

@Repository
public class CustomDepoIciTasimaRepository {

	@PersistenceContext
	private EntityManager em;

	public BooleanExpression kriter(DepoIciTasima depoIciTasima, String sorgu) {
		BooleanExpression booleanExpression = null;

		if(Util.notEmpty(depoIciTasima.getTarih_saat())) {
			booleanExpression = Util.and(booleanExpression,QDepoIciTasima.depoIciTasima.tarih_saat.eq(depoIciTasima.getTarih_saat()));
		}

		if(depoIciTasima.getAdet() != 0) {
			booleanExpression = Util.and(booleanExpression,QDepoIciTasima.depoIciTasima.adet.eq(depoIciTasima.getAdet()));
		}

		if(depoIciTasima.getKullanici_id() != null && depoIciTasima.getKullanici_id().getId() != null && depoIciTasima.getKullanici_id().getId() != 0) {
			booleanExpression = Util.and(booleanExpression,QDepoIciTasima.depoIciTasima.kullanici_id.id.eq(depoIciTasima.getKullanici_id().getId()));
		}

		if(depoIciTasima.getKaynak_id() != null && depoIciTasima.getKaynak_id().getId() != null && depoIciTasima.getKaynak_id().getId() != 0) {
			booleanExpression = Util.and(booleanExpression,QDepoIciTasima.depoIciTasima.kaynak_id.id.eq(depoIciTasima.getKaynak_id().getId()));
		}

		if(depoIciTasima.getHedef_id() != null && depoIciTasima.getHedef_id().getId() != null && depoIciTasima.getHedef_id().getId() != 0) {
			booleanExpression = Util.and(booleanExpression,QDepoIciTasima.depoIciTasima.hedef_id.id.eq(depoIciTasima.getHedef_id().getId()));
		}

		if(Util.notEmpty(sorgu)) {
			BooleanExpression booleanExpressionAdvancedSearch = null;

			if(booleanExpressionAdvancedSearch != null)
				booleanExpression = Util.and(booleanExpression, booleanExpressionAdvancedSearch);
		}

		return booleanExpression;
	}

	public Page<DepoIciTasima> sorgula(int pageSize, int pageNumber, Map<String, SortMeta> sort, DepoIciTasima depoIciTasima, String sorgu) {
		BooleanExpression booleanExpression = kriter(depoIciTasima, sorgu);

		JPAQueryFactory queryFactory = new JPAQueryFactory(em);
		JPAQuery<DepoIciTasima> query = queryFactory.
				select(QDepoIciTasima.depoIciTasima).
				from(QDepoIciTasima.depoIciTasima);

		if (booleanExpression != null)
			query = query.where(booleanExpression);

		long total = query.fetchCount();

		if (sort != null) {
			for (Map.Entry<String, SortMeta> entry : sort.entrySet()) {
				PathBuilder<DepoIciTasima> builder = new PathBuilder<DepoIciTasima>(QDepoIciTasima.depoIciTasima.getType(), QDepoIciTasima.depoIciTasima.getMetadata());

				if (entry.getValue().getOrder().isAscending()) {
					query.orderBy(builder.getString(entry.getKey()).asc());
				} else {
					query.orderBy(builder.getString(entry.getKey()).desc());
				}
			}
		}

		query = query.offset(pageNumber * pageSize).limit(pageSize);
		List<DepoIciTasima> list = (List<DepoIciTasima>) query.fetch();

		return new PageImpl<>(list, PageRequest.of(pageNumber, pageSize), total);
	}

	public List<DepoIciTasima> combo(String sorgu) {
		BooleanExpression booleanExpression = null;

		JPAQueryFactory queryFactory = new JPAQueryFactory(em);
		JPAQuery<DepoIciTasima> query = queryFactory.
				select(QDepoIciTasima.depoIciTasima).
				from(QDepoIciTasima.depoIciTasima).
				limit(Util.LIMIT);

		if (booleanExpression != null)
			query = query.where(booleanExpression);

		return (List<DepoIciTasima>) query.fetch();
	}

}

