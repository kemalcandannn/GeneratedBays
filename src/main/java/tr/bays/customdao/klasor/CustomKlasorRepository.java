package tr.bays.customdao.klasor;

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

import tr.bays.entity.klasor.Klasor;
import tr.bays.entity.klasor.QKlasor;
import util.Util;

@Repository
public class CustomKlasorRepository {

	@PersistenceContext
	private EntityManager em;

	public BooleanExpression kriter(Klasor klasor, String sorgu) {
		BooleanExpression booleanExpression = null;

		if(Util.notEmpty(klasor.getKlasor_no())) {
			booleanExpression = Util.and(booleanExpression,QKlasor.klasor.klasor_no.likeIgnoreCase(klasor.getKlasor_no()+"%"));
		}

		if(Util.notEmpty(klasor.getKlasor_ek_no())) {
			booleanExpression = Util.and(booleanExpression,QKlasor.klasor.klasor_ek_no.likeIgnoreCase(klasor.getKlasor_ek_no()+"%"));
		}

		if(klasor.getGizlilik() != 0) {
			booleanExpression = Util.and(booleanExpression,QKlasor.klasor.gizlilik.eq(klasor.getGizlilik()));
		}

		if(klasor.getFon_id() != null && klasor.getFon_id().getId() != null && klasor.getFon_id().getId() != 0) {
			booleanExpression = Util.and(booleanExpression,QKlasor.klasor.fon_id.id.eq(klasor.getFon_id().getId()));
		}

		if(klasor.getUst_klasor_id() != null && klasor.getUst_klasor_id().getId() != null && klasor.getUst_klasor_id().getId() != 0) {
			booleanExpression = Util.and(booleanExpression,QKlasor.klasor.ust_klasor_id.id.eq(klasor.getUst_klasor_id().getId()));
		}

		if(klasor.getDepo_id() != null && klasor.getDepo_id().getId() != null && klasor.getDepo_id().getId() != 0) {
			booleanExpression = Util.and(booleanExpression,QKlasor.klasor.depo_id.id.eq(klasor.getDepo_id().getId()));
		}

		if(Util.notEmpty(sorgu)) {
			BooleanExpression booleanExpressionAdvancedSearch = null;
			booleanExpressionAdvancedSearch = Util.or(booleanExpressionAdvancedSearch, QKlasor.klasor.klasor_no.likeIgnoreCase("%" + sorgu + "%"));
			booleanExpressionAdvancedSearch = Util.or(booleanExpressionAdvancedSearch, QKlasor.klasor.klasor_ek_no.likeIgnoreCase("%" + sorgu + "%"));

			if(booleanExpressionAdvancedSearch != null)
				booleanExpression = Util.and(booleanExpression, booleanExpressionAdvancedSearch);
		}

		return booleanExpression;
	}

	public Page<Klasor> sorgula(int pageSize, int pageNumber, Map<String, SortMeta> sort, Klasor klasor, String sorgu) {
		BooleanExpression booleanExpression = kriter(klasor, sorgu);

		JPAQueryFactory queryFactory = new JPAQueryFactory(em);
		JPAQuery<Klasor> query = queryFactory.
				select(QKlasor.klasor).
				from(QKlasor.klasor);

		if (booleanExpression != null)
			query = query.where(booleanExpression);

		long total = query.fetchCount();

		if (sort != null) {
			for (Map.Entry<String, SortMeta> entry : sort.entrySet()) {
				PathBuilder<Klasor> builder = new PathBuilder<Klasor>(QKlasor.klasor.getType(), QKlasor.klasor.getMetadata());

				if (entry.getValue().getOrder().isAscending()) {
					query.orderBy(builder.getString(entry.getKey()).asc());
				} else {
					query.orderBy(builder.getString(entry.getKey()).desc());
				}
			}
		}

		query = query.offset(pageNumber * pageSize).limit(pageSize);
		List<Klasor> list = (List<Klasor>) query.fetch();

		return new PageImpl<>(list, PageRequest.of(pageNumber, pageSize), total);
	}

	public List<Klasor> combo(String sorgu) {
		BooleanExpression booleanExpression = null;

		if(Util.notEmpty(sorgu)) {
			booleanExpression = Util.or(booleanExpression, QKlasor.klasor.klasor_no.likeIgnoreCase(sorgu+"%"));
		}

		JPAQueryFactory queryFactory = new JPAQueryFactory(em);
		JPAQuery<Klasor> query = queryFactory.
				select(QKlasor.klasor).
				from(QKlasor.klasor).
				limit(Util.LIMIT);

		if (booleanExpression != null)
			query = query.where(booleanExpression);

		return (List<Klasor>) query.fetch();
	}

}

