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

import tr.bays.entity.klasor.KlasorEnvanter;
import tr.bays.entity.klasor.QKlasorEnvanter;
import util.Util;

@Repository
public class CustomKlasorEnvanterRepository {

	@PersistenceContext
	private EntityManager em;

	public BooleanExpression kriter(KlasorEnvanter klasorEnvanter, String sorgu) {
		BooleanExpression booleanExpression = null;

		if(klasorEnvanter.getTespit_edilmis_alt_klasor_sayisi() != 0) {
			booleanExpression = Util.and(booleanExpression,QKlasorEnvanter.klasorEnvanter.tespit_edilmis_alt_klasor_sayisi.eq(klasorEnvanter.getTespit_edilmis_alt_klasor_sayisi()));
		}

		if(klasorEnvanter.getTespit_edilmis_gomlek_sayisi() != 0) {
			booleanExpression = Util.and(booleanExpression,QKlasorEnvanter.klasorEnvanter.tespit_edilmis_gomlek_sayisi.eq(klasorEnvanter.getTespit_edilmis_gomlek_sayisi()));
		}

		if(klasorEnvanter.getKayitli_alt_klasor_sayisi() != 0) {
			booleanExpression = Util.and(booleanExpression,QKlasorEnvanter.klasorEnvanter.kayitli_alt_klasor_sayisi.eq(klasorEnvanter.getKayitli_alt_klasor_sayisi()));
		}

		if(klasorEnvanter.getKayitli_gomlek_sayisi() != 0) {
			booleanExpression = Util.and(booleanExpression,QKlasorEnvanter.klasorEnvanter.kayitli_gomlek_sayisi.eq(klasorEnvanter.getKayitli_gomlek_sayisi()));
		}

		if(klasorEnvanter.getKlasor_id() != null && klasorEnvanter.getKlasor_id().getId() != null && klasorEnvanter.getKlasor_id().getId() != 0) {
			booleanExpression = Util.and(booleanExpression,QKlasorEnvanter.klasorEnvanter.klasor_id.id.eq(klasorEnvanter.getKlasor_id().getId()));
		}

		if(Util.notEmpty(sorgu)) {
			BooleanExpression booleanExpressionAdvancedSearch = null;

			if(booleanExpressionAdvancedSearch != null)
				booleanExpression = Util.and(booleanExpression, booleanExpressionAdvancedSearch);
		}

		return booleanExpression;
	}

	public Page<KlasorEnvanter> sorgula(int pageSize, int pageNumber, Map<String, SortMeta> sort, KlasorEnvanter klasorEnvanter, String sorgu) {
		BooleanExpression booleanExpression = kriter(klasorEnvanter, sorgu);

		JPAQueryFactory queryFactory = new JPAQueryFactory(em);
		JPAQuery<KlasorEnvanter> query = queryFactory.
				select(QKlasorEnvanter.klasorEnvanter).
				from(QKlasorEnvanter.klasorEnvanter);

		if (booleanExpression != null)
			query = query.where(booleanExpression);

		long total = query.fetchCount();

		if (sort != null) {
			for (Map.Entry<String, SortMeta> entry : sort.entrySet()) {
				PathBuilder<KlasorEnvanter> builder = new PathBuilder<KlasorEnvanter>(QKlasorEnvanter.klasorEnvanter.getType(), QKlasorEnvanter.klasorEnvanter.getMetadata());

				if (entry.getValue().getOrder().isAscending()) {
					query.orderBy(builder.getString(entry.getKey()).asc());
				} else {
					query.orderBy(builder.getString(entry.getKey()).desc());
				}
			}
		}

		query = query.offset(pageNumber * pageSize).limit(pageSize);
		List<KlasorEnvanter> list = (List<KlasorEnvanter>) query.fetch();

		return new PageImpl<>(list, PageRequest.of(pageNumber, pageSize), total);
	}

	public List<KlasorEnvanter> combo(String sorgu) {
		BooleanExpression booleanExpression = null;

		JPAQueryFactory queryFactory = new JPAQueryFactory(em);
		JPAQuery<KlasorEnvanter> query = queryFactory.
				select(QKlasorEnvanter.klasorEnvanter).
				from(QKlasorEnvanter.klasorEnvanter).
				limit(Util.LIMIT);

		if (booleanExpression != null)
			query = query.where(booleanExpression);

		return (List<KlasorEnvanter>) query.fetch();
	}

}

