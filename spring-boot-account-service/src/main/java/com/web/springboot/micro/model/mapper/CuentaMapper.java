
package com.web.springboot.micro.model.mapper;

import java.util.List;

import org.mapstruct.Mapper;

import com.web.springboot.micro.model.dto.CuentaDto;
import com.web.springboot.micro.model.entity.Cuenta;

@Mapper(componentModel = "spring", uses = { MovimientosMapper.class })
public interface CuentaMapper {
	
	CuentaDto toDto(Cuenta target);
	
	List<CuentaDto> toDtoList(List<Cuenta> entityList);
	
	Cuenta toEntity(CuentaDto source);

	List<Cuenta> toEntityList(List<CuentaDto> dtoList);
	
	
}