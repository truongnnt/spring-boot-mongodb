package vn.com.vui.truongnnt.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.mongodb.MongoBulkWriteException;
import com.mongodb.MongoWriteException;
import com.mongodb.client.result.UpdateResult;

import vn.com.vui.truongnnt.demo.adapter.MerchantConverter;
import vn.com.vui.truongnnt.demo.entity.MerchantEntity;
import vn.com.vui.truongnnt.demo.entity.StoreEntity;
import vn.com.vui.truongnnt.demo.model.Merchant;
import vn.com.vui.truongnnt.demo.repositoty.MerchantRepository;

@Service
public class MerchantServiceImpl implements MerchantService {

	@Autowired
	private MerchantRepository merchantRepository;

	@Autowired
	private MerchantConverter merchantConverter;

	@Autowired
	private MongoTemplate mongoTemplate;

	@Override
	public MerchantEntity add(Merchant merchant) {
		// TODO Auto-generated method stub
		try {
			if (merchant.getStores() != null) {
				mongoTemplate.insertAll(merchant.getStores());
			}
			return mongoTemplate.insert(merchantConverter.convert(merchant));
		} catch (Exception e) {
			// TODO: handle exception
			throw resolseException(e);
		}
	}

	@Override
	public UpdateResult addStore(String merchantId, StoreEntity store) {
		// TODO Auto-generated method stub
		try {
			mongoTemplate.save(store);

			Query query = new Query(Criteria.where("name").is(merchantId));
			Update update = new Update();
			update.addToSet("stores", store);

			UpdateResult result = mongoTemplate.updateMulti(query, update, MerchantEntity.class);
			return result;
		} catch (Throwable ex) {
			throw resolseException(ex);
		}
	}

	@Override
	public MerchantEntity get(String name) {
		// TODO Auto-generated method stub
		return merchantRepository.findByName(name).get();
	}

	private RuntimeException resolseException(Throwable ex) {
		if (ex instanceof MongoWriteException && ((MongoWriteException) ex).getError().getCode() == 11000) {
			return new ResponseStatusException(HttpStatus.CONFLICT, ex.getMessage());
		} else if (ex instanceof MongoBulkWriteException
				&& ((MongoBulkWriteException) ex).getWriteErrors().get(0).getCode() == 11000) {
			return new ResponseStatusException(HttpStatus.CONFLICT, ex.getMessage());
		} else if (ex.getCause() != null) {
			return resolseException(ex.getCause());
		}
		return new RuntimeException(ex);
	}
}
