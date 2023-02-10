package vn.com.vui.truongnnt.demo.adapter;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import vn.com.vui.truongnnt.demo.model.PageSize;

@Component
public class PageSizeConverter implements Converter<String, PageSize>{

	@Override
	public PageSize convert(String source) {
		// TODO Auto-generated method stub
		return PageSize.valueOfNameOrSize(source);
	}

}
