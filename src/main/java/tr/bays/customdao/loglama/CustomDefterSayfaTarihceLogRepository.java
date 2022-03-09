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

import tr.bays.entity.loglama.DefterSayfaTarihceLog;
import tr.bays.entity.loglama.QDefterSayfaTarihceLog;
import util.Util;

@Repository
public class CustomDefterSayfaTarihceLogRepository {

	@PersistenceContext
	private EntityManager em;

	public BooleanExpression kriter(DefterSayfaTarihceLog defterSayfaTarihceLog, String sorgu) {
		BooleanExpression booleanExpression = null;

		if(Util.notEmpty(defterSayfaTarihceLog.getTarih_saat())) {
			booleanExpression = Util.and(booleanExpression,QDefterSayfaTarihceLog.defterSayfaTarihceLog.tarih_saat.eq(defterSayfaTarihceLog.getTarih_saat()));
		}

		if(Util.notEmpty(defterSayfaTarihceLog.getIp())) {
			booleanExpression = Util.and(booleanExpression,QDefterSayfaTarihceLog.defterSayfaTarihceLog.ip.likeIgnoreCase(defterSayfaTarihceLog.getIp()+"%"));
		}

		if(Util.notEmpty(defterSayfaTarihceLog.getVeri())) {
			booleanExpression = Util.and(booleanExpression,QDefterSayfaTarihceLog.defterSayfaTarihceLog.veri.likeIgnoreCase(defterSayfaTarihceLog.getVeri()+"%"));
		}

		if(defterSayfaTarihceLog.getDurum() != 0) {
			booleanExpression = Util.and(booleanExpression,QDefterSayfaTarihceLog.defterSayfaTarihceLog.durum.eq(defterSayfaTarihceLog.getDurum()));
		}

		if(defterSayfaTarihceLog.getKullanici_id() != null && defterSayfaTarihceLog.getKullanici_id().getId() != null && defterSayfaTarihceLog.getKullanici_id().getId() != 0) {
			booleanExpression = Util.and(booleanExpression,QDefterSayfaTarihceLog.defterSayfaTarihceLog.kullanici_id.id.eq(defterSayfaTarihceLog.getKullanici_id().getId()));
		}

		if(defterSayfaTarihceLog.getDefter_sayfa_id() != null && defterSayfaTarihceLog.getDefter_sayfa_id().getId() != null && defterSayfaTarihceLog.getDefter_sayfa_id().getId() != 0) {
			booleanExpression = Util.and(booleanExpression,QDefterSayfaTarihceLog.defterSayfaTarihceLog.defter_sayfa_id.id.eq(defterSayfaTarihceLog.getDefter_sayfa_id().getId()));
		}

		if(Util.notEmpty(sorgu)) {
			BooleanExpression booleanExpressionAdvancedSearch = null;
			booleanExpressionAdvancedSearch = Util.or(booleanExpressionAdvancedSearch, QDefterSayfaTarihceLog.defterSayfaTarihceLog.ip.likeIgnoreCase("%" + sorgu + "%"));
			booleanExpressionAdvancedSearch = Util.or(booleanExpressionAdvancedSearch, QDefterSayfaTarihceLog.defterSayfaTarihceLog.veri.likeIgnoreCase("%" + sorgu + "%"));

			if(booleanExpressionAdvancedSearch != null)
				booleanExpression = Util.and(booleanExpression, booleanExpressionAdvancedSearch);
		}

		return booleanExpression;
	}

	public Page<DefterSayfaTarihceLog> sorgula(int pageSize, int pageNumber, Map<String, SortMeta> sort, DefterSayfaTarihceLog defterSayfaTarihceLog, String sorgu) {
		BooleanExpression booleanExpression = kriter(defterSayfaTarihceLog, sorgu);

		JPAQueryFactory queryFactory = new JPAQueryFactory(em);
		JPAQuery<DefterSayfaTarihceLog> query = queryFactory.
				select(QDefterSayfaTarihceLog.defterSayfaTarihceLog).
				from(QDefterSayfaTarihceLog.defterSayfaTarihceLog);

		if (booleanExpression != null)
			query = query.where(booleanExpression);

		long total = query.fetchCount();

		if (sort != null) {
			for (Map.Entry<String, SortMeta> entry : sort.entrySet()) {
				PathBuilder<DefterSayfaTarihceLog> builder = new PathBuilder<DefterSayfaTarihceLog>(QDefterSayfaTarihceLog.defterSayfaTarihceLog.getType(), QDefterSayfaTarihceLog.defterSayfaTarihceLog.getMetadata());

				if (entry.getValue().getOrder().isAscending()) {
					query.orderBy(builder.getString(entry.getKey()).asc());
				} else {
					query.orderBy(builder.getString(entry.getKey()).desc());
				}
			}
		}

		query = query.offset(pageNumber * pageSize).limit(pageSize);
		List<DefterSayfaTarihceLog> list = (List<DefterSayfaTarihceLog>) query.fetch();

		return new PageImpl<>(list, PageRequest.of(pageNumber, pageSize), total);
	}

	public List<DefterSayfaTarihceLog> combo(String sorgu) {
		BooleanExpression booleanExpression = null;

		if(Util.notEmpty(sorgu)) {
			booleanExpression = Util.or(booleanExpression, QDefterSayfaTarihceLog.defterSayfaTarihceLog.ip.likeIgnoreCase(sorgu+"%"));
		}

		JPAQueryFactory queryFactory = new JPAQueryFactory(em);
		JPAQuery<DefterSayfaTarihceLog> query = queryFactory.
				select(QDefterSayfaTarihceLog.defterSayfaTarihceLog).
				from(QDefterSayfaTarihceLog.defterSayfaTarihceLog).
				limit(Util.LIMIT);

		if (booleanExpression != null)
			query = query.where(booleanExpression);

		return (List<DefterSayfaTarihceLog>) query.fetch();
	}

}

