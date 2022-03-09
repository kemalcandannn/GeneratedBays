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

import tr.bays.entity.sozluk.Sozluk;
import tr.bays.entity.sozluk.QSozluk;
import util.Util;

@Repository
public class CustomSozlukRepository {

	@PersistenceContext
	private EntityManager em;

	public BooleanExpression kriter(Sozluk sozluk, String sorgu) {
		BooleanExpression booleanExpression = null;

		if(Util.notEmpty(sozluk.getAd())) {
			booleanExpression = Util.and(booleanExpression,QSozluk.sozluk.ad.likeIgnoreCase(sozluk.getAd()+"%"));
		}

		if(Util.notEmpty(sozluk.getKisaltma())) {
			booleanExpression = Util.and(booleanExpression,QSozluk.sozluk.kisaltma.likeIgnoreCase(sozluk.getKisaltma()+"%"));
		}

		if(Util.notEmpty(sozluk.getAciklama())) {
			booleanExpression = Util.and(booleanExpression,QSozluk.sozluk.aciklama.likeIgnoreCase(sozluk.getAciklama()+"%"));
		}

		if(Util.notEmpty(sozluk.getListe_aciklama())) {
			booleanExpression = Util.and(booleanExpression,QSozluk.sozluk.liste_aciklama.likeIgnoreCase(sozluk.getListe_aciklama()+"%"));
		}

		if(sozluk.getKullanim_sekli() != 0) {
			booleanExpression = Util.and(booleanExpression,QSozluk.sozluk.kullanim_sekli.eq(sozluk.getKullanim_sekli()));
		}

		if(sozluk.getAktif() != 0) {
			booleanExpression = Util.and(booleanExpression,QSozluk.sozluk.aktif.eq(sozluk.getAktif()));
		}

		if(Util.notEmpty(sorgu)) {
			BooleanExpression booleanExpressionAdvancedSearch = null;
			booleanExpressionAdvancedSearch = Util.or(booleanExpressionAdvancedSearch, QSozluk.sozluk.ad.likeIgnoreCase("%" + sorgu + "%"));
			booleanExpressionAdvancedSearch = Util.or(booleanExpressionAdvancedSearch, QSozluk.sozluk.kisaltma.likeIgnoreCase("%" + sorgu + "%"));
			booleanExpressionAdvancedSearch = Util.or(booleanExpressionAdvancedSearch, QSozluk.sozluk.aciklama.likeIgnoreCase("%" + sorgu + "%"));
			booleanExpressionAdvancedSearch = Util.or(booleanExpressionAdvancedSearch, QSozluk.sozluk.liste_aciklama.likeIgnoreCase("%" + sorgu + "%"));

			if(booleanExpressionAdvancedSearch != null)
				booleanExpression = Util.and(booleanExpression, booleanExpressionAdvancedSearch);
		}

		return booleanExpression;
	}

	public Page<Sozluk> sorgula(int pageSize, int pageNumber, Map<String, SortMeta> sort, Sozluk sozluk, String sorgu) {
		BooleanExpression booleanExpression = kriter(sozluk, sorgu);

		JPAQueryFactory queryFactory = new JPAQueryFactory(em);
		JPAQuery<Sozluk> query = queryFactory.
				select(QSozluk.sozluk).
				from(QSozluk.sozluk);

		if (booleanExpression != null)
			query = query.where(booleanExpression);

		long total = query.fetchCount();

		if (sort != null) {
			for (Map.Entry<String, SortMeta> entry : sort.entrySet()) {
				PathBuilder<Sozluk> builder = new PathBuilder<Sozluk>(QSozluk.sozluk.getType(), QSozluk.sozluk.getMetadata());

				if (entry.getValue().getOrder().isAscending()) {
					query.orderBy(builder.getString(entry.getKey()).asc());
				} else {
					query.orderBy(builder.getString(entry.getKey()).desc());
				}
			}
		}

		query = query.offset(pageNumber * pageSize).limit(pageSize);
		List<Sozluk> list = (List<Sozluk>) query.fetch();

		return new PageImpl<>(list, PageRequest.of(pageNumber, pageSize), total);
	}

	public List<Sozluk> combo(String sorgu) {
		BooleanExpression booleanExpression = null;

		if(Util.notEmpty(sorgu)) {
			booleanExpression = Util.or(booleanExpression, QSozluk.sozluk.ad.likeIgnoreCase(sorgu+"%"));
		}

		JPAQueryFactory queryFactory = new JPAQueryFactory(em);
		JPAQuery<Sozluk> query = queryFactory.
				select(QSozluk.sozluk).
				from(QSozluk.sozluk).
				limit(Util.LIMIT);

		if (booleanExpression != null)
			query = query.where(booleanExpression);

		return (List<Sozluk>) query.fetch();
	}

}

