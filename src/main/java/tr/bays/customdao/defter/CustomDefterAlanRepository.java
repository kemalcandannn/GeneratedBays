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

import tr.bays.entity.defter.DefterAlan;
import tr.bays.entity.defter.QDefterAlan;
import util.Util;

@Repository
public class CustomDefterAlanRepository {

	@PersistenceContext
	private EntityManager em;

	public BooleanExpression kriter(DefterAlan defterAlan, String sorgu) {
		BooleanExpression booleanExpression = null;

		if(Util.notEmpty(defterAlan.getDeger())) {
			booleanExpression = Util.and(booleanExpression,QDefterAlan.defterAlan.deger.likeIgnoreCase(defterAlan.getDeger()+"%"));
		}

		if(defterAlan.getAlan_id() != null && defterAlan.getAlan_id().getId() != null && defterAlan.getAlan_id().getId() != 0) {
			booleanExpression = Util.and(booleanExpression,QDefterAlan.defterAlan.alan_id.id.eq(defterAlan.getAlan_id().getId()));
		}

		if(defterAlan.getDefter_id() != null && defterAlan.getDefter_id().getId() != null && defterAlan.getDefter_id().getId() != 0) {
			booleanExpression = Util.and(booleanExpression,QDefterAlan.defterAlan.defter_id.id.eq(defterAlan.getDefter_id().getId()));
		}

		if(Util.notEmpty(sorgu)) {
			BooleanExpression booleanExpressionAdvancedSearch = null;
			booleanExpressionAdvancedSearch = Util.or(booleanExpressionAdvancedSearch, QDefterAlan.defterAlan.deger.likeIgnoreCase("%" + sorgu + "%"));

			if(booleanExpressionAdvancedSearch != null)
				booleanExpression = Util.and(booleanExpression, booleanExpressionAdvancedSearch);
		}

		return booleanExpression;
	}

	public Page<DefterAlan> sorgula(int pageSize, int pageNumber, Map<String, SortMeta> sort, DefterAlan defterAlan, String sorgu) {
		BooleanExpression booleanExpression = kriter(defterAlan, sorgu);

		JPAQueryFactory queryFactory = new JPAQueryFactory(em);
		JPAQuery<DefterAlan> query = queryFactory.
				select(QDefterAlan.defterAlan).
				from(QDefterAlan.defterAlan);

		if (booleanExpression != null)
			query = query.where(booleanExpression);

		long total = query.fetchCount();

		if (sort != null) {
			for (Map.Entry<String, SortMeta> entry : sort.entrySet()) {
				PathBuilder<DefterAlan> builder = new PathBuilder<DefterAlan>(QDefterAlan.defterAlan.getType(), QDefterAlan.defterAlan.getMetadata());

				if (entry.getValue().getOrder().isAscending()) {
					query.orderBy(builder.getString(entry.getKey()).asc());
				} else {
					query.orderBy(builder.getString(entry.getKey()).desc());
				}
			}
		}

		query = query.offset(pageNumber * pageSize).limit(pageSize);
		List<DefterAlan> list = (List<DefterAlan>) query.fetch();

		return new PageImpl<>(list, PageRequest.of(pageNumber, pageSize), total);
	}

	public List<DefterAlan> combo(String sorgu) {
		BooleanExpression booleanExpression = null;

		if(Util.notEmpty(sorgu)) {
			booleanExpression = Util.or(booleanExpression, QDefterAlan.defterAlan.deger.likeIgnoreCase(sorgu+"%"));
		}

		JPAQueryFactory queryFactory = new JPAQueryFactory(em);
		JPAQuery<DefterAlan> query = queryFactory.
				select(QDefterAlan.defterAlan).
				from(QDefterAlan.defterAlan).
				limit(Util.LIMIT);

		if (booleanExpression != null)
			query = query.where(booleanExpression);

		return (List<DefterAlan>) query.fetch();
	}

}

