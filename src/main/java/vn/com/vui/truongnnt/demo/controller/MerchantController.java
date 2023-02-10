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

import vn.com.vui.truongnnt.demo.entity.Merchant;
import vn.com.vui.truongnnt.demo.entity.Store;
import vn.com.vui.truongnnt.demo.service.MerchantService;

@RestController(value = "merchants")
@RequestMapping(path = "/merchants")
public class MerchantController {

	@Autowired
	private MerchantService merchantService;

	@PostMapping(produces = "application/json")
	public Merchant create(@RequestBody Merchant merchant) {
		Point point = new Point(
				new Position(Arrays.asList(Double.parseDouble("107687613"), Double.parseDouble("1067000027.18"))));
		merchant.setHead(point);
		return merchantService.add(merchant);
	}

	@PostMapping(path = "/{merchantId}/stores", produces = "application/json")
	public UpdateResult addStore(@PathVariable String merchantId, @RequestBody Store store) {
		return merchantService.addStore(merchantId, store);
	}
	
	@GetMapping(path = "/{name}", produces = "application/json")
	public Merchant get(@PathVariable String name) {
		return merchantService.get(name);
	}
}
