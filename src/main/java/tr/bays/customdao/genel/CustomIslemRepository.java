package tr.bays.customdao.genel;

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

import tr.bays.entity.genel.Islem;
import tr.bays.entity.genel.QIslem;
import util.Util;

@Repository
public class CustomIslemRepository {

	@PersistenceContext
	private EntityManager em;

	public BooleanExpression kriter(Islem islem, String sorgu) {
		BooleanExpression booleanExpression = null;

		if(Util.notEmpty(islem.getAd())) {
			booleanExpression = Util.and(booleanExpression,QIslem.islem.ad.likeIgnoreCase(islem.getAd()+"%"));
		}

		if(Util.notEmpty(islem.getAciklama())) {
			booleanExpression = Util.and(booleanExpression,QIslem.islem.aciklama.likeIgnoreCase(islem.getAciklama()+"%"));
		}

		if(islem.getUst_islem_id() != null && islem.getUst_islem_id().getId() != null && islem.getUst_islem_id().getId() != 0) {
			booleanExpression = Util.and(booleanExpression,QIslem.islem.ust_islem_id.id.eq(islem.getUst_islem_id().getId()));
		}

		if(Util.notEmpty(sorgu)) {
			BooleanExpression booleanExpressionAdvancedSearch = null;
			booleanExpressionAdvancedSearch = Util.or(booleanExpressionAdvancedSearch, QIslem.islem.ad.likeIgnoreCase("%" + sorgu + "%"));
			booleanExpressionAdvancedSearch = Util.or(booleanExpressionAdvancedSearch, QIslem.islem.aciklama.likeIgnoreCase("%" + sorgu + "%"));

			if(booleanExpressionAdvancedSearch != null)
				booleanExpression = Util.and(booleanExpression, booleanExpressionAdvancedSearch);
		}

		return booleanExpression;
	}

	public Page<Islem> sorgula(int pageSize, int pageNumber, Map<String, SortMeta> sort, Islem islem, String sorgu) {
		BooleanExpression booleanExpression = kriter(islem, sorgu);

		JPAQueryFactory queryFactory = new JPAQueryFactory(em);
		JPAQuery<Islem> query = queryFactory.
				select(QIslem.islem).
				from(QIslem.islem);

		if (booleanExpression != null)
			query = query.where(booleanExpression);

		long total = query.fetchCount();

		if (sort != null) {
			for (Map.Entry<String, SortMeta> entry : sort.entrySet()) {
				PathBuilder<Islem> builder = new PathBuilder<Islem>(QIslem.islem.getType(), QIslem.islem.getMetadata());

				if (entry.getValue().getOrder().isAscending()) {
					query.orderBy(builder.getString(entry.getKey()).asc());
				} else {
					query.orderBy(builder.getString(entry.getKey()).desc());
				}
			}
		}

		query = query.offset(pageNumber * pageSize).limit(pageSize);
		List<Islem> list = (List<Islem>) query.fetch();

		return new PageImpl<>(list, PageRequest.of(pageNumber, pageSize), total);
	}

	public List<Islem> combo(String sorgu) {
		BooleanExpression booleanExpression = null;

		if(Util.notEmpty(sorgu)) {
			booleanExpression = Util.or(booleanExpression, QIslem.islem.ad.likeIgnoreCase(sorgu+"%"));
		}

		JPAQueryFactory queryFactory = new JPAQueryFactory(em);
		JPAQuery<Islem> query = queryFactory.
				select(QIslem.islem).
				from(QIslem.islem).
				limit(Util.LIMIT);

		if (booleanExpression != null)
			query = query.where(booleanExpression);

		return (List<Islem>) query.fetch();
	}

}

