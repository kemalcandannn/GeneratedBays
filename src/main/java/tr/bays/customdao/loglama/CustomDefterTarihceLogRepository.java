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

import tr.bays.entity.loglama.DefterTarihceLog;
import tr.bays.entity.loglama.QDefterTarihceLog;
import util.Util;

@Repository
public class CustomDefterTarihceLogRepository {

	@PersistenceContext
	private EntityManager em;

	public BooleanExpression kriter(DefterTarihceLog defterTarihceLog, String sorgu) {
		BooleanExpression booleanExpression = null;

		if(Util.notEmpty(defterTarihceLog.getTarih_saat())) {
			booleanExpression = Util.and(booleanExpression,QDefterTarihceLog.defterTarihceLog.tarih_saat.eq(defterTarihceLog.getTarih_saat()));
		}

		if(Util.notEmpty(defterTarihceLog.getIp())) {
			booleanExpression = Util.and(booleanExpression,QDefterTarihceLog.defterTarihceLog.ip.likeIgnoreCase(defterTarihceLog.getIp()+"%"));
		}

		if(Util.notEmpty(defterTarihceLog.getVeri())) {
			booleanExpression = Util.and(booleanExpression,QDefterTarihceLog.defterTarihceLog.veri.likeIgnoreCase(defterTarihceLog.getVeri()+"%"));
		}

		if(defterTarihceLog.getDurum() != 0) {
			booleanExpression = Util.and(booleanExpression,QDefterTarihceLog.defterTarihceLog.durum.eq(defterTarihceLog.getDurum()));
		}

		if(defterTarihceLog.getKullanici_id() != null && defterTarihceLog.getKullanici_id().getId() != null && defterTarihceLog.getKullanici_id().getId() != 0) {
			booleanExpression = Util.and(booleanExpression,QDefterTarihceLog.defterTarihceLog.kullanici_id.id.eq(defterTarihceLog.getKullanici_id().getId()));
		}

		if(defterTarihceLog.getDefter_id() != null && defterTarihceLog.getDefter_id().getId() != null && defterTarihceLog.getDefter_id().getId() != 0) {
			booleanExpression = Util.and(booleanExpression,QDefterTarihceLog.defterTarihceLog.defter_id.id.eq(defterTarihceLog.getDefter_id().getId()));
		}

		if(Util.notEmpty(sorgu)) {
			BooleanExpression booleanExpressionAdvancedSearch = null;
			booleanExpressionAdvancedSearch = Util.or(booleanExpressionAdvancedSearch, QDefterTarihceLog.defterTarihceLog.ip.likeIgnoreCase("%" + sorgu + "%"));
			booleanExpressionAdvancedSearch = Util.or(booleanExpressionAdvancedSearch, QDefterTarihceLog.defterTarihceLog.veri.likeIgnoreCase("%" + sorgu + "%"));

			if(booleanExpressionAdvancedSearch != null)
				booleanExpression = Util.and(booleanExpression, booleanExpressionAdvancedSearch);
		}

		return booleanExpression;
	}

	public Page<DefterTarihceLog> sorgula(int pageSize, int pageNumber, Map<String, SortMeta> sort, DefterTarihceLog defterTarihceLog, String sorgu) {
		BooleanExpression booleanExpression = kriter(defterTarihceLog, sorgu);

		JPAQueryFactory queryFactory = new JPAQueryFactory(em);
		JPAQuery<DefterTarihceLog> query = queryFactory.
				select(QDefterTarihceLog.defterTarihceLog).
				from(QDefterTarihceLog.defterTarihceLog);

		if (booleanExpression != null)
			query = query.where(booleanExpression);

		long total = query.fetchCount();

		if (sort != null) {
			for (Map.Entry<String, SortMeta> entry : sort.entrySet()) {
				PathBuilder<DefterTarihceLog> builder = new PathBuilder<DefterTarihceLog>(QDefterTarihceLog.defterTarihceLog.getType(), QDefterTarihceLog.defterTarihceLog.getMetadata());

				if (entry.getValue().getOrder().isAscending()) {
					query.orderBy(builder.getString(entry.getKey()).asc());
				} else {
					query.orderBy(builder.getString(entry.getKey()).desc());
				}
			}
		}

		query = query.offset(pageNumber * pageSize).limit(pageSize);
		List<DefterTarihceLog> list = (List<DefterTarihceLog>) query.fetch();

		return new PageImpl<>(list, PageRequest.of(pageNumber, pageSize), total);
	}

	public List<DefterTarihceLog> combo(String sorgu) {
		BooleanExpression booleanExpression = null;

		if(Util.notEmpty(sorgu)) {
			booleanExpression = Util.or(booleanExpression, QDefterTarihceLog.defterTarihceLog.ip.likeIgnoreCase(sorgu+"%"));
		}

		JPAQueryFactory queryFactory = new JPAQueryFactory(em);
		JPAQuery<DefterTarihceLog> query = queryFactory.
				select(QDefterTarihceLog.defterTarihceLog).
				from(QDefterTarihceLog.defterTarihceLog).
				limit(Util.LIMIT);

		if (booleanExpression != null)
			query = query.where(booleanExpression);

		return (List<DefterTarihceLog>) query.fetch();
	}

}

