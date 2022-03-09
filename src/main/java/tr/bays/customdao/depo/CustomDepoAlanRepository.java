package tr.bays.customdao.depo;

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

import tr.bays.entity.depo.DepoAlan;
import tr.bays.entity.depo.QDepoAlan;
import util.Util;

@Repository
public class CustomDepoAlanRepository {

	@PersistenceContext
	private EntityManager em;

	public BooleanExpression kriter(DepoAlan depoAlan, String sorgu) {
		BooleanExpression booleanExpression = null;

		if(Util.notEmpty(depoAlan.getDeger())) {
			booleanExpression = Util.and(booleanExpression,QDepoAlan.depoAlan.deger.likeIgnoreCase(depoAlan.getDeger()+"%"));
		}

		if(depoAlan.getAlan_id() != null && depoAlan.getAlan_id().getId() != null && depoAlan.getAlan_id().getId() != 0) {
			booleanExpression = Util.and(booleanExpression,QDepoAlan.depoAlan.alan_id.id.eq(depoAlan.getAlan_id().getId()));
		}

		if(depoAlan.getDepo_id() != null && depoAlan.getDepo_id().getId() != null && depoAlan.getDepo_id().getId() != 0) {
			booleanExpression = Util.and(booleanExpression,QDepoAlan.depoAlan.depo_id.id.eq(depoAlan.getDepo_id().getId()));
		}

		if(Util.notEmpty(sorgu)) {
			BooleanExpression booleanExpressionAdvancedSearch = null;
			booleanExpressionAdvancedSearch = Util.or(booleanExpressionAdvancedSearch, QDepoAlan.depoAlan.deger.likeIgnoreCase("%" + sorgu + "%"));

			if(booleanExpressionAdvancedSearch != null)
				booleanExpression = Util.and(booleanExpression, booleanExpressionAdvancedSearch);
		}

		return booleanExpression;
	}

	public Page<DepoAlan> sorgula(int pageSize, int pageNumber, Map<String, SortMeta> sort, DepoAlan depoAlan, String sorgu) {
		BooleanExpression booleanExpression = kriter(depoAlan, sorgu);

		JPAQueryFactory queryFactory = new JPAQueryFactory(em);
		JPAQuery<DepoAlan> query = queryFactory.
				select(QDepoAlan.depoAlan).
				from(QDepoAlan.depoAlan);

		if (booleanExpression != null)
			query = query.where(booleanExpression);

		long total = query.fetchCount();

		if (sort != null) {
			for (Map.Entry<String, SortMeta> entry : sort.entrySet()) {
				PathBuilder<DepoAlan> builder = new PathBuilder<DepoAlan>(QDepoAlan.depoAlan.getType(), QDepoAlan.depoAlan.getMetadata());

				if (entry.getValue().getOrder().isAscending()) {
					query.orderBy(builder.getString(entry.getKey()).asc());
				} else {
					query.orderBy(builder.getString(entry.getKey()).desc());
				}
			}
		}

		query = query.offset(pageNumber * pageSize).limit(pageSize);
		List<DepoAlan> list = (List<DepoAlan>) query.fetch();

		return new PageImpl<>(list, PageRequest.of(pageNumber, pageSize), total);
	}

	public List<DepoAlan> combo(String sorgu) {
		BooleanExpression booleanExpression = null;

		if(Util.notEmpty(sorgu)) {
			booleanExpression = Util.or(booleanExpression, QDepoAlan.depoAlan.deger.likeIgnoreCase(sorgu+"%"));
		}

		JPAQueryFactory queryFactory = new JPAQueryFactory(em);
		JPAQuery<DepoAlan> query = queryFactory.
				select(QDepoAlan.depoAlan).
				from(QDepoAlan.depoAlan).
				limit(Util.LIMIT);

		if (booleanExpression != null)
			query = query.where(booleanExpression);

		return (List<DepoAlan>) query.fetch();
	}

}

