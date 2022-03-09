package tr.bays.customdao.depoMuhafaza;

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

import tr.bays.entity.depoMuhafaza.TopluTasima;
import tr.bays.entity.depoMuhafaza.QTopluTasima;
import util.Util;

@Repository
public class CustomTopluTasimaRepository {

	@PersistenceContext
	private EntityManager em;

	public BooleanExpression kriter(TopluTasima topluTasima, String sorgu) {
		BooleanExpression booleanExpression = null;

		if(Util.notEmpty(topluTasima.getTarih_saat())) {
			booleanExpression = Util.and(booleanExpression,QTopluTasima.topluTasima.tarih_saat.eq(topluTasima.getTarih_saat()));
		}

		if(Util.notEmpty(topluTasima.getIp())) {
			booleanExpression = Util.and(booleanExpression,QTopluTasima.topluTasima.ip.likeIgnoreCase(topluTasima.getIp()+"%"));
		}

		if(topluTasima.getKullanici_id() != null && topluTasima.getKullanici_id().getId() != null && topluTasima.getKullanici_id().getId() != 0) {
			booleanExpression = Util.and(booleanExpression,QTopluTasima.topluTasima.kullanici_id.id.eq(topluTasima.getKullanici_id().getId()));
		}

		if(Util.notEmpty(sorgu)) {
			BooleanExpression booleanExpressionAdvancedSearch = null;
			booleanExpressionAdvancedSearch = Util.or(booleanExpressionAdvancedSearch, QTopluTasima.topluTasima.ip.likeIgnoreCase("%" + sorgu + "%"));

			if(booleanExpressionAdvancedSearch != null)
				booleanExpression = Util.and(booleanExpression, booleanExpressionAdvancedSearch);
		}

		return booleanExpression;
	}

	public Page<TopluTasima> sorgula(int pageSize, int pageNumber, Map<String, SortMeta> sort, TopluTasima topluTasima, String sorgu) {
		BooleanExpression booleanExpression = kriter(topluTasima, sorgu);

		JPAQueryFactory queryFactory = new JPAQueryFactory(em);
		JPAQuery<TopluTasima> query = queryFactory.
				select(QTopluTasima.topluTasima).
				from(QTopluTasima.topluTasima);

		if (booleanExpression != null)
			query = query.where(booleanExpression);

		long total = query.fetchCount();

		if (sort != null) {
			for (Map.Entry<String, SortMeta> entry : sort.entrySet()) {
				PathBuilder<TopluTasima> builder = new PathBuilder<TopluTasima>(QTopluTasima.topluTasima.getType(), QTopluTasima.topluTasima.getMetadata());

				if (entry.getValue().getOrder().isAscending()) {
					query.orderBy(builder.getString(entry.getKey()).asc());
				} else {
					query.orderBy(builder.getString(entry.getKey()).desc());
				}
			}
		}

		query = query.offset(pageNumber * pageSize).limit(pageSize);
		List<TopluTasima> list = (List<TopluTasima>) query.fetch();

		return new PageImpl<>(list, PageRequest.of(pageNumber, pageSize), total);
	}

	public List<TopluTasima> combo(String sorgu) {
		BooleanExpression booleanExpression = null;

		if(Util.notEmpty(sorgu)) {
			booleanExpression = Util.or(booleanExpression, QTopluTasima.topluTasima.ip.likeIgnoreCase(sorgu+"%"));
		}

		JPAQueryFactory queryFactory = new JPAQueryFactory(em);
		JPAQuery<TopluTasima> query = queryFactory.
				select(QTopluTasima.topluTasima).
				from(QTopluTasima.topluTasima).
				limit(Util.LIMIT);

		if (booleanExpression != null)
			query = query.where(booleanExpression);

		return (List<TopluTasima>) query.fetch();
	}

}

