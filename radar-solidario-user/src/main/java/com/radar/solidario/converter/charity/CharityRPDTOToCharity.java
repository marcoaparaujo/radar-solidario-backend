package com.radar.solidario.converter.charity;

import org.modelmapper.Converter;
import org.modelmapper.spi.MappingContext;

import com.radar.solidario.dto.charity.CharityRPDTO;
import com.radar.solidario.entity.Address;
import com.radar.solidario.entity.Charity;

public class CharityRPDTOToCharity implements Converter<CharityRPDTO, Charity> {

	@Override
	public Charity convert(MappingContext<CharityRPDTO, Charity> context) {
		CharityRPDTO source = context.getSource();

		Address address = Address.builder().cep(source.getAddress().getCep())
				.complement(source.getAddress().getComplement()).neighborhood(source.getAddress().getNeighborhood())
				.number(source.getAddress().getNumber()).phone(source.getAddress().getPhone())
				.street(source.getAddress().getStreet()).build();

		return Charity.builder().name(source.getName()).address(address).build();
	}
}
