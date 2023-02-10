package vn.com.vui.truongnnt.demo;

public enum MongoDbCollection {

	User("user"), Address("address");

	public final String name;

	private MongoDbCollection(String name) {
		// TODO Auto-generated constructor stub
		this.name = name;
	}
}
