package tr.bays.customdao.arsivSahibi;

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

import tr.bays.entity.arsivSahibi.ArsivSahibi;
import tr.bays.entity.arsivSahibi.QArsivSahibi;
import util.Util;

@Repository
public class CustomArsivSahibiRepository {

	@PersistenceContext
	private EntityManager em;

	public BooleanExpression kriter(ArsivSahibi arsivSahibi, String sorgu) {
		BooleanExpression booleanExpression = null;

		if(Util.notEmpty(arsivSahibi.getAd())) {
			booleanExpression = Util.and(booleanExpression,QArsivSahibi.arsivSahibi.ad.likeIgnoreCase(arsivSahibi.getAd()+"%"));
		}

		if(arsivSahibi.getTur() != 0) {
			booleanExpression = Util.and(booleanExpression,QArsivSahibi.arsivSahibi.tur.eq(arsivSahibi.getTur()));
		}

		if(arsivSahibi.getAktif() != 0) {
			booleanExpression = Util.and(booleanExpression,QArsivSahibi.arsivSahibi.aktif.eq(arsivSahibi.getAktif()));
		}

		if(arsivSahibi.getUst_kurum_iliski() != 0) {
			booleanExpression = Util.and(booleanExpression,QArsivSahibi.arsivSahibi.ust_kurum_iliski.eq(arsivSahibi.getUst_kurum_iliski()));
		}

		if(arsivSahibi.getOncul_versiyon_id() != null && arsivSahibi.getOncul_versiyon_id().getId() != null && arsivSahibi.getOncul_versiyon_id().getId() != 0) {
			booleanExpression = Util.and(booleanExpression,QArsivSahibi.arsivSahibi.oncul_versiyon_id.id.eq(arsivSahibi.getOncul_versiyon_id().getId()));
		}

		if(arsivSahibi.getUst_arsiv_sahibi_id() != null && arsivSahibi.getUst_arsiv_sahibi_id().getId() != null && arsivSahibi.getUst_arsiv_sahibi_id().getId() != 0) {
			booleanExpression = Util.and(booleanExpression,QArsivSahibi.arsivSahibi.ust_arsiv_sahibi_id.id.eq(arsivSahibi.getUst_arsiv_sahibi_id().getId()));
		}

		if(Util.notEmpty(sorgu)) {
			BooleanExpression booleanExpressionAdvancedSearch = null;
			booleanExpressionAdvancedSearch = Util.or(booleanExpressionAdvancedSearch, QArsivSahibi.arsivSahibi.ad.likeIgnoreCase("%" + sorgu + "%"));

			if(booleanExpressionAdvancedSearch != null)
				booleanExpression = Util.and(booleanExpression, booleanExpressionAdvancedSearch);
		}

		return booleanExpression;
	}

	public Page<ArsivSahibi> sorgula(int pageSize, int pageNumber, Map<String, SortMeta> sort, ArsivSahibi arsivSahibi, String sorgu) {
		BooleanExpression booleanExpression = kriter(arsivSahibi, sorgu);

		JPAQueryFactory queryFactory = new JPAQueryFactory(em);
		JPAQuery<ArsivSahibi> query = queryFactory.
				select(QArsivSahibi.arsivSahibi).
				from(QArsivSahibi.arsivSahibi);

		if (booleanExpression != null)
			query = query.where(booleanExpression);

		long total = query.fetchCount();

		if (sort != null) {
			for (Map.Entry<String, SortMeta> entry : sort.entrySet()) {
				PathBuilder<ArsivSahibi> builder = new PathBuilder<ArsivSahibi>(QArsivSahibi.arsivSahibi.getType(), QArsivSahibi.arsivSahibi.getMetadata());

				if (entry.getValue().getOrder().isAscending()) {
					query.orderBy(builder.getString(entry.getKey()).asc());
				} else {
					query.orderBy(builder.getString(entry.getKey()).desc());
				}
			}
		}

		query = query.offset(pageNumber * pageSize).limit(pageSize);
		List<ArsivSahibi> list = (List<ArsivSahibi>) query.fetch();

		return new PageImpl<>(list, PageRequest.of(pageNumber, pageSize), total);
	}

	public List<ArsivSahibi> combo(String sorgu) {
		BooleanExpression booleanExpression = null;

		if(Util.notEmpty(sorgu)) {
			booleanExpression = Util.or(booleanExpression, QArsivSahibi.arsivSahibi.ad.likeIgnoreCase(sorgu+"%"));
		}

		JPAQueryFactory queryFactory = new JPAQueryFactory(em);
		JPAQuery<ArsivSahibi> query = queryFactory.
				select(QArsivSahibi.arsivSahibi).
				from(QArsivSahibi.arsivSahibi).
				limit(Util.LIMIT);

		if (booleanExpression != null)
			query = query.where(booleanExpression);

		return (List<ArsivSahibi>) query.fetch();
	}

}

