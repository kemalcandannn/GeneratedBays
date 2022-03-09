package tr.bays.customdao.teskilat;

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

import tr.bays.entity.teskilat.KullaniciRol;
import tr.bays.entity.teskilat.QKullaniciRol;
import util.Util;

@Repository
public class CustomKullaniciRolRepository {

	@PersistenceContext
	private EntityManager em;

	public BooleanExpression kriter(KullaniciRol kullaniciRol, String sorgu) {
		BooleanExpression booleanExpression = null;

		if(kullaniciRol.getRol_id() != null && kullaniciRol.getRol_id().getId() != null && kullaniciRol.getRol_id().getId() != 0) {
			booleanExpression = Util.and(booleanExpression,QKullaniciRol.kullaniciRol.rol_id.id.eq(kullaniciRol.getRol_id().getId()));
		}

		if(kullaniciRol.getKullanici_id() != null && kullaniciRol.getKullanici_id().getId() != null && kullaniciRol.getKullanici_id().getId() != 0) {
			booleanExpression = Util.and(booleanExpression,QKullaniciRol.kullaniciRol.kullanici_id.id.eq(kullaniciRol.getKullanici_id().getId()));
		}

		if(Util.notEmpty(sorgu)) {
			BooleanExpression booleanExpressionAdvancedSearch = null;

			if(booleanExpressionAdvancedSearch != null)
				booleanExpression = Util.and(booleanExpression, booleanExpressionAdvancedSearch);
		}

		return booleanExpression;
	}

	public Page<KullaniciRol> sorgula(int pageSize, int pageNumber, Map<String, SortMeta> sort, KullaniciRol kullaniciRol, String sorgu) {
		BooleanExpression booleanExpression = kriter(kullaniciRol, sorgu);

		JPAQueryFactory queryFactory = new JPAQueryFactory(em);
		JPAQuery<KullaniciRol> query = queryFactory.
				select(QKullaniciRol.kullaniciRol).
				from(QKullaniciRol.kullaniciRol);

		if (booleanExpression != null)
			query = query.where(booleanExpression);

		long total = query.fetchCount();

		if (sort != null) {
			for (Map.Entry<String, SortMeta> entry : sort.entrySet()) {
				PathBuilder<KullaniciRol> builder = new PathBuilder<KullaniciRol>(QKullaniciRol.kullaniciRol.getType(), QKullaniciRol.kullaniciRol.getMetadata());

				if (entry.getValue().getOrder().isAscending()) {
					query.orderBy(builder.getString(entry.getKey()).asc());
				} else {
					query.orderBy(builder.getString(entry.getKey()).desc());
				}
			}
		}

		query = query.offset(pageNumber * pageSize).limit(pageSize);
		List<KullaniciRol> list = (List<KullaniciRol>) query.fetch();

		return new PageImpl<>(list, PageRequest.of(pageNumber, pageSize), total);
	}

	public List<KullaniciRol> combo(String sorgu) {
		BooleanExpression booleanExpression = null;

		JPAQueryFactory queryFactory = new JPAQueryFactory(em);
		JPAQuery<KullaniciRol> query = queryFactory.
				select(QKullaniciRol.kullaniciRol).
				from(QKullaniciRol.kullaniciRol).
				limit(Util.LIMIT);

		if (booleanExpression != null)
			query = query.where(booleanExpression);

		return (List<KullaniciRol>) query.fetch();
	}

}

