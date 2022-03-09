package tr.bays.customdao.loglama;

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

import tr.bays.entity.loglama.ArsivSahibiPersonelOnayLog;
import tr.bays.entity.loglama.QArsivSahibiPersonelOnayLog;
import util.Util;

@Repository
public class CustomArsivSahibiPersonelOnayLogRepository {

	@PersistenceContext
	private EntityManager em;

	public BooleanExpression kriter(ArsivSahibiPersonelOnayLog arsivSahibiPersonelOnayLog, String sorgu) {
		BooleanExpression booleanExpression = null;

		if(Util.notEmpty(arsivSahibiPersonelOnayLog.getTarih_saat())) {
			booleanExpression = Util.and(booleanExpression,QArsivSahibiPersonelOnayLog.arsivSahibiPersonelOnayLog.tarih_saat.eq(arsivSahibiPersonelOnayLog.getTarih_saat()));
		}

		if(arsivSahibiPersonelOnayLog.getIslem() != 0) {
			booleanExpression = Util.and(booleanExpression,QArsivSahibiPersonelOnayLog.arsivSahibiPersonelOnayLog.islem.eq(arsivSahibiPersonelOnayLog.getIslem()));
		}

		if(arsivSahibiPersonelOnayLog.getOnaylayan_kullanici_id() != null && arsivSahibiPersonelOnayLog.getOnaylayan_kullanici_id().getId() != null && arsivSahibiPersonelOnayLog.getOnaylayan_kullanici_id().getId() != 0) {
			booleanExpression = Util.and(booleanExpression,QArsivSahibiPersonelOnayLog.arsivSahibiPersonelOnayLog.onaylayan_kullanici_id.id.eq(arsivSahibiPersonelOnayLog.getOnaylayan_kullanici_id().getId()));
		}

		if(arsivSahibiPersonelOnayLog.getOnaylanan_personel_id() != null && arsivSahibiPersonelOnayLog.getOnaylanan_personel_id().getId() != null && arsivSahibiPersonelOnayLog.getOnaylanan_personel_id().getId() != 0) {
			booleanExpression = Util.and(booleanExpression,QArsivSahibiPersonelOnayLog.arsivSahibiPersonelOnayLog.onaylanan_personel_id.id.eq(arsivSahibiPersonelOnayLog.getOnaylanan_personel_id().getId()));
		}

		if(Util.notEmpty(sorgu)) {
			BooleanExpression booleanExpressionAdvancedSearch = null;

			if(booleanExpressionAdvancedSearch != null)
				booleanExpression = Util.and(booleanExpression, booleanExpressionAdvancedSearch);
		}

		return booleanExpression;
	}

	public Page<ArsivSahibiPersonelOnayLog> sorgula(int pageSize, int pageNumber, Map<String, SortMeta> sort, ArsivSahibiPersonelOnayLog arsivSahibiPersonelOnayLog, String sorgu) {
		BooleanExpression booleanExpression = kriter(arsivSahibiPersonelOnayLog, sorgu);

		JPAQueryFactory queryFactory = new JPAQueryFactory(em);
		JPAQuery<ArsivSahibiPersonelOnayLog> query = queryFactory.
				select(QArsivSahibiPersonelOnayLog.arsivSahibiPersonelOnayLog).
				from(QArsivSahibiPersonelOnayLog.arsivSahibiPersonelOnayLog);

		if (booleanExpression != null)
			query = query.where(booleanExpression);

		long total = query.fetchCount();

		if (sort != null) {
			for (Map.Entry<String, SortMeta> entry : sort.entrySet()) {
				PathBuilder<ArsivSahibiPersonelOnayLog> builder = new PathBuilder<ArsivSahibiPersonelOnayLog>(QArsivSahibiPersonelOnayLog.arsivSahibiPersonelOnayLog.getType(), QArsivSahibiPersonelOnayLog.arsivSahibiPersonelOnayLog.getMetadata());

				if (entry.getValue().getOrder().isAscending()) {
					query.orderBy(builder.getString(entry.getKey()).asc());
				} else {
					query.orderBy(builder.getString(entry.getKey()).desc());
				}
			}
		}

		query = query.offset(pageNumber * pageSize).limit(pageSize);
		List<ArsivSahibiPersonelOnayLog> list = (List<ArsivSahibiPersonelOnayLog>) query.fetch();

		return new PageImpl<>(list, PageRequest.of(pageNumber, pageSize), total);
	}

	public List<ArsivSahibiPersonelOnayLog> combo(String sorgu) {
		BooleanExpression booleanExpression = null;

		JPAQueryFactory queryFactory = new JPAQueryFactory(em);
		JPAQuery<ArsivSahibiPersonelOnayLog> query = queryFactory.
				select(QArsivSahibiPersonelOnayLog.arsivSahibiPersonelOnayLog).
				from(QArsivSahibiPersonelOnayLog.arsivSahibiPersonelOnayLog).
				limit(Util.LIMIT);

		if (booleanExpression != null)
			query = query.where(booleanExpression);

		return (List<ArsivSahibiPersonelOnayLog>) query.fetch();
	}

}

