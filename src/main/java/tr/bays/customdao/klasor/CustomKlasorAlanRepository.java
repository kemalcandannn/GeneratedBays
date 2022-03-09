package tr.bays.customdao.klasor;

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

import tr.bays.entity.klasor.KlasorAlan;
import tr.bays.entity.klasor.QKlasorAlan;
import util.Util;

@Repository
public class CustomKlasorAlanRepository {

	@PersistenceContext
	private EntityManager em;

	public BooleanExpression kriter(KlasorAlan klasorAlan, String sorgu) {
		BooleanExpression booleanExpression = null;

		if(Util.notEmpty(klasorAlan.getDeger())) {
			booleanExpression = Util.and(booleanExpression,QKlasorAlan.klasorAlan.deger.likeIgnoreCase(klasorAlan.getDeger()+"%"));
		}

		if(klasorAlan.getAlan_id() != null && klasorAlan.getAlan_id().getId() != null && klasorAlan.getAlan_id().getId() != 0) {
			booleanExpression = Util.and(booleanExpression,QKlasorAlan.klasorAlan.alan_id.id.eq(klasorAlan.getAlan_id().getId()));
		}

		if(klasorAlan.getKlasor_id() != null && klasorAlan.getKlasor_id().getId() != null && klasorAlan.getKlasor_id().getId() != 0) {
			booleanExpression = Util.and(booleanExpression,QKlasorAlan.klasorAlan.klasor_id.id.eq(klasorAlan.getKlasor_id().getId()));
		}

		if(Util.notEmpty(sorgu)) {
			BooleanExpression booleanExpressionAdvancedSearch = null;
			booleanExpressionAdvancedSearch = Util.or(booleanExpressionAdvancedSearch, QKlasorAlan.klasorAlan.deger.likeIgnoreCase("%" + sorgu + "%"));

			if(booleanExpressionAdvancedSearch != null)
				booleanExpression = Util.and(booleanExpression, booleanExpressionAdvancedSearch);
		}

		return booleanExpression;
	}

	public Page<KlasorAlan> sorgula(int pageSize, int pageNumber, Map<String, SortMeta> sort, KlasorAlan klasorAlan, String sorgu) {
		BooleanExpression booleanExpression = kriter(klasorAlan, sorgu);

		JPAQueryFactory queryFactory = new JPAQueryFactory(em);
		JPAQuery<KlasorAlan> query = queryFactory.
				select(QKlasorAlan.klasorAlan).
				from(QKlasorAlan.klasorAlan);

		if (booleanExpression != null)
			query = query.where(booleanExpression);

		long total = query.fetchCount();

		if (sort != null) {
			for (Map.Entry<String, SortMeta> entry : sort.entrySet()) {
				PathBuilder<KlasorAlan> builder = new PathBuilder<KlasorAlan>(QKlasorAlan.klasorAlan.getType(), QKlasorAlan.klasorAlan.getMetadata());

				if (entry.getValue().getOrder().isAscending()) {
					query.orderBy(builder.getString(entry.getKey()).asc());
				} else {
					query.orderBy(builder.getString(entry.getKey()).desc());
				}
			}
		}

		query = query.offset(pageNumber * pageSize).limit(pageSize);
		List<KlasorAlan> list = (List<KlasorAlan>) query.fetch();

		return new PageImpl<>(list, PageRequest.of(pageNumber, pageSize), total);
	}

	public List<KlasorAlan> combo(String sorgu) {
		BooleanExpression booleanExpression = null;

		if(Util.notEmpty(sorgu)) {
			booleanExpression = Util.or(booleanExpression, QKlasorAlan.klasorAlan.deger.likeIgnoreCase(sorgu+"%"));
		}

		JPAQueryFactory queryFactory = new JPAQueryFactory(em);
		JPAQuery<KlasorAlan> query = queryFactory.
				select(QKlasorAlan.klasorAlan).
				from(QKlasorAlan.klasorAlan).
				limit(Util.LIMIT);

		if (booleanExpression != null)
			query = query.where(booleanExpression);

		return (List<KlasorAlan>) query.fetch();
	}

}

