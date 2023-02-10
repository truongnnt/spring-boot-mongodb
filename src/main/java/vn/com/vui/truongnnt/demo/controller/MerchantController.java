package vn.com.vui.truongnnt.demo.controller;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mongodb.client.model.geojson.Point;
import com.mongodb.client.model.geojson.Position;
import com.mongodb.client.result.UpdateResult;

import vn.com.vui.truongnnt.demo.entity.MerchantEntity;
import vn.com.vui.truongnnt.demo.entity.StoreEntity;
import vn.com.vui.truongnnt.demo.model.Merchant;
import vn.com.vui.truongnnt.demo.service.MerchantService;

@RestController(value = "merchants")
@RequestMapping(path = "/merchants")
public class MerchantController {

	@Autowired
	private MerchantService merchantService;

	@PostMapping(produces = "application/json")
	public MerchantEntity create(@RequestBody Merchant merchant) {
		
		return merchantService.add(merchant);
	}

	@PostMapping(path = "/{merchantId}/stores", produces = "application/json")
	public UpdateResult addStore(@PathVariable String merchantId, @RequestBody StoreEntity store) {
		return merchantService.addStore(merchantId, store);
	}
	
	@GetMapping(path = "/{name}", produces = "application/json")
	public MerchantEntity get(@PathVariable String name) {
		return merchantService.get(name);
	}
}
