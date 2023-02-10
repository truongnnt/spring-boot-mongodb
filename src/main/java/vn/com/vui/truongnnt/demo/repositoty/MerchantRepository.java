package vn.com.vui.truongnnt.demo.repositoty;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Repository;

import vn.com.vui.truongnnt.demo.entity.Merchant;
import vn.com.vui.truongnnt.demo.entity.User;
import vn.com.vui.truongnnt.demo.model.Category;

@Repository
public interface MerchantRepository extends MongoRepositoryCustom<Merchant, String> {

	Optional<Merchant> findByName(String name);

	List<Merchant> findByPresident(User president);
	
	List<Merchant> findByCategory(Category category);
}
