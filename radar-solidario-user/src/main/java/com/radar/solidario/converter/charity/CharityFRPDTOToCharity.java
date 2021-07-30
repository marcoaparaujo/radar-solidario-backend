package com.radar.solidario.converter.charity;

import org.modelmapper.Converter;
import org.modelmapper.spi.MappingContext;

import com.radar.solidario.dto.charity.CharityFRPDTO;
import com.radar.solidario.entity.Address;
import com.radar.solidario.entity.Charity;

public class CharityFRPDTOToCharity implements Converter<CharityFRPDTO, Charity> {

	@Override
	public Charity convert(MappingContext<CharityFRPDTO, Charity> context) {
		CharityFRPDTO source = context.getSource();

		Address address = Address.builder().cep(source.getAddress().getCep())
				.complement(source.getAddress().getComplement()).neighborhood(source.getAddress().getNeighborhood())
				.number(source.getAddress().getNumber()).phone(source.getAddress().getPhone())
				.street(source.getAddress().getStreet()).build();

		return Charity.builder().id(source.getId()).name(source.getName()).address(address).build();
	}
}
