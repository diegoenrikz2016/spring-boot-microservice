
package com.web.springboot.micro.model.mapper;

import java.util.List;

import org.mapstruct.Mapper;

import com.web.springboot.micro.model.dto.MovimientoDto;
import com.web.springboot.micro.model.entity.Movimiento;

@Mapper(componentModel = "spring", uses = { CuentaMapper.class })
public interface MovimientosMapper {

	MovimientoDto toDto(Movimiento target);

	List<MovimientoDto> toDtoList(List<Movimiento> entityList);

	Movimiento toEntity(MovimientoDto source);

	List<Movimiento> toEntityList(List<MovimientoDto> dtoList);

}