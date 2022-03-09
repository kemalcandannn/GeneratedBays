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

import tr.bays.entity.gomlek.GomlekEnvanter;
import tr.bays.entity.gomlek.QGomlekEnvanter;
import util.Util;

@Repository
public class CustomGomlekEnvanterRepository {

	@PersistenceContext
	private EntityManager em;

	public BooleanExpression kriter(GomlekEnvanter gomlekEnvanter, String sorgu) {
		BooleanExpression booleanExpression = null;

		if(gomlekEnvanter.getTespit_edilmis_belge_sayisi() != 0) {
			booleanExpression = Util.and(booleanExpression,QGomlekEnvanter.gomlekEnvanter.tespit_edilmis_belge_sayisi.eq(gomlekEnvanter.getTespit_edilmis_belge_sayisi()));
		}

		if(gomlekEnvanter.getTespit_edilmis_sayfa_sayisi() != 0) {
			booleanExpression = Util.and(booleanExpression,QGomlekEnvanter.gomlekEnvanter.tespit_edilmis_sayfa_sayisi.eq(gomlekEnvanter.getTespit_edilmis_sayfa_sayisi()));
		}

		if(gomlekEnvanter.getGomlek_id() != null && gomlekEnvanter.getGomlek_id().getId() != null && gomlekEnvanter.getGomlek_id().getId() != 0) {
			booleanExpression = Util.and(booleanExpression,QGomlekEnvanter.gomlekEnvanter.gomlek_id.id.eq(gomlekEnvanter.getGomlek_id().getId()));
		}

		if(Util.notEmpty(sorgu)) {
			BooleanExpression booleanExpressionAdvancedSearch = null;

			if(booleanExpressionAdvancedSearch != null)
				booleanExpression = Util.and(booleanExpression, booleanExpressionAdvancedSearch);
		}

		return booleanExpression;
	}

	public Page<GomlekEnvanter> sorgula(int pageSize, int pageNumber, Map<String, SortMeta> sort, GomlekEnvanter gomlekEnvanter, String sorgu) {
		BooleanExpression booleanExpression = kriter(gomlekEnvanter, sorgu);

		JPAQueryFactory queryFactory = new JPAQueryFactory(em);
		JPAQuery<GomlekEnvanter> query = queryFactory.
				select(QGomlekEnvanter.gomlekEnvanter).
				from(QGomlekEnvanter.gomlekEnvanter);

		if (booleanExpression != null)
			query = query.where(booleanExpression);

		long total = query.fetchCount();

		if (sort != null) {
			for (Map.Entry<String, SortMeta> entry : sort.entrySet()) {
				PathBuilder<GomlekEnvanter> builder = new PathBuilder<GomlekEnvanter>(QGomlekEnvanter.gomlekEnvanter.getType(), QGomlekEnvanter.gomlekEnvanter.getMetadata());

				if (entry.getValue().getOrder().isAscending()) {
					query.orderBy(builder.getString(entry.getKey()).asc());
				} else {
					query.orderBy(builder.getString(entry.getKey()).desc());
				}
			}
		}

		query = query.offset(pageNumber * pageSize).limit(pageSize);
		List<GomlekEnvanter> list = (List<GomlekEnvanter>) query.fetch();

		return new PageImpl<>(list, PageRequest.of(pageNumber, pageSize), total);
	}

	public List<GomlekEnvanter> combo(String sorgu) {
		BooleanExpression booleanExpression = null;

		JPAQueryFactory queryFactory = new JPAQueryFactory(em);
		JPAQuery<GomlekEnvanter> query = queryFactory.
				select(QGomlekEnvanter.gomlekEnvanter).
				from(QGomlekEnvanter.gomlekEnvanter).
				limit(Util.LIMIT);

		if (booleanExpression != null)
			query = query.where(booleanExpression);

		return (List<GomlekEnvanter>) query.fetch();
	}

}

