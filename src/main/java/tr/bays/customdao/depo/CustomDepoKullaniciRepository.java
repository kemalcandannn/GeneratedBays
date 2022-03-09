package tr.bays.customdao.depo;

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

import tr.bays.entity.depo.DepoKullanici;
import tr.bays.entity.depo.QDepoKullanici;
import util.Util;

@Repository
public class CustomDepoKullaniciRepository {

	@PersistenceContext
	private EntityManager em;

	public BooleanExpression kriter(DepoKullanici depoKullanici, String sorgu) {
		BooleanExpression booleanExpression = null;

		if(depoKullanici.getKullanici_id() != null && depoKullanici.getKullanici_id().getId() != null && depoKullanici.getKullanici_id().getId() != 0) {
			booleanExpression = Util.and(booleanExpression,QDepoKullanici.depoKullanici.kullanici_id.id.eq(depoKullanici.getKullanici_id().getId()));
		}

		if(depoKullanici.getDepo_id() != null && depoKullanici.getDepo_id().getId() != null && depoKullanici.getDepo_id().getId() != 0) {
			booleanExpression = Util.and(booleanExpression,QDepoKullanici.depoKullanici.depo_id.id.eq(depoKullanici.getDepo_id().getId()));
		}

		if(Util.notEmpty(sorgu)) {
			BooleanExpression booleanExpressionAdvancedSearch = null;

			if(booleanExpressionAdvancedSearch != null)
				booleanExpression = Util.and(booleanExpression, booleanExpressionAdvancedSearch);
		}

		return booleanExpression;
	}

	public Page<DepoKullanici> sorgula(int pageSize, int pageNumber, Map<String, SortMeta> sort, DepoKullanici depoKullanici, String sorgu) {
		BooleanExpression booleanExpression = kriter(depoKullanici, sorgu);

		JPAQueryFactory queryFactory = new JPAQueryFactory(em);
		JPAQuery<DepoKullanici> query = queryFactory.
				select(QDepoKullanici.depoKullanici).
				from(QDepoKullanici.depoKullanici);

		if (booleanExpression != null)
			query = query.where(booleanExpression);

		long total = query.fetchCount();

		if (sort != null) {
			for (Map.Entry<String, SortMeta> entry : sort.entrySet()) {
				PathBuilder<DepoKullanici> builder = new PathBuilder<DepoKullanici>(QDepoKullanici.depoKullanici.getType(), QDepoKullanici.depoKullanici.getMetadata());

				if (entry.getValue().getOrder().isAscending()) {
					query.orderBy(builder.getString(entry.getKey()).asc());
				} else {
					query.orderBy(builder.getString(entry.getKey()).desc());
				}
			}
		}

		query = query.offset(pageNumber * pageSize).limit(pageSize);
		List<DepoKullanici> list = (List<DepoKullanici>) query.fetch();

		return new PageImpl<>(list, PageRequest.of(pageNumber, pageSize), total);
	}

	public List<DepoKullanici> combo(String sorgu) {
		BooleanExpression booleanExpression = null;

		JPAQueryFactory queryFactory = new JPAQueryFactory(em);
		JPAQuery<DepoKullanici> query = queryFactory.
				select(QDepoKullanici.depoKullanici).
				from(QDepoKullanici.depoKullanici).
				limit(Util.LIMIT);

		if (booleanExpression != null)
			query = query.where(booleanExpression);

		return (List<DepoKullanici>) query.fetch();
	}

}

