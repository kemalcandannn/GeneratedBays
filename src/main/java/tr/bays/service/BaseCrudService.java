package tr.bays.service;

import java.util.List;
import java.util.Map;

import org.primefaces.model.SortMeta;

import tr.bays.common.QueryResult;

public interface BaseCrudService<T> extends BaseListingService<T> {

	T kaydet(T t);

	void sil(T t);

	void sil(List<T> tListesi);

	QueryResult<T> listele(int firstRecord, int pageSize, Map<String, SortMeta> sort, T sorguKriteri, String sorgu);

	List<T> combo(String query);

	T getir(long id);

}
