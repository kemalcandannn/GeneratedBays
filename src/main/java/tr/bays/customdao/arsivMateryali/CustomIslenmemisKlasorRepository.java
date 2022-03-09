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

import tr.bays.entity.arsivMateryali.IslenmemisKlasor;
import tr.bays.entity.arsivMateryali.QIslenmemisKlasor;
import util.Util;

@Repository
public class CustomIslenmemisKlasorRepository {

	@PersistenceContext
	private EntityManager em;

	public BooleanExpression kriter(IslenmemisKlasor islenmemisKlasor, String sorgu) {
		BooleanExpression booleanExpression = null;

		if(Util.notEmpty(islenmemisKlasor.getKlasor_no())) {
			booleanExpression = Util.and(booleanExpression,QIslenmemisKlasor.islenmemisKlasor.klasor_no.likeIgnoreCase(islenmemisKlasor.getKlasor_no()+"%"));
		}

		if(Util.notEmpty(islenmemisKlasor.getEk_no())) {
			booleanExpression = Util.and(booleanExpression,QIslenmemisKlasor.islenmemisKlasor.ek_no.likeIgnoreCase(islenmemisKlasor.getEk_no()+"%"));
		}

		if(islenmemisKlasor.getGizlilik() != 0) {
			booleanExpression = Util.and(booleanExpression,QIslenmemisKlasor.islenmemisKlasor.gizlilik.eq(islenmemisKlasor.getGizlilik()));
		}

		if(islenmemisKlasor.getFon_id() != null && islenmemisKlasor.getFon_id().getId() != null && islenmemisKlasor.getFon_id().getId() != 0) {
			booleanExpression = Util.and(booleanExpression,QIslenmemisKlasor.islenmemisKlasor.fon_id.id.eq(islenmemisKlasor.getFon_id().getId()));
		}

		if(islenmemisKlasor.getDepo_id() != null && islenmemisKlasor.getDepo_id().getId() != null && islenmemisKlasor.getDepo_id().getId() != 0) {
			booleanExpression = Util.and(booleanExpression,QIslenmemisKlasor.islenmemisKlasor.depo_id.id.eq(islenmemisKlasor.getDepo_id().getId()));
		}

		if(Util.notEmpty(sorgu)) {
			BooleanExpression booleanExpressionAdvancedSearch = null;
			booleanExpressionAdvancedSearch = Util.or(booleanExpressionAdvancedSearch, QIslenmemisKlasor.islenmemisKlasor.klasor_no.likeIgnoreCase("%" + sorgu + "%"));
			booleanExpressionAdvancedSearch = Util.or(booleanExpressionAdvancedSearch, QIslenmemisKlasor.islenmemisKlasor.ek_no.likeIgnoreCase("%" + sorgu + "%"));

			if(booleanExpressionAdvancedSearch != null)
				booleanExpression = Util.and(booleanExpression, booleanExpressionAdvancedSearch);
		}

		return booleanExpression;
	}

	public Page<IslenmemisKlasor> sorgula(int pageSize, int pageNumber, Map<String, SortMeta> sort, IslenmemisKlasor islenmemisKlasor, String sorgu) {
		BooleanExpression booleanExpression = kriter(islenmemisKlasor, sorgu);

		JPAQueryFactory queryFactory = new JPAQueryFactory(em);
		JPAQuery<IslenmemisKlasor> query = queryFactory.
				select(QIslenmemisKlasor.islenmemisKlasor).
				from(QIslenmemisKlasor.islenmemisKlasor);

		if (booleanExpression != null)
			query = query.where(booleanExpression);

		long total = query.fetchCount();

		if (sort != null) {
			for (Map.Entry<String, SortMeta> entry : sort.entrySet()) {
				PathBuilder<IslenmemisKlasor> builder = new PathBuilder<IslenmemisKlasor>(QIslenmemisKlasor.islenmemisKlasor.getType(), QIslenmemisKlasor.islenmemisKlasor.getMetadata());

				if (entry.getValue().getOrder().isAscending()) {
					query.orderBy(builder.getString(entry.getKey()).asc());
				} else {
					query.orderBy(builder.getString(entry.getKey()).desc());
				}
			}
		}

		query = query.offset(pageNumber * pageSize).limit(pageSize);
		List<IslenmemisKlasor> list = (List<IslenmemisKlasor>) query.fetch();

		return new PageImpl<>(list, PageRequest.of(pageNumber, pageSize), total);
	}

	public List<IslenmemisKlasor> combo(String sorgu) {
		BooleanExpression booleanExpression = null;

		if(Util.notEmpty(sorgu)) {
			booleanExpression = Util.or(booleanExpression, QIslenmemisKlasor.islenmemisKlasor.klasor_no.likeIgnoreCase(sorgu+"%"));
		}

		JPAQueryFactory queryFactory = new JPAQueryFactory(em);
		JPAQuery<IslenmemisKlasor> query = queryFactory.
				select(QIslenmemisKlasor.islenmemisKlasor).
				from(QIslenmemisKlasor.islenmemisKlasor).
				limit(Util.LIMIT);

		if (booleanExpression != null)
			query = query.where(booleanExpression);

		return (List<IslenmemisKlasor>) query.fetch();
	}

}

