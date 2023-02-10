package vn.com.vui.truongnnt.demo.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.mongodb.MongoWriteException;

import vn.com.vui.truongnnt.demo.entity.UserEntity;
import vn.com.vui.truongnnt.demo.model.PageSize;
import vn.com.vui.truongnnt.demo.service.UserService;

@RestController
@RequestMapping(value = "/users")
public class UserController {

	private final Logger LOG = LoggerFactory.getLogger(getClass());

	@Autowired
	private UserService userService;

//	@Autowired
//	private RedisTemplate redisTemplate;

	@RequestMapping(value = "", method = RequestMethod.POST)
	public UserEntity addNewUsers(@RequestBody UserEntity user) {
		LOG.info("Saving user.");
		try {
			return userService.save(user);
		} catch (Exception e) {
			// TODO: handle exception
			if (e.getCause() != null && e.getCause() instanceof MongoWriteException) {
				if (((MongoWriteException) e.getCause()).getError().getCode() == 11000) {
					throw new ResponseStatusException(HttpStatus.CONFLICT,
							"Duplicate the name [" + user.getName() + "]");
				}
			}
			throw e;
		}
	}

	@RequestMapping(value = "", method = RequestMethod.GET)
	public List<UserEntity> getAllUsers() {
		LOG.info("Getting all users.");
		return userService.findAllUsers();
	}

	@RequestMapping(value = "/id/{userId}", method = RequestMethod.GET)
	public UserEntity getUser(@PathVariable String userId) {
		LOG.info("Getting user with ID: {}.", userId);
		UserEntity user = userService.findUserById(userId).orElseThrow(
				() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Not found User with ID [" + userId + "]"));
		return user;
	}

	@RequestMapping(value = "/name/{name}", method = RequestMethod.GET)
	public List<UserEntity> getUserByName(@PathVariable String name, @RequestParam(defaultValue = "1") int page,
			@RequestParam(defaultValue = "10") PageSize size, @RequestParam(defaultValue = "age") String sortBy,
			@RequestParam(defaultValue = "ASC") com.querydsl.core.types.Order order) {
		LOG.info("Getting user with NAME: {}.", name);
		// return userDAL.getUserByName(name);
		return userService.findUsersByName(name);
	}

	@RequestMapping(value = "/email/{email}", method = RequestMethod.GET)
	public List<UserEntity> getByName(@PathVariable String email) {
		LOG.info("Getting user with NAME: {}.", email);
		// return userDAL.getUserByName(name);
		return userService.findUsersByEmail(email);
	}

	@RequestMapping(value = "page", method = RequestMethod.GET)
	public Page<UserEntity> getPageUsers(@RequestParam(defaultValue = "1") int page,
			@RequestParam(defaultValue = "10") PageSize size, @RequestParam(defaultValue = "age") String sortBy,
			@RequestParam(defaultValue = "ASC") com.querydsl.core.types.Order order) {
		LOG.info("Getting page users at page [{}], size {}", page, size);
		// return userDAL.getUsersByPage(page, size);
		return userService.findUsersPerPage(page, size, sortBy, order);
	}

	@RequestMapping(value = "page/age", method = RequestMethod.GET)
	public Page<UserEntity> getPageUsersByAge(@RequestParam int from, @RequestParam int to, @RequestParam int page,
			@RequestParam(defaultValue = "10") PageSize size, @RequestParam(defaultValue = "age") String sortBy,
			@RequestParam(defaultValue = "ASC") com.querydsl.core.types.Order order) {
		LOG.info("Getting users with age from {} to {} ", from, to);
		return userService.findUsersByAgeBetween(from, to, page, size, sortBy, order);
	}

	@RequestMapping(value = "page/ageWithQ", method = RequestMethod.GET)
	public Page<UserEntity> getPageUsersByAgeWithQ(@RequestParam int from, @RequestParam int to, @RequestParam int page,
			@RequestParam(defaultValue = "10") PageSize size, @RequestParam(defaultValue = "age") String sortBy,
			@RequestParam(defaultValue = "ASC") com.querydsl.core.types.Order order) {
		LOG.info("Getting users with age from {} to {} ", from, to);
		return userService.findUsersByAgeBetweenWithQ(from, to, page, size, sortBy, order);
	}

	// @RequestMapping(value = "/settings/{userId}", method = RequestMethod.GET)
	// public Object getAllUserSettings(@PathVariable String userId) {
	// User user = userRepository.findOne(userId);
	// if (user != null) {
	// return user.getUserSettings();
	// } else {
	// return "User not found.";
	// }
	// }

//	@RequestMapping(value = "/settings/{userId}", method = RequestMethod.GET)
//	public Object getAllUserSettings(@PathVariable String userId) {
//		User user = userRepository.findById(userId).get();
//		if (user != null) {
//			return userDAL.getAllUserSettings(userId);
//		} else {
//			return "User not found.";
//		}
//	}

	// @RequestMapping(value = "/settings/{userId}/{key}", method =
	// RequestMethod.GET)
	// public String getUserSetting(@PathVariable String userId, @PathVariable
	// String key) {
	// //User user = userRepository.findOne(userId);
	// String setting = userDAL.getUserSetting(userId, key);
	// LOG.info("Setting = "+setting);
	// if (setting != null) {
	// return setting;
	// } else {
	// return "Setting not found.";
	// }
	// }

//	@RequestMapping(value = "/settings/{userId}/{key}", method = RequestMethod.GET)
//	public String getUserSetting(@PathVariable String userId, @PathVariable String key) {
//		return userDAL.getUserSetting(userId, key);
//	}
//
//	@RequestMapping(value = "/settings/{userId}/{key}/{value}", method = RequestMethod.GET)
//	public String addUserSetting(@PathVariable String userId, @PathVariable String key, @PathVariable String value) {
//		User user = userRepository.findById(userId).get();
//		if (user != null) {
//			user.getUserSettings().put(key, value);
//			userRepository.save(user);
//			return "Key added";
//		} else {
//			return "User not found.";
//		}
//	}
//
//	@RequestMapping(value = "/{userId}/addresses", method = RequestMethod.POST)
//	public Address addAddress(@PathVariable String userId, @RequestBody Address address) {
//		User user = userDAL.getUserById(userId);
//		return addressDAL.addAddress(user, address);
//	}
//
//	@RequestMapping(value = "/{userId}/addresses", method = RequestMethod.GET)
//	public List<Address> getAllAddress(@PathVariable String userId) {
//		User user = userDAL.getUserById(userId);
//		return addressDAL.getAddress(user);
//	}
}