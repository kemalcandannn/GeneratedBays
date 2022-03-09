package tr.bays.customdao.gomlek;

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

import tr.bays.entity.gomlek.GomlekAlan;
import tr.bays.entity.gomlek.QGomlekAlan;
import util.Util;

@Repository
public class CustomGomlekAlanRepository {

	@PersistenceContext
	private EntityManager em;

	public BooleanExpression kriter(GomlekAlan gomlekAlan, String sorgu) {
		BooleanExpression booleanExpression = null;

		if(Util.notEmpty(gomlekAlan.getDeger())) {
			booleanExpression = Util.and(booleanExpression,QGomlekAlan.gomlekAlan.deger.likeIgnoreCase(gomlekAlan.getDeger()+"%"));
		}

		if(gomlekAlan.getAlan_id() != null && gomlekAlan.getAlan_id().getId() != null && gomlekAlan.getAlan_id().getId() != 0) {
			booleanExpression = Util.and(booleanExpression,QGomlekAlan.gomlekAlan.alan_id.id.eq(gomlekAlan.getAlan_id().getId()));
		}

		if(gomlekAlan.getGomlek_id() != null && gomlekAlan.getGomlek_id().getId() != null && gomlekAlan.getGomlek_id().getId() != 0) {
			booleanExpression = Util.and(booleanExpression,QGomlekAlan.gomlekAlan.gomlek_id.id.eq(gomlekAlan.getGomlek_id().getId()));
		}

		if(Util.notEmpty(sorgu)) {
			BooleanExpression booleanExpressionAdvancedSearch = null;
			booleanExpressionAdvancedSearch = Util.or(booleanExpressionAdvancedSearch, QGomlekAlan.gomlekAlan.deger.likeIgnoreCase("%" + sorgu + "%"));

			if(booleanExpressionAdvancedSearch != null)
				booleanExpression = Util.and(booleanExpression, booleanExpressionAdvancedSearch);
		}

		return booleanExpression;
	}

	public Page<GomlekAlan> sorgula(int pageSize, int pageNumber, Map<String, SortMeta> sort, GomlekAlan gomlekAlan, String sorgu) {
		BooleanExpression booleanExpression = kriter(gomlekAlan, sorgu);

		JPAQueryFactory queryFactory = new JPAQueryFactory(em);
		JPAQuery<GomlekAlan> query = queryFactory.
				select(QGomlekAlan.gomlekAlan).
				from(QGomlekAlan.gomlekAlan);

		if (booleanExpression != null)
			query = query.where(booleanExpression);

		long total = query.fetchCount();

		if (sort != null) {
			for (Map.Entry<String, SortMeta> entry : sort.entrySet()) {
				PathBuilder<GomlekAlan> builder = new PathBuilder<GomlekAlan>(QGomlekAlan.gomlekAlan.getType(), QGomlekAlan.gomlekAlan.getMetadata());

				if (entry.getValue().getOrder().isAscending()) {
					query.orderBy(builder.getString(entry.getKey()).asc());
				} else {
					query.orderBy(builder.getString(entry.getKey()).desc());
				}
			}
		}

		query = query.offset(pageNumber * pageSize).limit(pageSize);
		List<GomlekAlan> list = (List<GomlekAlan>) query.fetch();

		return new PageImpl<>(list, PageRequest.of(pageNumber, pageSize), total);
	}

	public List<GomlekAlan> combo(String sorgu) {
		BooleanExpression booleanExpression = null;

		if(Util.notEmpty(sorgu)) {
			booleanExpression = Util.or(booleanExpression, QGomlekAlan.gomlekAlan.deger.likeIgnoreCase(sorgu+"%"));
		}

		JPAQueryFactory queryFactory = new JPAQueryFactory(em);
		JPAQuery<GomlekAlan> query = queryFactory.
				select(QGomlekAlan.gomlekAlan).
				from(QGomlekAlan.gomlekAlan).
				limit(Util.LIMIT);

		if (booleanExpression != null)
			query = query.where(booleanExpression);

		return (List<GomlekAlan>) query.fetch();
	}

}

