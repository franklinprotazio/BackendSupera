package br.com.banco.config;

import org.modelmapper.AbstractConverter;
import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import br.com.banco.core.entity.Conta;
import br.com.banco.v1.dto.ContaDTO;

@Configuration
public class ModelMapperConfiguration {

	@Bean
	public static ModelMapper getModelMapper() {
		ModelMapper modelMapper = new ModelMapper();
		modelMapper.getConfiguration().setAmbiguityIgnored(true);
		modelMapper.addConverter(converterStringToString());
		modelMapper.typeMap(Conta.class, ContaDTO.class);

		return modelMapper;
	}

	private static Converter<String, String> converterStringToString() {
		return new AbstractConverter<String, String>() {
			protected String convert(String source) {
				return source == null ? null : source.trim();
			}
		};
	}
}
