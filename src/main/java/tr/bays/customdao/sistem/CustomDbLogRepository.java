package tr.bays.customdao.sistem;

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

import tr.bays.entity.sistem.DbLog;
import tr.bays.entity.sistem.QDbLog;
import util.Util;

@Repository
public class CustomDbLogRepository {

	@PersistenceContext
	private EntityManager em;

	public BooleanExpression kriter(DbLog dbLog, String sorgu) {
		BooleanExpression booleanExpression = null;

		if(Util.notEmpty(dbLog.getAd())) {
			booleanExpression = Util.and(booleanExpression,QDbLog.dbLog.ad.likeIgnoreCase(dbLog.getAd()+"%"));
		}

		if(Util.notEmpty(dbLog.getIp())) {
			booleanExpression = Util.and(booleanExpression,QDbLog.dbLog.ip.likeIgnoreCase(dbLog.getIp()+"%"));
		}

		if(Util.notEmpty(dbLog.getTarih())) {
			booleanExpression = Util.and(booleanExpression,QDbLog.dbLog.tarih.eq(dbLog.getTarih()));
		}

		if(Util.notEmpty(dbLog.getSaat())) {
			booleanExpression = Util.and(booleanExpression,QDbLog.dbLog.saat.eq(dbLog.getSaat()));
		}

		if(dbLog.getTur() != 0) {
			booleanExpression = Util.and(booleanExpression,QDbLog.dbLog.tur.eq(dbLog.getTur()));
		}

		if(Util.notEmpty(dbLog.getAciklama())) {
			booleanExpression = Util.and(booleanExpression,QDbLog.dbLog.aciklama.likeIgnoreCase(dbLog.getAciklama()+"%"));
		}

		if(Util.notEmpty(dbLog.getEk1())) {
			booleanExpression = Util.and(booleanExpression,QDbLog.dbLog.ek1.likeIgnoreCase(dbLog.getEk1()+"%"));
		}

		if(Util.notEmpty(dbLog.getEk2())) {
			booleanExpression = Util.and(booleanExpression,QDbLog.dbLog.ek2.likeIgnoreCase(dbLog.getEk2()+"%"));
		}

		if(Util.notEmpty(dbLog.getPuk())) {
			booleanExpression = Util.and(booleanExpression,QDbLog.dbLog.puk.likeIgnoreCase(dbLog.getPuk()+"%"));
		}

		if(Util.notEmpty(dbLog.getSinif())) {
			booleanExpression = Util.and(booleanExpression,QDbLog.dbLog.sinif.likeIgnoreCase(dbLog.getSinif()+"%"));
		}

		if(Util.notEmpty(dbLog.getMetod())) {
			booleanExpression = Util.and(booleanExpression,QDbLog.dbLog.metod.likeIgnoreCase(dbLog.getMetod()+"%"));
		}

		if(Util.notEmpty(dbLog.getIslem())) {
			booleanExpression = Util.and(booleanExpression,QDbLog.dbLog.islem.likeIgnoreCase(dbLog.getIslem()+"%"));
		}

		if(Util.notEmpty(sorgu)) {
			BooleanExpression booleanExpressionAdvancedSearch = null;
			booleanExpressionAdvancedSearch = Util.or(booleanExpressionAdvancedSearch, QDbLog.dbLog.ad.likeIgnoreCase("%" + sorgu + "%"));
			booleanExpressionAdvancedSearch = Util.or(booleanExpressionAdvancedSearch, QDbLog.dbLog.ip.likeIgnoreCase("%" + sorgu + "%"));
			booleanExpressionAdvancedSearch = Util.or(booleanExpressionAdvancedSearch, QDbLog.dbLog.aciklama.likeIgnoreCase("%" + sorgu + "%"));
			booleanExpressionAdvancedSearch = Util.or(booleanExpressionAdvancedSearch, QDbLog.dbLog.ek1.likeIgnoreCase("%" + sorgu + "%"));
			booleanExpressionAdvancedSearch = Util.or(booleanExpressionAdvancedSearch, QDbLog.dbLog.ek2.likeIgnoreCase("%" + sorgu + "%"));
			booleanExpressionAdvancedSearch = Util.or(booleanExpressionAdvancedSearch, QDbLog.dbLog.puk.likeIgnoreCase("%" + sorgu + "%"));
			booleanExpressionAdvancedSearch = Util.or(booleanExpressionAdvancedSearch, QDbLog.dbLog.sinif.likeIgnoreCase("%" + sorgu + "%"));
			booleanExpressionAdvancedSearch = Util.or(booleanExpressionAdvancedSearch, QDbLog.dbLog.metod.likeIgnoreCase("%" + sorgu + "%"));
			booleanExpressionAdvancedSearch = Util.or(booleanExpressionAdvancedSearch, QDbLog.dbLog.islem.likeIgnoreCase("%" + sorgu + "%"));

			if(booleanExpressionAdvancedSearch != null)
				booleanExpression = Util.and(booleanExpression, booleanExpressionAdvancedSearch);
		}

		return booleanExpression;
	}

	public Page<DbLog> sorgula(int pageSize, int pageNumber, Map<String, SortMeta> sort, DbLog dbLog, String sorgu) {
		BooleanExpression booleanExpression = kriter(dbLog, sorgu);

		JPAQueryFactory queryFactory = new JPAQueryFactory(em);
		JPAQuery<DbLog> query = queryFactory.
				select(QDbLog.dbLog).
				from(QDbLog.dbLog);

		if (booleanExpression != null)
			query = query.where(booleanExpression);

		long total = query.fetchCount();

		if (sort != null) {
			for (Map.Entry<String, SortMeta> entry : sort.entrySet()) {
				PathBuilder<DbLog> builder = new PathBuilder<DbLog>(QDbLog.dbLog.getType(), QDbLog.dbLog.getMetadata());

				if (entry.getValue().getOrder().isAscending()) {
					query.orderBy(builder.getString(entry.getKey()).asc());
				} else {
					query.orderBy(builder.getString(entry.getKey()).desc());
				}
			}
		}

		query = query.offset(pageNumber * pageSize).limit(pageSize);
		List<DbLog> list = (List<DbLog>) query.fetch();

		return new PageImpl<>(list, PageRequest.of(pageNumber, pageSize), total);
	}

	public List<DbLog> combo(String sorgu) {
		BooleanExpression booleanExpression = null;

		if(Util.notEmpty(sorgu)) {
			booleanExpression = Util.or(booleanExpression, QDbLog.dbLog.ad.likeIgnoreCase(sorgu+"%"));
		}

		JPAQueryFactory queryFactory = new JPAQueryFactory(em);
		JPAQuery<DbLog> query = queryFactory.
				select(QDbLog.dbLog).
				from(QDbLog.dbLog).
				limit(Util.LIMIT);

		if (booleanExpression != null)
			query = query.where(booleanExpression);

		return (List<DbLog>) query.fetch();
	}

}

