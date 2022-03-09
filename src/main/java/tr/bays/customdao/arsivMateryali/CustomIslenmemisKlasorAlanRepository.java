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

import tr.bays.entity.arsivMateryali.IslenmemisKlasorAlan;
import tr.bays.entity.arsivMateryali.QIslenmemisKlasorAlan;
import util.Util;

@Repository
public class CustomIslenmemisKlasorAlanRepository {

	@PersistenceContext
	private EntityManager em;

	public BooleanExpression kriter(IslenmemisKlasorAlan islenmemisKlasorAlan, String sorgu) {
		BooleanExpression booleanExpression = null;

		if(Util.notEmpty(islenmemisKlasorAlan.getDeger())) {
			booleanExpression = Util.and(booleanExpression,QIslenmemisKlasorAlan.islenmemisKlasorAlan.deger.likeIgnoreCase(islenmemisKlasorAlan.getDeger()+"%"));
		}

		if(islenmemisKlasorAlan.getAlan_id() != null && islenmemisKlasorAlan.getAlan_id().getId() != null && islenmemisKlasorAlan.getAlan_id().getId() != 0) {
			booleanExpression = Util.and(booleanExpression,QIslenmemisKlasorAlan.islenmemisKlasorAlan.alan_id.id.eq(islenmemisKlasorAlan.getAlan_id().getId()));
		}

		if(islenmemisKlasorAlan.getIslenmemis_klasor_id() != null && islenmemisKlasorAlan.getIslenmemis_klasor_id().getId() != null && islenmemisKlasorAlan.getIslenmemis_klasor_id().getId() != 0) {
			booleanExpression = Util.and(booleanExpression,QIslenmemisKlasorAlan.islenmemisKlasorAlan.islenmemis_klasor_id.id.eq(islenmemisKlasorAlan.getIslenmemis_klasor_id().getId()));
		}

		if(Util.notEmpty(sorgu)) {
			BooleanExpression booleanExpressionAdvancedSearch = null;
			booleanExpressionAdvancedSearch = Util.or(booleanExpressionAdvancedSearch, QIslenmemisKlasorAlan.islenmemisKlasorAlan.deger.likeIgnoreCase("%" + sorgu + "%"));

			if(booleanExpressionAdvancedSearch != null)
				booleanExpression = Util.and(booleanExpression, booleanExpressionAdvancedSearch);
		}

		return booleanExpression;
	}

	public Page<IslenmemisKlasorAlan> sorgula(int pageSize, int pageNumber, Map<String, SortMeta> sort, IslenmemisKlasorAlan islenmemisKlasorAlan, String sorgu) {
		BooleanExpression booleanExpression = kriter(islenmemisKlasorAlan, sorgu);

		JPAQueryFactory queryFactory = new JPAQueryFactory(em);
		JPAQuery<IslenmemisKlasorAlan> query = queryFactory.
				select(QIslenmemisKlasorAlan.islenmemisKlasorAlan).
				from(QIslenmemisKlasorAlan.islenmemisKlasorAlan);

		if (booleanExpression != null)
			query = query.where(booleanExpression);

		long total = query.fetchCount();

		if (sort != null) {
			for (Map.Entry<String, SortMeta> entry : sort.entrySet()) {
				PathBuilder<IslenmemisKlasorAlan> builder = new PathBuilder<IslenmemisKlasorAlan>(QIslenmemisKlasorAlan.islenmemisKlasorAlan.getType(), QIslenmemisKlasorAlan.islenmemisKlasorAlan.getMetadata());

				if (entry.getValue().getOrder().isAscending()) {
					query.orderBy(builder.getString(entry.getKey()).asc());
				} else {
					query.orderBy(builder.getString(entry.getKey()).desc());
				}
			}
		}

		query = query.offset(pageNumber * pageSize).limit(pageSize);
		List<IslenmemisKlasorAlan> list = (List<IslenmemisKlasorAlan>) query.fetch();

		return new PageImpl<>(list, PageRequest.of(pageNumber, pageSize), total);
	}

	public List<IslenmemisKlasorAlan> combo(String sorgu) {
		BooleanExpression booleanExpression = null;

		if(Util.notEmpty(sorgu)) {
			booleanExpression = Util.or(booleanExpression, QIslenmemisKlasorAlan.islenmemisKlasorAlan.deger.likeIgnoreCase(sorgu+"%"));
		}

		JPAQueryFactory queryFactory = new JPAQueryFactory(em);
		JPAQuery<IslenmemisKlasorAlan> query = queryFactory.
				select(QIslenmemisKlasorAlan.islenmemisKlasorAlan).
				from(QIslenmemisKlasorAlan.islenmemisKlasorAlan).
				limit(Util.LIMIT);

		if (booleanExpression != null)
			query = query.where(booleanExpression);

		return (List<IslenmemisKlasorAlan>) query.fetch();
	}

}

