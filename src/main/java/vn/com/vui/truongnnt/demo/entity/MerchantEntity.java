package vn.com.vui.truongnnt.demo.entity;

import java.io.InputStream;
import java.util.Set;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.GeoSpatialIndexType;
import org.springframework.data.mongodb.core.index.GeoSpatialIndexed;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import com.mongodb.client.model.geojson.Point;
import com.mongodb.gridfs.GridFS;
import com.querydsl.core.annotations.PropertyType;
import com.querydsl.core.annotations.QueryType;

import lombok.Builder;
import lombok.Data;
import vn.com.vui.truongnnt.demo.model.Category;

@Document(collection = "merchant")
@Data
@Builder
public class MerchantEntity {

	@Id
	private String id;

	@Indexed(unique = true)
	private String name;

	@GeoSpatialIndexed(type = GeoSpatialIndexType.GEO_2DSPHERE)
	private Point head;
	
	@DBRef
	private Set<StoreEntity> stores;
	
	@DBRef
	@Indexed
	private UserEntity president;
	
	@QueryType(PropertyType.NONE)
	private InputStream icon;
	
	@Indexed
	private Category category;
}
