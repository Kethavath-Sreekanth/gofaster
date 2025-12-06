package com.quickmove.GoFaster.service;

import org.springframework.beans.factory.annotation.Autowired; 
import org.springframework.stereotype.Service;
import com.quickmove.GoFaster.dto.RegisterDriverVehiclesDto;
import com.quickmove.GoFaster.entity.Driver;
import com.quickmove.GoFaster.entity.Vehicle;
import com.quickmove.GoFaster.repository.DriverRepository;
import com.quickmove.GoFaster.repository.VehicleRepository;

@Service
public class RegisterDriverVehiclesDtoService {

	    @Autowired
	    private DriverRepository driverRepo;

	    @Autowired
	    private VehicleRepository vehicleRepo;

	    public Driver saveRegisterDriverVehiclesDto(RegisterDriverVehiclesDto dto) {

	        Vehicle vehicle = new Vehicle();
	        vehicle.setVehicleName(dto.getVehicleName());
	        vehicle.setVehicleNo(String.valueOf(dto.getVehicleNo()));
	        vehicle.setVehicleType(dto.getVehicleType());
	        vehicle.setVehiclecapaCity(dto.getVehicleCapacity());
	        vehicle.setPricePerKm(dto.getPricePerKm());

	        vehicleRepo.save(vehicle);

	        Driver driver = new Driver();
	        driver.setLicenceNo(String.valueOf(dto.getLicenceNo()));
	        driver.setUpiId(String.valueOf(dto.getUpiId()));
	        driver.setName(dto.getDriverName());
	        driver.setAge(dto.getAge());
	        driver.setMobileNo(dto.getMobileNumber());
	        driver.setGender(String.valueOf(dto.getGender()));
	        driver.setMailId(dto.getMailId());
	        driver.setVehicle(vehicle);

	        return driverRepo.save(driver);
	    }
}
