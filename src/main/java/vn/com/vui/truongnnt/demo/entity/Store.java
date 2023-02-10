package vn.com.vui.truongnnt.demo.entity;

import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.GeoSpatialIndexType;
import org.springframework.data.mongodb.core.index.GeoSpatialIndexed;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import com.mongodb.client.model.geojson.Point;

import lombok.Data;

@Document(collection = "store")
@Data
public class Store {
	@Id
	private String id;

	@Indexed
	private String name;

	@GeoSpatialIndexed(type = GeoSpatialIndexType.GEO_2DSPHERE)
	private Point location;

	@Indexed
	private User manager;

	private List<User> staffs;
}
