package vn.com.vui.truongnnt.demo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;

import vn.com.vui.truongnnt.demo.entity.User;
import vn.com.vui.truongnnt.demo.model.PageSize;

public interface UserService {
	User save(User user);

	List<User> findAllUsers();

	Optional<User> findUserById(String userId);

	List<User> findUsersByName(String name);

	List<User> findUsersByEmail(String email);

	Page<User> findUsersPerPage(int page, PageSize pageSize, String sortBy, com.querydsl.core.types.Order order);

	Page<User> findUsersByAgeBetween(int ageGT, int ageLT, int page, PageSize pageSize, String sortBy,
			com.querydsl.core.types.Order order);
	
	Page<User> findUsersByAgeBetweenWithQ(int ageGT, int ageLT, int page, PageSize pageSize, String sortBy,
			com.querydsl.core.types.Order order);
}
