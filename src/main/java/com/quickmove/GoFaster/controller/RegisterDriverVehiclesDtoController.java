package com.quickmove.GoFaster.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.quickmove.GoFaster.dto.RegisterDriverVehiclesDto;
import com.quickmove.GoFaster.entity.Driver;
import com.quickmove.GoFaster.service.RegisterDriverVehiclesDtoService;
import com.quickmove.GoFaster.util.ResponseStructure;

@RestController
public class RegisterDriverVehiclesDtoController {

	@Autowired 
	private RegisterDriverVehiclesDtoService ds;
	
	@PostMapping("/registerdrivervehiclesdto")
	public ResponseEntity<ResponseStructure<Driver>> saveRegisterDriverVehicle(@RequestBody RegisterDriverVehiclesDto dv) { 
		return ds.saveRegisterDriverVehiclesDto(dv);
		}
	
	

}
