package tr.bays.customdao.teskilat;

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

import tr.bays.entity.teskilat.ArsivKurumu;
import tr.bays.entity.teskilat.QArsivKurumu;
import util.Util;

@Repository
public class CustomArsivKurumuRepository {

	@PersistenceContext
	private EntityManager em;

	public BooleanExpression kriter(ArsivKurumu arsivKurumu, String sorgu) {
		BooleanExpression booleanExpression = null;

		if(Util.notEmpty(arsivKurumu.getAd())) {
			booleanExpression = Util.and(booleanExpression,QArsivKurumu.arsivKurumu.ad.likeIgnoreCase(arsivKurumu.getAd()+"%"));
		}

		if(arsivKurumu.getDurum() != 0) {
			booleanExpression = Util.and(booleanExpression,QArsivKurumu.arsivKurumu.durum.eq(arsivKurumu.getDurum()));
		}

		if(Util.notEmpty(sorgu)) {
			BooleanExpression booleanExpressionAdvancedSearch = null;
			booleanExpressionAdvancedSearch = Util.or(booleanExpressionAdvancedSearch, QArsivKurumu.arsivKurumu.ad.likeIgnoreCase("%" + sorgu + "%"));

			if(booleanExpressionAdvancedSearch != null)
				booleanExpression = Util.and(booleanExpression, booleanExpressionAdvancedSearch);
		}

		return booleanExpression;
	}

	public Page<ArsivKurumu> sorgula(int pageSize, int pageNumber, Map<String, SortMeta> sort, ArsivKurumu arsivKurumu, String sorgu) {
		BooleanExpression booleanExpression = kriter(arsivKurumu, sorgu);

		JPAQueryFactory queryFactory = new JPAQueryFactory(em);
		JPAQuery<ArsivKurumu> query = queryFactory.
				select(QArsivKurumu.arsivKurumu).
				from(QArsivKurumu.arsivKurumu);

		if (booleanExpression != null)
			query = query.where(booleanExpression);

		long total = query.fetchCount();

		if (sort != null) {
			for (Map.Entry<String, SortMeta> entry : sort.entrySet()) {
				PathBuilder<ArsivKurumu> builder = new PathBuilder<ArsivKurumu>(QArsivKurumu.arsivKurumu.getType(), QArsivKurumu.arsivKurumu.getMetadata());

				if (entry.getValue().getOrder().isAscending()) {
					query.orderBy(builder.getString(entry.getKey()).asc());
				} else {
					query.orderBy(builder.getString(entry.getKey()).desc());
				}
			}
		}

		query = query.offset(pageNumber * pageSize).limit(pageSize);
		List<ArsivKurumu> list = (List<ArsivKurumu>) query.fetch();

		return new PageImpl<>(list, PageRequest.of(pageNumber, pageSize), total);
	}

	public List<ArsivKurumu> combo(String sorgu) {
		BooleanExpression booleanExpression = null;

		if(Util.notEmpty(sorgu)) {
			booleanExpression = Util.or(booleanExpression, QArsivKurumu.arsivKurumu.ad.likeIgnoreCase(sorgu+"%"));
		}

		JPAQueryFactory queryFactory = new JPAQueryFactory(em);
		JPAQuery<ArsivKurumu> query = queryFactory.
				select(QArsivKurumu.arsivKurumu).
				from(QArsivKurumu.arsivKurumu).
				limit(Util.LIMIT);

		if (booleanExpression != null)
			query = query.where(booleanExpression);

		return (List<ArsivKurumu>) query.fetch();
	}

}

