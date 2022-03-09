package tr.bays.customdao.depoTalep;

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

import tr.bays.entity.depoTalep.DepoTalepMalzeme;
import tr.bays.entity.depoTalep.QDepoTalepMalzeme;
import util.Util;

@Repository
public class CustomDepoTalepMalzemeRepository {

	@PersistenceContext
	private EntityManager em;

	public BooleanExpression kriter(DepoTalepMalzeme depoTalepMalzeme, String sorgu) {
		BooleanExpression booleanExpression = null;

		if(depoTalepMalzeme.getTur() != 0) {
			booleanExpression = Util.and(booleanExpression,QDepoTalepMalzeme.depoTalepMalzeme.tur.eq(depoTalepMalzeme.getTur()));
		}

		if(depoTalepMalzeme.getKlasor_id() != null && depoTalepMalzeme.getKlasor_id().getId() != null && depoTalepMalzeme.getKlasor_id().getId() != 0) {
			booleanExpression = Util.and(booleanExpression,QDepoTalepMalzeme.depoTalepMalzeme.klasor_id.id.eq(depoTalepMalzeme.getKlasor_id().getId()));
		}

		if(depoTalepMalzeme.getGomlek_id() != null && depoTalepMalzeme.getGomlek_id().getId() != null && depoTalepMalzeme.getGomlek_id().getId() != 0) {
			booleanExpression = Util.and(booleanExpression,QDepoTalepMalzeme.depoTalepMalzeme.gomlek_id.id.eq(depoTalepMalzeme.getGomlek_id().getId()));
		}

		if(depoTalepMalzeme.getDefter_id() != null && depoTalepMalzeme.getDefter_id().getId() != null && depoTalepMalzeme.getDefter_id().getId() != 0) {
			booleanExpression = Util.and(booleanExpression,QDepoTalepMalzeme.depoTalepMalzeme.defter_id.id.eq(depoTalepMalzeme.getDefter_id().getId()));
		}

		if(depoTalepMalzeme.getArsiv_materyali_id() != null && depoTalepMalzeme.getArsiv_materyali_id().getId() != null && depoTalepMalzeme.getArsiv_materyali_id().getId() != 0) {
			booleanExpression = Util.and(booleanExpression,QDepoTalepMalzeme.depoTalepMalzeme.arsiv_materyali_id.id.eq(depoTalepMalzeme.getArsiv_materyali_id().getId()));
		}

		if(depoTalepMalzeme.getIslenmemis_klasor_id() != null && depoTalepMalzeme.getIslenmemis_klasor_id().getId() != null && depoTalepMalzeme.getIslenmemis_klasor_id().getId() != 0) {
			booleanExpression = Util.and(booleanExpression,QDepoTalepMalzeme.depoTalepMalzeme.islenmemis_klasor_id.id.eq(depoTalepMalzeme.getIslenmemis_klasor_id().getId()));
		}

		if(depoTalepMalzeme.getDepo_talep_id() != null && depoTalepMalzeme.getDepo_talep_id().getId() != null && depoTalepMalzeme.getDepo_talep_id().getId() != 0) {
			booleanExpression = Util.and(booleanExpression,QDepoTalepMalzeme.depoTalepMalzeme.depo_talep_id.id.eq(depoTalepMalzeme.getDepo_talep_id().getId()));
		}

		if(Util.notEmpty(sorgu)) {
			BooleanExpression booleanExpressionAdvancedSearch = null;

			if(booleanExpressionAdvancedSearch != null)
				booleanExpression = Util.and(booleanExpression, booleanExpressionAdvancedSearch);
		}

		return booleanExpression;
	}

	public Page<DepoTalepMalzeme> sorgula(int pageSize, int pageNumber, Map<String, SortMeta> sort, DepoTalepMalzeme depoTalepMalzeme, String sorgu) {
		BooleanExpression booleanExpression = kriter(depoTalepMalzeme, sorgu);

		JPAQueryFactory queryFactory = new JPAQueryFactory(em);
		JPAQuery<DepoTalepMalzeme> query = queryFactory.
				select(QDepoTalepMalzeme.depoTalepMalzeme).
				from(QDepoTalepMalzeme.depoTalepMalzeme);

		if (booleanExpression != null)
			query = query.where(booleanExpression);

		long total = query.fetchCount();

		if (sort != null) {
			for (Map.Entry<String, SortMeta> entry : sort.entrySet()) {
				PathBuilder<DepoTalepMalzeme> builder = new PathBuilder<DepoTalepMalzeme>(QDepoTalepMalzeme.depoTalepMalzeme.getType(), QDepoTalepMalzeme.depoTalepMalzeme.getMetadata());

				if (entry.getValue().getOrder().isAscending()) {
					query.orderBy(builder.getString(entry.getKey()).asc());
				} else {
					query.orderBy(builder.getString(entry.getKey()).desc());
				}
			}
		}

		query = query.offset(pageNumber * pageSize).limit(pageSize);
		List<DepoTalepMalzeme> list = (List<DepoTalepMalzeme>) query.fetch();

		return new PageImpl<>(list, PageRequest.of(pageNumber, pageSize), total);
	}

	public List<DepoTalepMalzeme> combo(String sorgu) {
		BooleanExpression booleanExpression = null;

		JPAQueryFactory queryFactory = new JPAQueryFactory(em);
		JPAQuery<DepoTalepMalzeme> query = queryFactory.
				select(QDepoTalepMalzeme.depoTalepMalzeme).
				from(QDepoTalepMalzeme.depoTalepMalzeme).
				limit(Util.LIMIT);

		if (booleanExpression != null)
			query = query.where(booleanExpression);

		return (List<DepoTalepMalzeme>) query.fetch();
	}

}

