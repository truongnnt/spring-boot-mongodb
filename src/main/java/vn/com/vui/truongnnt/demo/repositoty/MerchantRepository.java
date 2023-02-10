package vn.com.vui.truongnnt.demo.repositoty;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Repository;

import vn.com.vui.truongnnt.demo.entity.MerchantEntity;
import vn.com.vui.truongnnt.demo.entity.UserEntity;
import vn.com.vui.truongnnt.demo.model.Category;

@Repository
public interface MerchantRepository extends MongoRepositoryCustom<MerchantEntity, String> {

	Optional<MerchantEntity> findByName(String name);

	List<MerchantEntity> findByPresident(UserEntity president);
	
	List<MerchantEntity> findByCategory(Category category);
}
