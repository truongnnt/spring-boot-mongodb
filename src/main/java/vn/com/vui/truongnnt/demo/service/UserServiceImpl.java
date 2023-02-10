package vn.com.vui.truongnnt.demo.service;

import java.text.ParseException;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.querydsl.core.types.Order;

import vn.com.vui.truongnnt.demo.entity.UserEntity;
import vn.com.vui.truongnnt.demo.model.PageSize;
import vn.com.vui.truongnnt.demo.repositoty.UserRepository;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	public UserRepository userRepository;

	@Override
	public UserEntity save(UserEntity user) {
		// TODO Auto-generated method stub
		return userRepository.save(user);
	}

	@Override
	public List<UserEntity> findAllUsers() {
		// TODO Auto-generated method stub
		return userRepository.findAll();
	}

	@Override
	public Optional<UserEntity> findUserById(String userId) {
		// TODO Auto-generated method stub
		return userRepository.findById(userId);
	}

	@Override
	public List<UserEntity> findUsersByName(String name) {
		// TODO Auto-generated method stub
		return userRepository.findByName(name);
	}

	@Override
	public List<UserEntity> findUsersByEmail(String email) {
		// TODO Auto-generated method stub
		return userRepository.findByEmail(email);
	}

	@Override
	public Page<UserEntity> findUsersPerPage(int page, PageSize pageSize, String sortBy, Order order) {
		// TODO Auto-generated method stub
		return userRepository.findAll(page, pageSize, sortBy, order);
	}

	@Override
	public Page<UserEntity> findUsersByAgeBetween(int ageGT, int ageLT, int page, PageSize pageSize, String sortBy,
			com.querydsl.core.types.Order order) {
		return userRepository.findUsersByAgeBetween(ageGT, ageLT, page, pageSize, sortBy, order);
	}

	@Override
	public Page<UserEntity> findUsersByAgeBetweenWithQ(int ageGT, int ageLT, int page, PageSize pageSize, String sortBy,
			Order order) {
		// TODO Auto-generated method stub
		try {
			return userRepository.findByAgeBetweenWithQ(ageGT, ageLT, page, pageSize, sortBy, order);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
		}
	}
}
