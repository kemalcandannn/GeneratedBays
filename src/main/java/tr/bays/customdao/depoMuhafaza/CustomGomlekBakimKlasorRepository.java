package tr.bays.customdao.depoMuhafaza;

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

import tr.bays.entity.depoMuhafaza.GomlekBakimKlasor;
import tr.bays.entity.depoMuhafaza.QGomlekBakimKlasor;
import util.Util;

@Repository
public class CustomGomlekBakimKlasorRepository {

	@PersistenceContext
	private EntityManager em;

	public BooleanExpression kriter(GomlekBakimKlasor gomlekBakimKlasor, String sorgu) {
		BooleanExpression booleanExpression = null;

		if(gomlekBakimKlasor.getDurum() != 0) {
			booleanExpression = Util.and(booleanExpression,QGomlekBakimKlasor.gomlekBakimKlasor.durum.eq(gomlekBakimKlasor.getDurum()));
		}

		if(gomlekBakimKlasor.getKlasor_id() != null && gomlekBakimKlasor.getKlasor_id().getId() != null && gomlekBakimKlasor.getKlasor_id().getId() != 0) {
			booleanExpression = Util.and(booleanExpression,QGomlekBakimKlasor.gomlekBakimKlasor.klasor_id.id.eq(gomlekBakimKlasor.getKlasor_id().getId()));
		}

		if(gomlekBakimKlasor.getPlanlanan_id() != null && gomlekBakimKlasor.getPlanlanan_id().getId() != null && gomlekBakimKlasor.getPlanlanan_id().getId() != 0) {
			booleanExpression = Util.and(booleanExpression,QGomlekBakimKlasor.gomlekBakimKlasor.planlanan_id.id.eq(gomlekBakimKlasor.getPlanlanan_id().getId()));
		}

		if(gomlekBakimKlasor.getAtanan_id() != null && gomlekBakimKlasor.getAtanan_id().getId() != null && gomlekBakimKlasor.getAtanan_id().getId() != 0) {
			booleanExpression = Util.and(booleanExpression,QGomlekBakimKlasor.gomlekBakimKlasor.atanan_id.id.eq(gomlekBakimKlasor.getAtanan_id().getId()));
		}

		if(gomlekBakimKlasor.getYenilenen_id() != null && gomlekBakimKlasor.getYenilenen_id().getId() != null && gomlekBakimKlasor.getYenilenen_id().getId() != 0) {
			booleanExpression = Util.and(booleanExpression,QGomlekBakimKlasor.gomlekBakimKlasor.yenilenen_id.id.eq(gomlekBakimKlasor.getYenilenen_id().getId()));
		}

		if(Util.notEmpty(sorgu)) {
			BooleanExpression booleanExpressionAdvancedSearch = null;

			if(booleanExpressionAdvancedSearch != null)
				booleanExpression = Util.and(booleanExpression, booleanExpressionAdvancedSearch);
		}

		return booleanExpression;
	}

	public Page<GomlekBakimKlasor> sorgula(int pageSize, int pageNumber, Map<String, SortMeta> sort, GomlekBakimKlasor gomlekBakimKlasor, String sorgu) {
		BooleanExpression booleanExpression = kriter(gomlekBakimKlasor, sorgu);

		JPAQueryFactory queryFactory = new JPAQueryFactory(em);
		JPAQuery<GomlekBakimKlasor> query = queryFactory.
				select(QGomlekBakimKlasor.gomlekBakimKlasor).
				from(QGomlekBakimKlasor.gomlekBakimKlasor);

		if (booleanExpression != null)
			query = query.where(booleanExpression);

		long total = query.fetchCount();

		if (sort != null) {
			for (Map.Entry<String, SortMeta> entry : sort.entrySet()) {
				PathBuilder<GomlekBakimKlasor> builder = new PathBuilder<GomlekBakimKlasor>(QGomlekBakimKlasor.gomlekBakimKlasor.getType(), QGomlekBakimKlasor.gomlekBakimKlasor.getMetadata());

				if (entry.getValue().getOrder().isAscending()) {
					query.orderBy(builder.getString(entry.getKey()).asc());
				} else {
					query.orderBy(builder.getString(entry.getKey()).desc());
				}
			}
		}

		query = query.offset(pageNumber * pageSize).limit(pageSize);
		List<GomlekBakimKlasor> list = (List<GomlekBakimKlasor>) query.fetch();

		return new PageImpl<>(list, PageRequest.of(pageNumber, pageSize), total);
	}

	public List<GomlekBakimKlasor> combo(String sorgu) {
		BooleanExpression booleanExpression = null;

		JPAQueryFactory queryFactory = new JPAQueryFactory(em);
		JPAQuery<GomlekBakimKlasor> query = queryFactory.
				select(QGomlekBakimKlasor.gomlekBakimKlasor).
				from(QGomlekBakimKlasor.gomlekBakimKlasor).
				limit(Util.LIMIT);

		if (booleanExpression != null)
			query = query.where(booleanExpression);

		return (List<GomlekBakimKlasor>) query.fetch();
	}

}

