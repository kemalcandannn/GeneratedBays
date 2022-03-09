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

import tr.bays.entity.arsivMateryali.ArsivMateryali;
import tr.bays.entity.arsivMateryali.QArsivMateryali;
import util.Util;

@Repository
public class CustomArsivMateryaliRepository {

	@PersistenceContext
	private EntityManager em;

	public BooleanExpression kriter(ArsivMateryali arsivMateryali, String sorgu) {
		BooleanExpression booleanExpression = null;

		if(arsivMateryali.getTur() != 0) {
			booleanExpression = Util.and(booleanExpression,QArsivMateryali.arsivMateryali.tur.eq(arsivMateryali.getTur()));
		}

		if(Util.notEmpty(arsivMateryali.getMateryal_no())) {
			booleanExpression = Util.and(booleanExpression,QArsivMateryali.arsivMateryali.materyal_no.likeIgnoreCase(arsivMateryali.getMateryal_no()+"%"));
		}

		if(Util.notEmpty(arsivMateryali.getEk_no())) {
			booleanExpression = Util.and(booleanExpression,QArsivMateryali.arsivMateryali.ek_no.likeIgnoreCase(arsivMateryali.getEk_no()+"%"));
		}

		if(arsivMateryali.getGizlilik() != 0) {
			booleanExpression = Util.and(booleanExpression,QArsivMateryali.arsivMateryali.gizlilik.eq(arsivMateryali.getGizlilik()));
		}

		if(arsivMateryali.getFon_id() != null && arsivMateryali.getFon_id().getId() != null && arsivMateryali.getFon_id().getId() != 0) {
			booleanExpression = Util.and(booleanExpression,QArsivMateryali.arsivMateryali.fon_id.id.eq(arsivMateryali.getFon_id().getId()));
		}

		if(arsivMateryali.getDepo_id() != null && arsivMateryali.getDepo_id().getId() != null && arsivMateryali.getDepo_id().getId() != 0) {
			booleanExpression = Util.and(booleanExpression,QArsivMateryali.arsivMateryali.depo_id.id.eq(arsivMateryali.getDepo_id().getId()));
		}

		if(Util.notEmpty(sorgu)) {
			BooleanExpression booleanExpressionAdvancedSearch = null;
			booleanExpressionAdvancedSearch = Util.or(booleanExpressionAdvancedSearch, QArsivMateryali.arsivMateryali.materyal_no.likeIgnoreCase("%" + sorgu + "%"));
			booleanExpressionAdvancedSearch = Util.or(booleanExpressionAdvancedSearch, QArsivMateryali.arsivMateryali.ek_no.likeIgnoreCase("%" + sorgu + "%"));

			if(booleanExpressionAdvancedSearch != null)
				booleanExpression = Util.and(booleanExpression, booleanExpressionAdvancedSearch);
		}

		return booleanExpression;
	}

	public Page<ArsivMateryali> sorgula(int pageSize, int pageNumber, Map<String, SortMeta> sort, ArsivMateryali arsivMateryali, String sorgu) {
		BooleanExpression booleanExpression = kriter(arsivMateryali, sorgu);

		JPAQueryFactory queryFactory = new JPAQueryFactory(em);
		JPAQuery<ArsivMateryali> query = queryFactory.
				select(QArsivMateryali.arsivMateryali).
				from(QArsivMateryali.arsivMateryali);

		if (booleanExpression != null)
			query = query.where(booleanExpression);

		long total = query.fetchCount();

		if (sort != null) {
			for (Map.Entry<String, SortMeta> entry : sort.entrySet()) {
				PathBuilder<ArsivMateryali> builder = new PathBuilder<ArsivMateryali>(QArsivMateryali.arsivMateryali.getType(), QArsivMateryali.arsivMateryali.getMetadata());

				if (entry.getValue().getOrder().isAscending()) {
					query.orderBy(builder.getString(entry.getKey()).asc());
				} else {
					query.orderBy(builder.getString(entry.getKey()).desc());
				}
			}
		}

		query = query.offset(pageNumber * pageSize).limit(pageSize);
		List<ArsivMateryali> list = (List<ArsivMateryali>) query.fetch();

		return new PageImpl<>(list, PageRequest.of(pageNumber, pageSize), total);
	}

	public List<ArsivMateryali> combo(String sorgu) {
		BooleanExpression booleanExpression = null;

		if(Util.notEmpty(sorgu)) {
			booleanExpression = Util.or(booleanExpression, QArsivMateryali.arsivMateryali.materyal_no.likeIgnoreCase(sorgu+"%"));
		}

		JPAQueryFactory queryFactory = new JPAQueryFactory(em);
		JPAQuery<ArsivMateryali> query = queryFactory.
				select(QArsivMateryali.arsivMateryali).
				from(QArsivMateryali.arsivMateryali).
				limit(Util.LIMIT);

		if (booleanExpression != null)
			query = query.where(booleanExpression);

		return (List<ArsivMateryali>) query.fetch();
	}

}

