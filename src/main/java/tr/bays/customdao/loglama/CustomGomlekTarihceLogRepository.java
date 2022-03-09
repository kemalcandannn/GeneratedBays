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

import tr.bays.entity.loglama.GomlekTarihceLog;
import tr.bays.entity.loglama.QGomlekTarihceLog;
import util.Util;

@Repository
public class CustomGomlekTarihceLogRepository {

	@PersistenceContext
	private EntityManager em;

	public BooleanExpression kriter(GomlekTarihceLog gomlekTarihceLog, String sorgu) {
		BooleanExpression booleanExpression = null;

		if(Util.notEmpty(gomlekTarihceLog.getTarih_saat())) {
			booleanExpression = Util.and(booleanExpression,QGomlekTarihceLog.gomlekTarihceLog.tarih_saat.eq(gomlekTarihceLog.getTarih_saat()));
		}

		if(Util.notEmpty(gomlekTarihceLog.getIp())) {
			booleanExpression = Util.and(booleanExpression,QGomlekTarihceLog.gomlekTarihceLog.ip.likeIgnoreCase(gomlekTarihceLog.getIp()+"%"));
		}

		if(Util.notEmpty(gomlekTarihceLog.getVeri())) {
			booleanExpression = Util.and(booleanExpression,QGomlekTarihceLog.gomlekTarihceLog.veri.likeIgnoreCase(gomlekTarihceLog.getVeri()+"%"));
		}

		if(gomlekTarihceLog.getDurum() != 0) {
			booleanExpression = Util.and(booleanExpression,QGomlekTarihceLog.gomlekTarihceLog.durum.eq(gomlekTarihceLog.getDurum()));
		}

		if(gomlekTarihceLog.getKullanici_id() != null && gomlekTarihceLog.getKullanici_id().getId() != null && gomlekTarihceLog.getKullanici_id().getId() != 0) {
			booleanExpression = Util.and(booleanExpression,QGomlekTarihceLog.gomlekTarihceLog.kullanici_id.id.eq(gomlekTarihceLog.getKullanici_id().getId()));
		}

		if(gomlekTarihceLog.getGomlek_id() != null && gomlekTarihceLog.getGomlek_id().getId() != null && gomlekTarihceLog.getGomlek_id().getId() != 0) {
			booleanExpression = Util.and(booleanExpression,QGomlekTarihceLog.gomlekTarihceLog.gomlek_id.id.eq(gomlekTarihceLog.getGomlek_id().getId()));
		}

		if(Util.notEmpty(sorgu)) {
			BooleanExpression booleanExpressionAdvancedSearch = null;
			booleanExpressionAdvancedSearch = Util.or(booleanExpressionAdvancedSearch, QGomlekTarihceLog.gomlekTarihceLog.ip.likeIgnoreCase("%" + sorgu + "%"));
			booleanExpressionAdvancedSearch = Util.or(booleanExpressionAdvancedSearch, QGomlekTarihceLog.gomlekTarihceLog.veri.likeIgnoreCase("%" + sorgu + "%"));

			if(booleanExpressionAdvancedSearch != null)
				booleanExpression = Util.and(booleanExpression, booleanExpressionAdvancedSearch);
		}

		return booleanExpression;
	}

	public Page<GomlekTarihceLog> sorgula(int pageSize, int pageNumber, Map<String, SortMeta> sort, GomlekTarihceLog gomlekTarihceLog, String sorgu) {
		BooleanExpression booleanExpression = kriter(gomlekTarihceLog, sorgu);

		JPAQueryFactory queryFactory = new JPAQueryFactory(em);
		JPAQuery<GomlekTarihceLog> query = queryFactory.
				select(QGomlekTarihceLog.gomlekTarihceLog).
				from(QGomlekTarihceLog.gomlekTarihceLog);

		if (booleanExpression != null)
			query = query.where(booleanExpression);

		long total = query.fetchCount();

		if (sort != null) {
			for (Map.Entry<String, SortMeta> entry : sort.entrySet()) {
				PathBuilder<GomlekTarihceLog> builder = new PathBuilder<GomlekTarihceLog>(QGomlekTarihceLog.gomlekTarihceLog.getType(), QGomlekTarihceLog.gomlekTarihceLog.getMetadata());

				if (entry.getValue().getOrder().isAscending()) {
					query.orderBy(builder.getString(entry.getKey()).asc());
				} else {
					query.orderBy(builder.getString(entry.getKey()).desc());
				}
			}
		}

		query = query.offset(pageNumber * pageSize).limit(pageSize);
		List<GomlekTarihceLog> list = (List<GomlekTarihceLog>) query.fetch();

		return new PageImpl<>(list, PageRequest.of(pageNumber, pageSize), total);
	}

	public List<GomlekTarihceLog> combo(String sorgu) {
		BooleanExpression booleanExpression = null;

		if(Util.notEmpty(sorgu)) {
			booleanExpression = Util.or(booleanExpression, QGomlekTarihceLog.gomlekTarihceLog.ip.likeIgnoreCase(sorgu+"%"));
		}

		JPAQueryFactory queryFactory = new JPAQueryFactory(em);
		JPAQuery<GomlekTarihceLog> query = queryFactory.
				select(QGomlekTarihceLog.gomlekTarihceLog).
				from(QGomlekTarihceLog.gomlekTarihceLog).
				limit(Util.LIMIT);

		if (booleanExpression != null)
			query = query.where(booleanExpression);

		return (List<GomlekTarihceLog>) query.fetch();
	}

}

