package tr.bays.customdao.calismaGrubu;

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

import tr.bays.entity.calismaGrubu.CalismaGrubuUye;
import tr.bays.entity.calismaGrubu.QCalismaGrubuUye;
import util.Util;

@Repository
public class CustomCalismaGrubuUyeRepository {

	@PersistenceContext
	private EntityManager em;

	public BooleanExpression kriter(CalismaGrubuUye calismaGrubuUye, String sorgu) {
		BooleanExpression booleanExpression = null;

		if(calismaGrubuUye.getKullanici_id() != null && calismaGrubuUye.getKullanici_id().getId() != null && calismaGrubuUye.getKullanici_id().getId() != 0) {
			booleanExpression = Util.and(booleanExpression,QCalismaGrubuUye.calismaGrubuUye.kullanici_id.id.eq(calismaGrubuUye.getKullanici_id().getId()));
		}

		if(calismaGrubuUye.getArsiv_sahibi_personel_id() != null && calismaGrubuUye.getArsiv_sahibi_personel_id().getId() != null && calismaGrubuUye.getArsiv_sahibi_personel_id().getId() != 0) {
			booleanExpression = Util.and(booleanExpression,QCalismaGrubuUye.calismaGrubuUye.arsiv_sahibi_personel_id.id.eq(calismaGrubuUye.getArsiv_sahibi_personel_id().getId()));
		}

		if(calismaGrubuUye.getCalisma_grubu_id() != null && calismaGrubuUye.getCalisma_grubu_id().getId() != null && calismaGrubuUye.getCalisma_grubu_id().getId() != 0) {
			booleanExpression = Util.and(booleanExpression,QCalismaGrubuUye.calismaGrubuUye.calisma_grubu_id.id.eq(calismaGrubuUye.getCalisma_grubu_id().getId()));
		}

		if(Util.notEmpty(sorgu)) {
			BooleanExpression booleanExpressionAdvancedSearch = null;

			if(booleanExpressionAdvancedSearch != null)
				booleanExpression = Util.and(booleanExpression, booleanExpressionAdvancedSearch);
		}

		return booleanExpression;
	}

	public Page<CalismaGrubuUye> sorgula(int pageSize, int pageNumber, Map<String, SortMeta> sort, CalismaGrubuUye calismaGrubuUye, String sorgu) {
		BooleanExpression booleanExpression = kriter(calismaGrubuUye, sorgu);

		JPAQueryFactory queryFactory = new JPAQueryFactory(em);
		JPAQuery<CalismaGrubuUye> query = queryFactory.
				select(QCalismaGrubuUye.calismaGrubuUye).
				from(QCalismaGrubuUye.calismaGrubuUye);

		if (booleanExpression != null)
			query = query.where(booleanExpression);

		long total = query.fetchCount();

		if (sort != null) {
			for (Map.Entry<String, SortMeta> entry : sort.entrySet()) {
				PathBuilder<CalismaGrubuUye> builder = new PathBuilder<CalismaGrubuUye>(QCalismaGrubuUye.calismaGrubuUye.getType(), QCalismaGrubuUye.calismaGrubuUye.getMetadata());

				if (entry.getValue().getOrder().isAscending()) {
					query.orderBy(builder.getString(entry.getKey()).asc());
				} else {
					query.orderBy(builder.getString(entry.getKey()).desc());
				}
			}
		}

		query = query.offset(pageNumber * pageSize).limit(pageSize);
		List<CalismaGrubuUye> list = (List<CalismaGrubuUye>) query.fetch();

		return new PageImpl<>(list, PageRequest.of(pageNumber, pageSize), total);
	}

	public List<CalismaGrubuUye> combo(String sorgu) {
		BooleanExpression booleanExpression = null;

		JPAQueryFactory queryFactory = new JPAQueryFactory(em);
		JPAQuery<CalismaGrubuUye> query = queryFactory.
				select(QCalismaGrubuUye.calismaGrubuUye).
				from(QCalismaGrubuUye.calismaGrubuUye).
				limit(Util.LIMIT);

		if (booleanExpression != null)
			query = query.where(booleanExpression);

		return (List<CalismaGrubuUye>) query.fetch();
	}

}

