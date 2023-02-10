package vn.com.vui.truongnnt.demo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;

import vn.com.vui.truongnnt.demo.entity.UserEntity;
import vn.com.vui.truongnnt.demo.model.PageSize;

public interface UserService {
	UserEntity save(UserEntity user);

	List<UserEntity> findAllUsers();

	Optional<UserEntity> findUserById(String userId);

	List<UserEntity> findUsersByName(String name);

	List<UserEntity> findUsersByEmail(String email);

	Page<UserEntity> findUsersPerPage(int page, PageSize pageSize, String sortBy, com.querydsl.core.types.Order order);

	Page<UserEntity> findUsersByAgeBetween(int ageGT, int ageLT, int page, PageSize pageSize, String sortBy,
			com.querydsl.core.types.Order order);
	
	Page<UserEntity> findUsersByAgeBetweenWithQ(int ageGT, int ageLT, int page, PageSize pageSize, String sortBy,
			com.querydsl.core.types.Order order);
}
