package tr.bays.customdao.depo;

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

import tr.bays.entity.depo.Depo;
import tr.bays.entity.depo.QDepo;
import util.Util;

@Repository
public class CustomDepoRepository {

	@PersistenceContext
	private EntityManager em;

	public BooleanExpression kriter(Depo depo, String sorgu) {
		BooleanExpression booleanExpression = null;

		if(Util.notEmpty(depo.getAd())) {
			booleanExpression = Util.and(booleanExpression,QDepo.depo.ad.likeIgnoreCase(depo.getAd()+"%"));
		}

		if(Util.notEmpty(depo.getKisaltma())) {
			booleanExpression = Util.and(booleanExpression,QDepo.depo.kisaltma.likeIgnoreCase(depo.getKisaltma()+"%"));
		}

		if(Util.notEmpty(depo.getAciklama())) {
			booleanExpression = Util.and(booleanExpression,QDepo.depo.aciklama.likeIgnoreCase(depo.getAciklama()+"%"));
		}

		if(depo.getAktif() != 0) {
			booleanExpression = Util.and(booleanExpression,QDepo.depo.aktif.eq(depo.getAktif()));
		}

		if(depo.getKapasite() != 0) {
			booleanExpression = Util.and(booleanExpression,QDepo.depo.kapasite.eq(depo.getKapasite()));
		}

		if(depo.getCalisma_gozu() != 0) {
			booleanExpression = Util.and(booleanExpression,QDepo.depo.calisma_gozu.eq(depo.getCalisma_gozu()));
		}

		if(depo.getTasnifsiz_malzeme_sayisi() != 0) {
			booleanExpression = Util.and(booleanExpression,QDepo.depo.tasnifsiz_malzeme_sayisi.eq(depo.getTasnifsiz_malzeme_sayisi()));
		}

		if(depo.getArsiv_kurumu_id() != null && depo.getArsiv_kurumu_id().getId() != null && depo.getArsiv_kurumu_id().getId() != 0) {
			booleanExpression = Util.and(booleanExpression,QDepo.depo.arsiv_kurumu_id.id.eq(depo.getArsiv_kurumu_id().getId()));
		}

		if(depo.getUst_depo_id() != null && depo.getUst_depo_id().getId() != null && depo.getUst_depo_id().getId() != 0) {
			booleanExpression = Util.and(booleanExpression,QDepo.depo.ust_depo_id.id.eq(depo.getUst_depo_id().getId()));
		}

		if(Util.notEmpty(sorgu)) {
			BooleanExpression booleanExpressionAdvancedSearch = null;
			booleanExpressionAdvancedSearch = Util.or(booleanExpressionAdvancedSearch, QDepo.depo.ad.likeIgnoreCase("%" + sorgu + "%"));
			booleanExpressionAdvancedSearch = Util.or(booleanExpressionAdvancedSearch, QDepo.depo.kisaltma.likeIgnoreCase("%" + sorgu + "%"));
			booleanExpressionAdvancedSearch = Util.or(booleanExpressionAdvancedSearch, QDepo.depo.aciklama.likeIgnoreCase("%" + sorgu + "%"));

			if(booleanExpressionAdvancedSearch != null)
				booleanExpression = Util.and(booleanExpression, booleanExpressionAdvancedSearch);
		}

		return booleanExpression;
	}

	public Page<Depo> sorgula(int pageSize, int pageNumber, Map<String, SortMeta> sort, Depo depo, String sorgu) {
		BooleanExpression booleanExpression = kriter(depo, sorgu);

		JPAQueryFactory queryFactory = new JPAQueryFactory(em);
		JPAQuery<Depo> query = queryFactory.
				select(QDepo.depo).
				from(QDepo.depo);

		if (booleanExpression != null)
			query = query.where(booleanExpression);

		long total = query.fetchCount();

		if (sort != null) {
			for (Map.Entry<String, SortMeta> entry : sort.entrySet()) {
				PathBuilder<Depo> builder = new PathBuilder<Depo>(QDepo.depo.getType(), QDepo.depo.getMetadata());

				if (entry.getValue().getOrder().isAscending()) {
					query.orderBy(builder.getString(entry.getKey()).asc());
				} else {
					query.orderBy(builder.getString(entry.getKey()).desc());
				}
			}
		}

		query = query.offset(pageNumber * pageSize).limit(pageSize);
		List<Depo> list = (List<Depo>) query.fetch();

		return new PageImpl<>(list, PageRequest.of(pageNumber, pageSize), total);
	}

	public List<Depo> combo(String sorgu) {
		BooleanExpression booleanExpression = null;

		if(Util.notEmpty(sorgu)) {
			booleanExpression = Util.or(booleanExpression, QDepo.depo.ad.likeIgnoreCase(sorgu+"%"));
		}

		JPAQueryFactory queryFactory = new JPAQueryFactory(em);
		JPAQuery<Depo> query = queryFactory.
				select(QDepo.depo).
				from(QDepo.depo).
				limit(Util.LIMIT);

		if (booleanExpression != null)
			query = query.where(booleanExpression);

		return (List<Depo>) query.fetch();
	}

}

