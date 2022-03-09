package tr.bays.customdao.hamEvrak;

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

import tr.bays.entity.hamEvrak.HamEvrak;
import tr.bays.entity.hamEvrak.QHamEvrak;
import util.Util;

@Repository
public class CustomHamEvrakRepository {

	@PersistenceContext
	private EntityManager em;

	public BooleanExpression kriter(HamEvrak hamEvrak, String sorgu) {
		BooleanExpression booleanExpression = null;

		if(hamEvrak.getSaklanma_turu() != 0) {
			booleanExpression = Util.and(booleanExpression,QHamEvrak.hamEvrak.saklanma_turu.eq(hamEvrak.getSaklanma_turu()));
		}

		if(hamEvrak.getAdet() != 0) {
			booleanExpression = Util.and(booleanExpression,QHamEvrak.hamEvrak.adet.eq(hamEvrak.getAdet()));
		}

		if(hamEvrak.getFon_id() != null && hamEvrak.getFon_id().getId() != null && hamEvrak.getFon_id().getId() != 0) {
			booleanExpression = Util.and(booleanExpression,QHamEvrak.hamEvrak.fon_id.id.eq(hamEvrak.getFon_id().getId()));
		}

		if(hamEvrak.getBas_depo_id() != null && hamEvrak.getBas_depo_id().getId() != null && hamEvrak.getBas_depo_id().getId() != 0) {
			booleanExpression = Util.and(booleanExpression,QHamEvrak.hamEvrak.bas_depo_id.id.eq(hamEvrak.getBas_depo_id().getId()));
		}

		if(hamEvrak.getBit_depo_id() != null && hamEvrak.getBit_depo_id().getId() != null && hamEvrak.getBit_depo_id().getId() != 0) {
			booleanExpression = Util.and(booleanExpression,QHamEvrak.hamEvrak.bit_depo_id.id.eq(hamEvrak.getBit_depo_id().getId()));
		}

		if(Util.notEmpty(sorgu)) {
			BooleanExpression booleanExpressionAdvancedSearch = null;

			if(booleanExpressionAdvancedSearch != null)
				booleanExpression = Util.and(booleanExpression, booleanExpressionAdvancedSearch);
		}

		return booleanExpression;
	}

	public Page<HamEvrak> sorgula(int pageSize, int pageNumber, Map<String, SortMeta> sort, HamEvrak hamEvrak, String sorgu) {
		BooleanExpression booleanExpression = kriter(hamEvrak, sorgu);

		JPAQueryFactory queryFactory = new JPAQueryFactory(em);
		JPAQuery<HamEvrak> query = queryFactory.
				select(QHamEvrak.hamEvrak).
				from(QHamEvrak.hamEvrak);

		if (booleanExpression != null)
			query = query.where(booleanExpression);

		long total = query.fetchCount();

		if (sort != null) {
			for (Map.Entry<String, SortMeta> entry : sort.entrySet()) {
				PathBuilder<HamEvrak> builder = new PathBuilder<HamEvrak>(QHamEvrak.hamEvrak.getType(), QHamEvrak.hamEvrak.getMetadata());

				if (entry.getValue().getOrder().isAscending()) {
					query.orderBy(builder.getString(entry.getKey()).asc());
				} else {
					query.orderBy(builder.getString(entry.getKey()).desc());
				}
			}
		}

		query = query.offset(pageNumber * pageSize).limit(pageSize);
		List<HamEvrak> list = (List<HamEvrak>) query.fetch();

		return new PageImpl<>(list, PageRequest.of(pageNumber, pageSize), total);
	}

	public List<HamEvrak> combo(String sorgu) {
		BooleanExpression booleanExpression = null;

		JPAQueryFactory queryFactory = new JPAQueryFactory(em);
		JPAQuery<HamEvrak> query = queryFactory.
				select(QHamEvrak.hamEvrak).
				from(QHamEvrak.hamEvrak).
				limit(Util.LIMIT);

		if (booleanExpression != null)
			query = query.where(booleanExpression);

		return (List<HamEvrak>) query.fetch();
	}

}

