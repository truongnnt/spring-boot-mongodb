package vn.com.vui.truongnnt.demo.adapter;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import com.mongodb.client.model.geojson.Point;
import com.mongodb.client.model.geojson.Position;

import vn.com.vui.truongnnt.demo.entity.MerchantEntity;
import vn.com.vui.truongnnt.demo.model.Merchant;

@Component
public class MerchantConverter implements Converter<Merchant, MerchantEntity> {

	@Override
	public MerchantEntity convert(Merchant source) {
		// TODO Auto-generated method stub
		return MerchantEntity.builder()
				.category(source.getCategory())
				.head(new Point(new Position(source.getPosition())))
				.icon(source.getIcon())
				.name(source.getName())
				.president(source.getPresident())
				.stores(source.getStores())
				.build();
	}

}
