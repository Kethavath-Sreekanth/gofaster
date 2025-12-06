package com.quickmove.GoFaster.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.quickmove.GoFaster.dto.CurrentLocationDTO;
import com.quickmove.GoFaster.entity.Driver;
import com.quickmove.GoFaster.exception.DriverMobileNoNotFound;
import com.quickmove.GoFaster.repository.DriverRepository;

@Service
public class DriverService {

    @Autowired
    private DriverRepository driverRepository;
    @Autowired
    private LocationIQService locationIQService;

    public Driver deleteDriverByMobileNo(Long mobileNo) {
        Driver driver = driverRepository.findByMobileNo(mobileNo);
        if(driver == null) {
            throw new DriverMobileNoNotFound();
        }
        driverRepository.delete(driver);
        return driver;
    }
    
    public Driver updateCurrentVehicleLocation(Long mobileNo, CurrentLocationDTO dto) {

        Driver driver = driverRepository.findByMobileNo(mobileNo);

        if (driver == null) {
            throw new RuntimeException("Driver Not Found with mobile: " + mobileNo);
        }

        // Update latitude & longitude
        driver.setLatitude(dto.getLatitude());
        driver.setLongitude(dto.getLongitude());

        // Get address from LocationIQ
        String address = locationIQService.getAddressFromCoordinates(dto.getLatitude(), dto.getLongitude());
        driver.setCurrentAddress(address);

        return driverRepository.save(driver);
    }
}

