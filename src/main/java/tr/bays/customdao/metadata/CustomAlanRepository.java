package tr.bays.customdao.metadata;

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

import tr.bays.entity.metadata.Alan;
import tr.bays.entity.metadata.QAlan;
import util.Util;

@Repository
public class CustomAlanRepository {

	@PersistenceContext
	private EntityManager em;

	public BooleanExpression kriter(Alan alan, String sorgu) {
		BooleanExpression booleanExpression = null;

		if(Util.notEmpty(alan.getAd())) {
			booleanExpression = Util.and(booleanExpression,QAlan.alan.ad.likeIgnoreCase(alan.getAd()+"%"));
		}

		if(alan.getMetadata_seti_id() != null && alan.getMetadata_seti_id().getId() != null && alan.getMetadata_seti_id().getId() != 0) {
			booleanExpression = Util.and(booleanExpression,QAlan.alan.metadata_seti_id.id.eq(alan.getMetadata_seti_id().getId()));
		}

		if(Util.notEmpty(sorgu)) {
			BooleanExpression booleanExpressionAdvancedSearch = null;
			booleanExpressionAdvancedSearch = Util.or(booleanExpressionAdvancedSearch, QAlan.alan.ad.likeIgnoreCase("%" + sorgu + "%"));

			if(booleanExpressionAdvancedSearch != null)
				booleanExpression = Util.and(booleanExpression, booleanExpressionAdvancedSearch);
		}

		return booleanExpression;
	}

	public Page<Alan> sorgula(int pageSize, int pageNumber, Map<String, SortMeta> sort, Alan alan, String sorgu) {
		BooleanExpression booleanExpression = kriter(alan, sorgu);

		JPAQueryFactory queryFactory = new JPAQueryFactory(em);
		JPAQuery<Alan> query = queryFactory.
				select(QAlan.alan).
				from(QAlan.alan);

		if (booleanExpression != null)
			query = query.where(booleanExpression);

		long total = query.fetchCount();

		if (sort != null) {
			for (Map.Entry<String, SortMeta> entry : sort.entrySet()) {
				PathBuilder<Alan> builder = new PathBuilder<Alan>(QAlan.alan.getType(), QAlan.alan.getMetadata());

				if (entry.getValue().getOrder().isAscending()) {
					query.orderBy(builder.getString(entry.getKey()).asc());
				} else {
					query.orderBy(builder.getString(entry.getKey()).desc());
				}
			}
		}

		query = query.offset(pageNumber * pageSize).limit(pageSize);
		List<Alan> list = (List<Alan>) query.fetch();

		return new PageImpl<>(list, PageRequest.of(pageNumber, pageSize), total);
	}

	public List<Alan> combo(String sorgu) {
		BooleanExpression booleanExpression = null;

		if(Util.notEmpty(sorgu)) {
			booleanExpression = Util.or(booleanExpression, QAlan.alan.ad.likeIgnoreCase(sorgu+"%"));
		}

		JPAQueryFactory queryFactory = new JPAQueryFactory(em);
		JPAQuery<Alan> query = queryFactory.
				select(QAlan.alan).
				from(QAlan.alan).
				limit(Util.LIMIT);

		if (booleanExpression != null)
			query = query.where(booleanExpression);

		return (List<Alan>) query.fetch();
	}

}

