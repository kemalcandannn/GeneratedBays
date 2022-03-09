package tr.bays.customdao.arsivMateryali;

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

import tr.bays.entity.arsivMateryali.ArsivMateryaliAlan;
import tr.bays.entity.arsivMateryali.QArsivMateryaliAlan;
import util.Util;

@Repository
public class CustomArsivMateryaliAlanRepository {

	@PersistenceContext
	private EntityManager em;

	public BooleanExpression kriter(ArsivMateryaliAlan arsivMateryaliAlan, String sorgu) {
		BooleanExpression booleanExpression = null;

		if(Util.notEmpty(arsivMateryaliAlan.getDeger())) {
			booleanExpression = Util.and(booleanExpression,QArsivMateryaliAlan.arsivMateryaliAlan.deger.likeIgnoreCase(arsivMateryaliAlan.getDeger()+"%"));
		}

		if(arsivMateryaliAlan.getAlan_id() != null && arsivMateryaliAlan.getAlan_id().getId() != null && arsivMateryaliAlan.getAlan_id().getId() != 0) {
			booleanExpression = Util.and(booleanExpression,QArsivMateryaliAlan.arsivMateryaliAlan.alan_id.id.eq(arsivMateryaliAlan.getAlan_id().getId()));
		}

		if(arsivMateryaliAlan.getArsiv_materyali_id() != null && arsivMateryaliAlan.getArsiv_materyali_id().getId() != null && arsivMateryaliAlan.getArsiv_materyali_id().getId() != 0) {
			booleanExpression = Util.and(booleanExpression,QArsivMateryaliAlan.arsivMateryaliAlan.arsiv_materyali_id.id.eq(arsivMateryaliAlan.getArsiv_materyali_id().getId()));
		}

		if(Util.notEmpty(sorgu)) {
			BooleanExpression booleanExpressionAdvancedSearch = null;
			booleanExpressionAdvancedSearch = Util.or(booleanExpressionAdvancedSearch, QArsivMateryaliAlan.arsivMateryaliAlan.deger.likeIgnoreCase("%" + sorgu + "%"));

			if(booleanExpressionAdvancedSearch != null)
				booleanExpression = Util.and(booleanExpression, booleanExpressionAdvancedSearch);
		}

		return booleanExpression;
	}

	public Page<ArsivMateryaliAlan> sorgula(int pageSize, int pageNumber, Map<String, SortMeta> sort, ArsivMateryaliAlan arsivMateryaliAlan, String sorgu) {
		BooleanExpression booleanExpression = kriter(arsivMateryaliAlan, sorgu);

		JPAQueryFactory queryFactory = new JPAQueryFactory(em);
		JPAQuery<ArsivMateryaliAlan> query = queryFactory.
				select(QArsivMateryaliAlan.arsivMateryaliAlan).
				from(QArsivMateryaliAlan.arsivMateryaliAlan);

		if (booleanExpression != null)
			query = query.where(booleanExpression);

		long total = query.fetchCount();

		if (sort != null) {
			for (Map.Entry<String, SortMeta> entry : sort.entrySet()) {
				PathBuilder<ArsivMateryaliAlan> builder = new PathBuilder<ArsivMateryaliAlan>(QArsivMateryaliAlan.arsivMateryaliAlan.getType(), QArsivMateryaliAlan.arsivMateryaliAlan.getMetadata());

				if (entry.getValue().getOrder().isAscending()) {
					query.orderBy(builder.getString(entry.getKey()).asc());
				} else {
					query.orderBy(builder.getString(entry.getKey()).desc());
				}
			}
		}

		query = query.offset(pageNumber * pageSize).limit(pageSize);
		List<ArsivMateryaliAlan> list = (List<ArsivMateryaliAlan>) query.fetch();

		return new PageImpl<>(list, PageRequest.of(pageNumber, pageSize), total);
	}

	public List<ArsivMateryaliAlan> combo(String sorgu) {
		BooleanExpression booleanExpression = null;

		if(Util.notEmpty(sorgu)) {
			booleanExpression = Util.or(booleanExpression, QArsivMateryaliAlan.arsivMateryaliAlan.deger.likeIgnoreCase(sorgu+"%"));
		}

		JPAQueryFactory queryFactory = new JPAQueryFactory(em);
		JPAQuery<ArsivMateryaliAlan> query = queryFactory.
				select(QArsivMateryaliAlan.arsivMateryaliAlan).
				from(QArsivMateryaliAlan.arsivMateryaliAlan).
				limit(Util.LIMIT);

		if (booleanExpression != null)
			query = query.where(booleanExpression);

		return (List<ArsivMateryaliAlan>) query.fetch();
	}

}

