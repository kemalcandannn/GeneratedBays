package tr.bays.customdao.sozluk;

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

import tr.bays.entity.sozluk.SozlukMadde;
import tr.bays.entity.sozluk.QSozlukMadde;
import util.Util;

@Repository
public class CustomSozlukMaddeRepository {

	@PersistenceContext
	private EntityManager em;

	public BooleanExpression kriter(SozlukMadde sozlukMadde, String sorgu) {
		BooleanExpression booleanExpression = null;

		if(Util.notEmpty(sozlukMadde.getAd())) {
			booleanExpression = Util.and(booleanExpression,QSozlukMadde.sozlukMadde.ad.likeIgnoreCase(sozlukMadde.getAd()+"%"));
		}

		if(Util.notEmpty(sozlukMadde.getKisaltma())) {
			booleanExpression = Util.and(booleanExpression,QSozlukMadde.sozlukMadde.kisaltma.likeIgnoreCase(sozlukMadde.getKisaltma()+"%"));
		}

		if(Util.notEmpty(sozlukMadde.getAciklama())) {
			booleanExpression = Util.and(booleanExpression,QSozlukMadde.sozlukMadde.aciklama.likeIgnoreCase(sozlukMadde.getAciklama()+"%"));
		}

		if(Util.notEmpty(sozlukMadde.getListe_aciklama())) {
			booleanExpression = Util.and(booleanExpression,QSozlukMadde.sozlukMadde.liste_aciklama.likeIgnoreCase(sozlukMadde.getListe_aciklama()+"%"));
		}

		if(sozlukMadde.getAktif() != 0) {
			booleanExpression = Util.and(booleanExpression,QSozlukMadde.sozlukMadde.aktif.eq(sozlukMadde.getAktif()));
		}

		if(sozlukMadde.getSozluk_id() != null && sozlukMadde.getSozluk_id().getId() != null && sozlukMadde.getSozluk_id().getId() != 0) {
			booleanExpression = Util.and(booleanExpression,QSozlukMadde.sozlukMadde.sozluk_id.id.eq(sozlukMadde.getSozluk_id().getId()));
		}

		if(Util.notEmpty(sorgu)) {
			BooleanExpression booleanExpressionAdvancedSearch = null;
			booleanExpressionAdvancedSearch = Util.or(booleanExpressionAdvancedSearch, QSozlukMadde.sozlukMadde.ad.likeIgnoreCase("%" + sorgu + "%"));
			booleanExpressionAdvancedSearch = Util.or(booleanExpressionAdvancedSearch, QSozlukMadde.sozlukMadde.kisaltma.likeIgnoreCase("%" + sorgu + "%"));
			booleanExpressionAdvancedSearch = Util.or(booleanExpressionAdvancedSearch, QSozlukMadde.sozlukMadde.aciklama.likeIgnoreCase("%" + sorgu + "%"));
			booleanExpressionAdvancedSearch = Util.or(booleanExpressionAdvancedSearch, QSozlukMadde.sozlukMadde.liste_aciklama.likeIgnoreCase("%" + sorgu + "%"));

			if(booleanExpressionAdvancedSearch != null)
				booleanExpression = Util.and(booleanExpression, booleanExpressionAdvancedSearch);
		}

		return booleanExpression;
	}

	public Page<SozlukMadde> sorgula(int pageSize, int pageNumber, Map<String, SortMeta> sort, SozlukMadde sozlukMadde, String sorgu) {
		BooleanExpression booleanExpression = kriter(sozlukMadde, sorgu);

		JPAQueryFactory queryFactory = new JPAQueryFactory(em);
		JPAQuery<SozlukMadde> query = queryFactory.
				select(QSozlukMadde.sozlukMadde).
				from(QSozlukMadde.sozlukMadde);

		if (booleanExpression != null)
			query = query.where(booleanExpression);

		long total = query.fetchCount();

		if (sort != null) {
			for (Map.Entry<String, SortMeta> entry : sort.entrySet()) {
				PathBuilder<SozlukMadde> builder = new PathBuilder<SozlukMadde>(QSozlukMadde.sozlukMadde.getType(), QSozlukMadde.sozlukMadde.getMetadata());

				if (entry.getValue().getOrder().isAscending()) {
					query.orderBy(builder.getString(entry.getKey()).asc());
				} else {
					query.orderBy(builder.getString(entry.getKey()).desc());
				}
			}
		}

		query = query.offset(pageNumber * pageSize).limit(pageSize);
		List<SozlukMadde> list = (List<SozlukMadde>) query.fetch();

		return new PageImpl<>(list, PageRequest.of(pageNumber, pageSize), total);
	}

	public List<SozlukMadde> combo(String sorgu) {
		BooleanExpression booleanExpression = null;

		if(Util.notEmpty(sorgu)) {
			booleanExpression = Util.or(booleanExpression, QSozlukMadde.sozlukMadde.ad.likeIgnoreCase(sorgu+"%"));
		}

		JPAQueryFactory queryFactory = new JPAQueryFactory(em);
		JPAQuery<SozlukMadde> query = queryFactory.
				select(QSozlukMadde.sozlukMadde).
				from(QSozlukMadde.sozlukMadde).
				limit(Util.LIMIT);

		if (booleanExpression != null)
			query = query.where(booleanExpression);

		return (List<SozlukMadde>) query.fetch();
	}

}

