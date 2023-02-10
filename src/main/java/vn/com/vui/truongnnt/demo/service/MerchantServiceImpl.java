package vn.com.vui.truongnnt.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.mongodb.MongoWriteException;
import com.mongodb.client.result.UpdateResult;

import vn.com.vui.truongnnt.demo.entity.Merchant;
import vn.com.vui.truongnnt.demo.entity.Store;
import vn.com.vui.truongnnt.demo.repositoty.MerchantRepository;

@Service
public class MerchantServiceImpl implements MerchantService {

	@Autowired
	private MerchantRepository merchantRepository;

	@Autowired
	private MongoTemplate mongoTemplate;

	@Override
	public Merchant add(Merchant merchant) {
		// TODO Auto-generated method stub
		if (merchant.getStores() != null) {
			mongoTemplate.insertAll(merchant.getStores());
		}
		return mongoTemplate.insert(merchant);
	}

	@Override
	public UpdateResult addStore(String merchantId, Store store) {
		// TODO Auto-generated method stub
		try {
			mongoTemplate.save(store);
		} catch (Throwable ex) {
			resolseException(ex);
		}
		Query query = new Query(Criteria.where("name").is(merchantId));
		Update update = new Update();
		update.addToSet("stores", store);

		UpdateResult result = mongoTemplate.updateMulti(query, update, Merchant.class);
		return result;
	}

	@Override
	public Merchant get(String name) {
		// TODO Auto-generated method stub
		return merchantRepository.findByName(name).get();
	}

	private void resolseException(Throwable ex) {
		if (ex instanceof MongoWriteException && ((MongoWriteException) ex).getError().getCode() == 11000) {
			throw new ResponseStatusException(HttpStatus.CONFLICT, ex.getMessage());
		} else if (ex.getCause() != null) {
			resolseException(ex.getCause());
		}
		throw new RuntimeException(ex);
	}
}
