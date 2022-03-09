package tr.bays.customdao.fon;

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

import tr.bays.entity.fon.FonOnculVeriLog;
import tr.bays.entity.fon.QFonOnculVeriLog;
import util.Util;

@Repository
public class CustomFonOnculVeriLogRepository {

	@PersistenceContext
	private EntityManager em;

	public BooleanExpression kriter(FonOnculVeriLog fonOnculVeriLog, String sorgu) {
		BooleanExpression booleanExpression = null;

		if(Util.notEmpty(fonOnculVeriLog.getOncul_veri())) {
			booleanExpression = Util.and(booleanExpression,QFonOnculVeriLog.fonOnculVeriLog.oncul_veri.likeIgnoreCase(fonOnculVeriLog.getOncul_veri()+"%"));
		}

		if(Util.notEmpty(fonOnculVeriLog.getIp())) {
			booleanExpression = Util.and(booleanExpression,QFonOnculVeriLog.fonOnculVeriLog.ip.likeIgnoreCase(fonOnculVeriLog.getIp()+"%"));
		}

		if(Util.notEmpty(fonOnculVeriLog.getTarih_saat())) {
			booleanExpression = Util.and(booleanExpression,QFonOnculVeriLog.fonOnculVeriLog.tarih_saat.eq(fonOnculVeriLog.getTarih_saat()));
		}

		if(fonOnculVeriLog.getKullanici_id() != null && fonOnculVeriLog.getKullanici_id().getId() != null && fonOnculVeriLog.getKullanici_id().getId() != 0) {
			booleanExpression = Util.and(booleanExpression,QFonOnculVeriLog.fonOnculVeriLog.kullanici_id.id.eq(fonOnculVeriLog.getKullanici_id().getId()));
		}

		if(fonOnculVeriLog.getFon_id() != null && fonOnculVeriLog.getFon_id().getId() != null && fonOnculVeriLog.getFon_id().getId() != 0) {
			booleanExpression = Util.and(booleanExpression,QFonOnculVeriLog.fonOnculVeriLog.fon_id.id.eq(fonOnculVeriLog.getFon_id().getId()));
		}

		if(Util.notEmpty(sorgu)) {
			BooleanExpression booleanExpressionAdvancedSearch = null;
			booleanExpressionAdvancedSearch = Util.or(booleanExpressionAdvancedSearch, QFonOnculVeriLog.fonOnculVeriLog.oncul_veri.likeIgnoreCase("%" + sorgu + "%"));
			booleanExpressionAdvancedSearch = Util.or(booleanExpressionAdvancedSearch, QFonOnculVeriLog.fonOnculVeriLog.ip.likeIgnoreCase("%" + sorgu + "%"));

			if(booleanExpressionAdvancedSearch != null)
				booleanExpression = Util.and(booleanExpression, booleanExpressionAdvancedSearch);
		}

		return booleanExpression;
	}

	public Page<FonOnculVeriLog> sorgula(int pageSize, int pageNumber, Map<String, SortMeta> sort, FonOnculVeriLog fonOnculVeriLog, String sorgu) {
		BooleanExpression booleanExpression = kriter(fonOnculVeriLog, sorgu);

		JPAQueryFactory queryFactory = new JPAQueryFactory(em);
		JPAQuery<FonOnculVeriLog> query = queryFactory.
				select(QFonOnculVeriLog.fonOnculVeriLog).
				from(QFonOnculVeriLog.fonOnculVeriLog);

		if (booleanExpression != null)
			query = query.where(booleanExpression);

		long total = query.fetchCount();

		if (sort != null) {
			for (Map.Entry<String, SortMeta> entry : sort.entrySet()) {
				PathBuilder<FonOnculVeriLog> builder = new PathBuilder<FonOnculVeriLog>(QFonOnculVeriLog.fonOnculVeriLog.getType(), QFonOnculVeriLog.fonOnculVeriLog.getMetadata());

				if (entry.getValue().getOrder().isAscending()) {
					query.orderBy(builder.getString(entry.getKey()).asc());
				} else {
					query.orderBy(builder.getString(entry.getKey()).desc());
				}
			}
		}

		query = query.offset(pageNumber * pageSize).limit(pageSize);
		List<FonOnculVeriLog> list = (List<FonOnculVeriLog>) query.fetch();

		return new PageImpl<>(list, PageRequest.of(pageNumber, pageSize), total);
	}

	public List<FonOnculVeriLog> combo(String sorgu) {
		BooleanExpression booleanExpression = null;

		if(Util.notEmpty(sorgu)) {
			booleanExpression = Util.or(booleanExpression, QFonOnculVeriLog.fonOnculVeriLog.oncul_veri.likeIgnoreCase(sorgu+"%"));
		}

		JPAQueryFactory queryFactory = new JPAQueryFactory(em);
		JPAQuery<FonOnculVeriLog> query = queryFactory.
				select(QFonOnculVeriLog.fonOnculVeriLog).
				from(QFonOnculVeriLog.fonOnculVeriLog).
				limit(Util.LIMIT);

		if (booleanExpression != null)
			query = query.where(booleanExpression);

		return (List<FonOnculVeriLog>) query.fetch();
	}

}

