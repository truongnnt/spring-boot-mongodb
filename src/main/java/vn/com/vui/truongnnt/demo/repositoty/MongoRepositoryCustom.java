package vn.com.vui.truongnnt.demo.repositoty;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.repository.NoRepositoryBean;

import com.querydsl.core.types.Order;

import vn.com.vui.truongnnt.demo.model.PageSize;

@NoRepositoryBean
public interface MongoRepositoryCustom<T, ID> extends MongoRepository<T, ID>, QuerydslPredicateExecutor<T> {

//	default Page<T> find(MongoTemplate mongoTemplate, Query query, int page, PageSize pageSize, Class<T> cls) {
//		long count = mongoTemplate.count(query, cls);
//		Pageable pageable = PageRequest.of(page, pageSize.size);
//		List<T> contents;
//		if (count > 0) {
//			// paging
//			query.with(pageable);
//			contents = mongoTemplate.find(query, cls);
//		} else {
//			contents = Collections.emptyList();
//		}
//		return new PageImpl<>(contents, pageable, count);
//	}

	default Page<T> findAll(int page, PageSize pageSize) {
		return findAll(getPageable(page, pageSize, null, Order.ASC));
	}

	default Page<T> findAll(int page, PageSize pageSize, String sortBy, Order order) {
		return findAll(getPageable(page, pageSize, sortBy, order));
	}

	default Pageable getPageable(int page, PageSize pageSize, String sortBy, Order order) {
		return PageRequest.of(page, pageSize.size,
				Sort.by(order == com.querydsl.core.types.Order.ASC
						? org.springframework.data.domain.Sort.Order.asc(sortBy)
						: org.springframework.data.domain.Sort.Order.desc(sortBy)));
	}
}
