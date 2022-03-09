package tr.bays.customdao.defter;

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

import tr.bays.entity.defter.DefterSayfaAlan;
import tr.bays.entity.defter.QDefterSayfaAlan;
import util.Util;

@Repository
public class CustomDefterSayfaAlanRepository {

	@PersistenceContext
	private EntityManager em;

	public BooleanExpression kriter(DefterSayfaAlan defterSayfaAlan, String sorgu) {
		BooleanExpression booleanExpression = null;

		if(Util.notEmpty(defterSayfaAlan.getDeger())) {
			booleanExpression = Util.and(booleanExpression,QDefterSayfaAlan.defterSayfaAlan.deger.likeIgnoreCase(defterSayfaAlan.getDeger()+"%"));
		}

		if(defterSayfaAlan.getAlan_id() != null && defterSayfaAlan.getAlan_id().getId() != null && defterSayfaAlan.getAlan_id().getId() != 0) {
			booleanExpression = Util.and(booleanExpression,QDefterSayfaAlan.defterSayfaAlan.alan_id.id.eq(defterSayfaAlan.getAlan_id().getId()));
		}

		if(defterSayfaAlan.getDefter_id() != null && defterSayfaAlan.getDefter_id().getId() != null && defterSayfaAlan.getDefter_id().getId() != 0) {
			booleanExpression = Util.and(booleanExpression,QDefterSayfaAlan.defterSayfaAlan.defter_id.id.eq(defterSayfaAlan.getDefter_id().getId()));
		}

		if(Util.notEmpty(sorgu)) {
			BooleanExpression booleanExpressionAdvancedSearch = null;
			booleanExpressionAdvancedSearch = Util.or(booleanExpressionAdvancedSearch, QDefterSayfaAlan.defterSayfaAlan.deger.likeIgnoreCase("%" + sorgu + "%"));

			if(booleanExpressionAdvancedSearch != null)
				booleanExpression = Util.and(booleanExpression, booleanExpressionAdvancedSearch);
		}

		return booleanExpression;
	}

	public Page<DefterSayfaAlan> sorgula(int pageSize, int pageNumber, Map<String, SortMeta> sort, DefterSayfaAlan defterSayfaAlan, String sorgu) {
		BooleanExpression booleanExpression = kriter(defterSayfaAlan, sorgu);

		JPAQueryFactory queryFactory = new JPAQueryFactory(em);
		JPAQuery<DefterSayfaAlan> query = queryFactory.
				select(QDefterSayfaAlan.defterSayfaAlan).
				from(QDefterSayfaAlan.defterSayfaAlan);

		if (booleanExpression != null)
			query = query.where(booleanExpression);

		long total = query.fetchCount();

		if (sort != null) {
			for (Map.Entry<String, SortMeta> entry : sort.entrySet()) {
				PathBuilder<DefterSayfaAlan> builder = new PathBuilder<DefterSayfaAlan>(QDefterSayfaAlan.defterSayfaAlan.getType(), QDefterSayfaAlan.defterSayfaAlan.getMetadata());

				if (entry.getValue().getOrder().isAscending()) {
					query.orderBy(builder.getString(entry.getKey()).asc());
				} else {
					query.orderBy(builder.getString(entry.getKey()).desc());
				}
			}
		}

		query = query.offset(pageNumber * pageSize).limit(pageSize);
		List<DefterSayfaAlan> list = (List<DefterSayfaAlan>) query.fetch();

		return new PageImpl<>(list, PageRequest.of(pageNumber, pageSize), total);
	}

	public List<DefterSayfaAlan> combo(String sorgu) {
		BooleanExpression booleanExpression = null;

		if(Util.notEmpty(sorgu)) {
			booleanExpression = Util.or(booleanExpression, QDefterSayfaAlan.defterSayfaAlan.deger.likeIgnoreCase(sorgu+"%"));
		}

		JPAQueryFactory queryFactory = new JPAQueryFactory(em);
		JPAQuery<DefterSayfaAlan> query = queryFactory.
				select(QDefterSayfaAlan.defterSayfaAlan).
				from(QDefterSayfaAlan.defterSayfaAlan).
				limit(Util.LIMIT);

		if (booleanExpression != null)
			query = query.where(booleanExpression);

		return (List<DefterSayfaAlan>) query.fetch();
	}

}

