
package com.web.springboot.micro.model.mapper;

import java.util.List;

import org.mapstruct.Mapper;

import com.web.springboot.micro.model.dto.ClienteDto;
import com.web.springboot.micro.model.entity.Cliente;

@Mapper(componentModel = "spring")
public interface ClienteMapper {
	
	
	ClienteDto toDto(Cliente target);
	
	List<ClienteDto> toDtoList(List<Cliente> entityList);
	
	Cliente toEntity(ClienteDto source);

	List<Cliente> toEntityList(List<ClienteDto> dtoList);
	
	
}