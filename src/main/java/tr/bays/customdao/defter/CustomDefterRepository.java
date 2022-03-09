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

import tr.bays.entity.defter.Defter;
import tr.bays.entity.defter.QDefter;
import util.Util;

@Repository
public class CustomDefterRepository {

	@PersistenceContext
	private EntityManager em;

	public BooleanExpression kriter(Defter defter, String sorgu) {
		BooleanExpression booleanExpression = null;

		if(Util.notEmpty(defter.getDefter_no())) {
			booleanExpression = Util.and(booleanExpression,QDefter.defter.defter_no.likeIgnoreCase(defter.getDefter_no()+"%"));
		}

		if(Util.notEmpty(defter.getEk_no())) {
			booleanExpression = Util.and(booleanExpression,QDefter.defter.ek_no.likeIgnoreCase(defter.getEk_no()+"%"));
		}

		if(defter.getGizlilik() != 0) {
			booleanExpression = Util.and(booleanExpression,QDefter.defter.gizlilik.eq(defter.getGizlilik()));
		}

		if(defter.getDefter_turu_id() != null && defter.getDefter_turu_id().getId() != null && defter.getDefter_turu_id().getId() != 0) {
			booleanExpression = Util.and(booleanExpression,QDefter.defter.defter_turu_id.id.eq(defter.getDefter_turu_id().getId()));
		}

		if(defter.getFon_id() != null && defter.getFon_id().getId() != null && defter.getFon_id().getId() != 0) {
			booleanExpression = Util.and(booleanExpression,QDefter.defter.fon_id.id.eq(defter.getFon_id().getId()));
		}

		if(defter.getDepo_id() != null && defter.getDepo_id().getId() != null && defter.getDepo_id().getId() != 0) {
			booleanExpression = Util.and(booleanExpression,QDefter.defter.depo_id.id.eq(defter.getDepo_id().getId()));
		}

		if(Util.notEmpty(sorgu)) {
			BooleanExpression booleanExpressionAdvancedSearch = null;
			booleanExpressionAdvancedSearch = Util.or(booleanExpressionAdvancedSearch, QDefter.defter.defter_no.likeIgnoreCase("%" + sorgu + "%"));
			booleanExpressionAdvancedSearch = Util.or(booleanExpressionAdvancedSearch, QDefter.defter.ek_no.likeIgnoreCase("%" + sorgu + "%"));

			if(booleanExpressionAdvancedSearch != null)
				booleanExpression = Util.and(booleanExpression, booleanExpressionAdvancedSearch);
		}

		return booleanExpression;
	}

	public Page<Defter> sorgula(int pageSize, int pageNumber, Map<String, SortMeta> sort, Defter defter, String sorgu) {
		BooleanExpression booleanExpression = kriter(defter, sorgu);

		JPAQueryFactory queryFactory = new JPAQueryFactory(em);
		JPAQuery<Defter> query = queryFactory.
				select(QDefter.defter).
				from(QDefter.defter);

		if (booleanExpression != null)
			query = query.where(booleanExpression);

		long total = query.fetchCount();

		if (sort != null) {
			for (Map.Entry<String, SortMeta> entry : sort.entrySet()) {
				PathBuilder<Defter> builder = new PathBuilder<Defter>(QDefter.defter.getType(), QDefter.defter.getMetadata());

				if (entry.getValue().getOrder().isAscending()) {
					query.orderBy(builder.getString(entry.getKey()).asc());
				} else {
					query.orderBy(builder.getString(entry.getKey()).desc());
				}
			}
		}

		query = query.offset(pageNumber * pageSize).limit(pageSize);
		List<Defter> list = (List<Defter>) query.fetch();

		return new PageImpl<>(list, PageRequest.of(pageNumber, pageSize), total);
	}

	public List<Defter> combo(String sorgu) {
		BooleanExpression booleanExpression = null;

		if(Util.notEmpty(sorgu)) {
			booleanExpression = Util.or(booleanExpression, QDefter.defter.defter_no.likeIgnoreCase(sorgu+"%"));
		}

		JPAQueryFactory queryFactory = new JPAQueryFactory(em);
		JPAQuery<Defter> query = queryFactory.
				select(QDefter.defter).
				from(QDefter.defter).
				limit(Util.LIMIT);

		if (booleanExpression != null)
			query = query.where(booleanExpression);

		return (List<Defter>) query.fetch();
	}

}

