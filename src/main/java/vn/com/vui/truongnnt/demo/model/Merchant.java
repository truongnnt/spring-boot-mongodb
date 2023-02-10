package vn.com.vui.truongnnt.demo.model;

import java.io.InputStream;
import java.util.List;
import java.util.Set;

import lombok.Data;
import vn.com.vui.truongnnt.demo.entity.StoreEntity;
import vn.com.vui.truongnnt.demo.entity.UserEntity;

@Data
public class Merchant {

	private String name;

	private List<Double> position;

	private Set<StoreEntity> stores;

	private UserEntity president;

	private InputStream icon;

	private Category category;
}
