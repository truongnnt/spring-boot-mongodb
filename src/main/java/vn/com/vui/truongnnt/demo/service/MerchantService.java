package vn.com.vui.truongnnt.demo.service;

import com.mongodb.client.result.UpdateResult;

import vn.com.vui.truongnnt.demo.entity.Merchant;
import vn.com.vui.truongnnt.demo.entity.Store;

public interface MerchantService {
	Merchant add(Merchant merchant);

	UpdateResult addStore(String merchantId, Store store);
	
	Merchant get(String name);
}
