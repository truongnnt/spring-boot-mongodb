package vn.com.vui.truongnnt.demo.model;

import java.util.stream.Stream;

import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

public enum PageSize {
	FIVE(5), TEN(10), TWENTY(20), FIFTY(50), HUNDRED(100);

	public final int size;

	private PageSize(int size) {
		this.size = size;
	}

	public static PageSize valueOfNameOrSize(String source) {
		// TODO Auto-generated method stub
		try {
			Integer size = Integer.valueOf(source);
			return Stream.of(values()).filter(t -> t.size == size).findFirst().orElseThrow(
					() -> new MethodArgumentTypeMismatchException(source, PageSize.class, null, null, null));
		} catch (NumberFormatException e) {
			// TODO: handle exception
			return valueOf(source);
		}
	}

}
