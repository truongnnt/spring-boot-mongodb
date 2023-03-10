package vn.com.vui.truongnnt.demo.repositoty;

import java.text.ParseException;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import com.querydsl.core.types.Predicate;
import com.querydsl.core.types.dsl.PathBuilder;

import vn.com.vui.truongnnt.demo.entity.UserEntity;
import vn.com.vui.truongnnt.demo.model.PageSize;

@Repository
public interface UserRepository extends MongoRepositoryCustom<UserEntity, String> {

	// @Query("{ 'name' : ?0 }")
	// List<User> findUsersByName(String name);

	// @Query("{ 'age' : { $gt: ?0, $lt: ?1 } }")
	// List<User> findUsersByAgeBetween(int ageGT, int ageLT);

	// @Query("{ 'name' : { $regex: ?0 } }")
	// List<User> findUsersByRegexpName(String regexp);

	// @Query(value = "{}", fields = "{name : 1}")
	// List<User> findNameAndId();

	// @Query(value = "{}", fields = "{_id : 0}")
	// List<User> findNameAndAgeExcludeId();

	/**
	 * FindByX: finding all users with the given {name}
	 * 
	 * @param name field {name}
	 * @return List of User collection
	 */
	List<UserEntity> findByName(String name);

	/**
	 * FindByX: finding all users with the given {email}
	 * 
	 * @param email field {email}
	 * @return List of User collection
	 */
	List<UserEntity> findByEmail(String email);

	/**
	 * Between: this will return all users with ages between ageGT and ageLT
	 * 
	 * @param ageGT greater than or equal
	 * @param ageLT less than or equal
	 * @param page
	 * @return
	 */
	// @Query("{ 'age' : { $gte: ?0, $lte: ?1 } }")
	Page<UserEntity> findByAgeBetween(int ageGT, int ageLT, Pageable page);

//	List<User> findByNameLikeOrderByAgeAsc(String name);
//	List<User> findByNameStartingWith(String regexp);
//
//	List<User> findByNameEndingWith(String regexp);

	/**
	 * Find User per Page by Age Between {ageGT} and {ageLT}
	 * 
	 * @param ageGT    greater than
	 * @param ageLT    less than
	 * @param page     page no
	 * @param pageSize page size
	 * @param sortBy   field sorted
	 * @param order    order (ASC/DESC)
	 * @return page of User collection
	 */
	default Page<UserEntity> findUsersByAgeBetween(int ageGT, int ageLT, int page, PageSize pageSize, String sortBy,
			com.querydsl.core.types.Order order) {
		return findByAgeBetween(ageGT, ageLT, getPageable(page, pageSize, sortBy, order));
	}

	default Page<UserEntity> findByAgeBetweenWithQ(int ageGT, int ageLT, int page, PageSize pageSize, String sortBy,
			com.querydsl.core.types.Order order) throws ParseException {
		PathBuilder<UserEntity> user = new PathBuilder<UserEntity>(UserEntity.class, "user");
		Predicate filter = user.getNumber("age", Integer.class).between(ageGT, ageLT);
		return findAll(filter, getPageable(page, pageSize, sortBy, order));
//		return findAll(QUser.user.age.between(ageGT, ageLT)
//			.and(QUser.user.creationDate.after(new SimpleDateFormat("yyyy-MM-dd").parse("2023-02-08")))	
//				, getPageable(page, pageSize, sortBy, order));
	}
}
