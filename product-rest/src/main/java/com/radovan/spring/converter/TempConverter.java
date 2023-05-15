package com.radovan.spring.converter;

import java.text.DecimalFormat;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.radovan.spring.dto.ProductDto;
import com.radovan.spring.entity.ProductEntity;

@Component
public class TempConverter {

	@Autowired
	private ModelMapper mapper; // we are using modelmapper to convert entity to dto and vice versa

	private static DecimalFormat decfor = new DecimalFormat("0.00"); // decfor will format double number with 2 decimals

	public ProductDto productEntityToDto(ProductEntity productEntity) {
		ProductDto returnValue = mapper.map(productEntity, ProductDto.class);
		Double price = Double.valueOf(decfor.format(returnValue.getPrice()));
		returnValue.setPrice(price);
		return returnValue;
	}

	public ProductEntity productDtoToEntity(ProductDto productDto) {
		ProductEntity returnValue = mapper.map(productDto, ProductEntity.class);
		Double price = Double.valueOf(decfor.format(returnValue.getPrice()));
		returnValue.setPrice(price);
		return returnValue;
	}

}
