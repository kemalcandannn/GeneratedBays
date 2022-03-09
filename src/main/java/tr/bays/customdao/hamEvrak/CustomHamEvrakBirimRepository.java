package tr.bays.customdao.hamEvrak;

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

import tr.bays.entity.hamEvrak.HamEvrakBirim;
import tr.bays.entity.hamEvrak.QHamEvrakBirim;
import util.Util;

@Repository
public class CustomHamEvrakBirimRepository {

	@PersistenceContext
	private EntityManager em;

	public BooleanExpression kriter(HamEvrakBirim hamEvrakBirim, String sorgu) {
		BooleanExpression booleanExpression = null;

		if(hamEvrakBirim.getYon() != 0) {
			booleanExpression = Util.and(booleanExpression,QHamEvrakBirim.hamEvrakBirim.yon.eq(hamEvrakBirim.getYon()));
		}

		if(hamEvrakBirim.getAdet() != 0) {
			booleanExpression = Util.and(booleanExpression,QHamEvrakBirim.hamEvrakBirim.adet.eq(hamEvrakBirim.getAdet()));
		}

		if(hamEvrakBirim.getSaklanma_turu() != 0) {
			booleanExpression = Util.and(booleanExpression,QHamEvrakBirim.hamEvrakBirim.saklanma_turu.eq(hamEvrakBirim.getSaklanma_turu()));
		}

		if(hamEvrakBirim.getBirim_id() != null && hamEvrakBirim.getBirim_id().getId() != null && hamEvrakBirim.getBirim_id().getId() != 0) {
			booleanExpression = Util.and(booleanExpression,QHamEvrakBirim.hamEvrakBirim.birim_id.id.eq(hamEvrakBirim.getBirim_id().getId()));
		}

		if(hamEvrakBirim.getDepo_sorumlusu_id() != null && hamEvrakBirim.getDepo_sorumlusu_id().getId() != null && hamEvrakBirim.getDepo_sorumlusu_id().getId() != 0) {
			booleanExpression = Util.and(booleanExpression,QHamEvrakBirim.hamEvrakBirim.depo_sorumlusu_id.id.eq(hamEvrakBirim.getDepo_sorumlusu_id().getId()));
		}

		if(hamEvrakBirim.getBirim_sorumlusu_id() != null && hamEvrakBirim.getBirim_sorumlusu_id().getId() != null && hamEvrakBirim.getBirim_sorumlusu_id().getId() != 0) {
			booleanExpression = Util.and(booleanExpression,QHamEvrakBirim.hamEvrakBirim.birim_sorumlusu_id.id.eq(hamEvrakBirim.getBirim_sorumlusu_id().getId()));
		}

		if(hamEvrakBirim.getFon_id() != null && hamEvrakBirim.getFon_id().getId() != null && hamEvrakBirim.getFon_id().getId() != 0) {
			booleanExpression = Util.and(booleanExpression,QHamEvrakBirim.hamEvrakBirim.fon_id.id.eq(hamEvrakBirim.getFon_id().getId()));
		}

		if(hamEvrakBirim.getHam_evrak_id() != null && hamEvrakBirim.getHam_evrak_id().getId() != null && hamEvrakBirim.getHam_evrak_id().getId() != 0) {
			booleanExpression = Util.and(booleanExpression,QHamEvrakBirim.hamEvrakBirim.ham_evrak_id.id.eq(hamEvrakBirim.getHam_evrak_id().getId()));
		}

		if(Util.notEmpty(sorgu)) {
			BooleanExpression booleanExpressionAdvancedSearch = null;

			if(booleanExpressionAdvancedSearch != null)
				booleanExpression = Util.and(booleanExpression, booleanExpressionAdvancedSearch);
		}

		return booleanExpression;
	}

	public Page<HamEvrakBirim> sorgula(int pageSize, int pageNumber, Map<String, SortMeta> sort, HamEvrakBirim hamEvrakBirim, String sorgu) {
		BooleanExpression booleanExpression = kriter(hamEvrakBirim, sorgu);

		JPAQueryFactory queryFactory = new JPAQueryFactory(em);
		JPAQuery<HamEvrakBirim> query = queryFactory.
				select(QHamEvrakBirim.hamEvrakBirim).
				from(QHamEvrakBirim.hamEvrakBirim);

		if (booleanExpression != null)
			query = query.where(booleanExpression);

		long total = query.fetchCount();

		if (sort != null) {
			for (Map.Entry<String, SortMeta> entry : sort.entrySet()) {
				PathBuilder<HamEvrakBirim> builder = new PathBuilder<HamEvrakBirim>(QHamEvrakBirim.hamEvrakBirim.getType(), QHamEvrakBirim.hamEvrakBirim.getMetadata());

				if (entry.getValue().getOrder().isAscending()) {
					query.orderBy(builder.getString(entry.getKey()).asc());
				} else {
					query.orderBy(builder.getString(entry.getKey()).desc());
				}
			}
		}

		query = query.offset(pageNumber * pageSize).limit(pageSize);
		List<HamEvrakBirim> list = (List<HamEvrakBirim>) query.fetch();

		return new PageImpl<>(list, PageRequest.of(pageNumber, pageSize), total);
	}

	public List<HamEvrakBirim> combo(String sorgu) {
		BooleanExpression booleanExpression = null;

		JPAQueryFactory queryFactory = new JPAQueryFactory(em);
		JPAQuery<HamEvrakBirim> query = queryFactory.
				select(QHamEvrakBirim.hamEvrakBirim).
				from(QHamEvrakBirim.hamEvrakBirim).
				limit(Util.LIMIT);

		if (booleanExpression != null)
			query = query.where(booleanExpression);

		return (List<HamEvrakBirim>) query.fetch();
	}

}

