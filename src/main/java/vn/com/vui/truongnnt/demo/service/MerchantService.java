package vn.com.vui.truongnnt.demo.service;

import com.mongodb.client.result.UpdateResult;

import vn.com.vui.truongnnt.demo.entity.MerchantEntity;
import vn.com.vui.truongnnt.demo.entity.StoreEntity;
import vn.com.vui.truongnnt.demo.model.Merchant;

public interface MerchantService {
	MerchantEntity add(Merchant merchant);

	UpdateResult addStore(String merchantId, StoreEntity store);
	
	MerchantEntity get(String name);
}
