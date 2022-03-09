package tr.bays.customdao.fon;

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

import tr.bays.entity.fon.FonAlan;
import tr.bays.entity.fon.QFonAlan;
import util.Util;

@Repository
public class CustomFonAlanRepository {

	@PersistenceContext
	private EntityManager em;

	public BooleanExpression kriter(FonAlan fonAlan, String sorgu) {
		BooleanExpression booleanExpression = null;

		if(Util.notEmpty(fonAlan.getDeger())) {
			booleanExpression = Util.and(booleanExpression,QFonAlan.fonAlan.deger.likeIgnoreCase(fonAlan.getDeger()+"%"));
		}

		if(fonAlan.getAlan_id() != null && fonAlan.getAlan_id().getId() != null && fonAlan.getAlan_id().getId() != 0) {
			booleanExpression = Util.and(booleanExpression,QFonAlan.fonAlan.alan_id.id.eq(fonAlan.getAlan_id().getId()));
		}

		if(fonAlan.getFon_id() != null && fonAlan.getFon_id().getId() != null && fonAlan.getFon_id().getId() != 0) {
			booleanExpression = Util.and(booleanExpression,QFonAlan.fonAlan.fon_id.id.eq(fonAlan.getFon_id().getId()));
		}

		if(Util.notEmpty(sorgu)) {
			BooleanExpression booleanExpressionAdvancedSearch = null;
			booleanExpressionAdvancedSearch = Util.or(booleanExpressionAdvancedSearch, QFonAlan.fonAlan.deger.likeIgnoreCase("%" + sorgu + "%"));

			if(booleanExpressionAdvancedSearch != null)
				booleanExpression = Util.and(booleanExpression, booleanExpressionAdvancedSearch);
		}

		return booleanExpression;
	}

	public Page<FonAlan> sorgula(int pageSize, int pageNumber, Map<String, SortMeta> sort, FonAlan fonAlan, String sorgu) {
		BooleanExpression booleanExpression = kriter(fonAlan, sorgu);

		JPAQueryFactory queryFactory = new JPAQueryFactory(em);
		JPAQuery<FonAlan> query = queryFactory.
				select(QFonAlan.fonAlan).
				from(QFonAlan.fonAlan);

		if (booleanExpression != null)
			query = query.where(booleanExpression);

		long total = query.fetchCount();

		if (sort != null) {
			for (Map.Entry<String, SortMeta> entry : sort.entrySet()) {
				PathBuilder<FonAlan> builder = new PathBuilder<FonAlan>(QFonAlan.fonAlan.getType(), QFonAlan.fonAlan.getMetadata());

				if (entry.getValue().getOrder().isAscending()) {
					query.orderBy(builder.getString(entry.getKey()).asc());
				} else {
					query.orderBy(builder.getString(entry.getKey()).desc());
				}
			}
		}

		query = query.offset(pageNumber * pageSize).limit(pageSize);
		List<FonAlan> list = (List<FonAlan>) query.fetch();

		return new PageImpl<>(list, PageRequest.of(pageNumber, pageSize), total);
	}

	public List<FonAlan> combo(String sorgu) {
		BooleanExpression booleanExpression = null;

		if(Util.notEmpty(sorgu)) {
			booleanExpression = Util.or(booleanExpression, QFonAlan.fonAlan.deger.likeIgnoreCase(sorgu+"%"));
		}

		JPAQueryFactory queryFactory = new JPAQueryFactory(em);
		JPAQuery<FonAlan> query = queryFactory.
				select(QFonAlan.fonAlan).
				from(QFonAlan.fonAlan).
				limit(Util.LIMIT);

		if (booleanExpression != null)
			query = query.where(booleanExpression);

		return (List<FonAlan>) query.fetch();
	}

}

