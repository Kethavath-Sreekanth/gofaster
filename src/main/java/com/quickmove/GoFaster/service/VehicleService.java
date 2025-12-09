import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.quickmove.GoFaster.dto.AvailableVehicleDto;
import com.quickmove.GoFaster.dto.CustomerDto;
import com.quickmove.GoFaster.dto.VehicleDetails;
import com.quickmove.GoFaster.dto.VehicleDto;
import com.quickmove.GoFaster.entity.Customer;
import com.quickmove.GoFaster.entity.Vehicle;
import com.quickmove.GoFaster.repository.CustomerRepository;
import com.quickmove.GoFaster.repository.VehicleRepository;

@Service
public class VehicleService {

    @Autowired
    private VehicleRepository vehicleRepository;

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private LocationIQService locationIQService;

    public AvailableVehicleDto seeAllAvailabilityVehicle(long mobileNo, String destinationCity) {

        Customer customer = customerRepository.findByMobileNo(mobileNo);

        double distance = locationIQService.getDistance(customer.getCurrentLocation(), destinationCity);

        List<Vehicle> vehicleList = vehicleRepository.findByVehiclecurrentCity(customer.getCurrentLocation());

        AvailableVehicleDto dto = new AvailableVehicleDto();
        dto.setSource(customer.getCurrentLocation());
        dto.setDestination(destinationCity);
        dto.setDistance(distance);

        // Convert Customer -> CustomerDto
        CustomerDto cd = new CustomerDto();
        cd.setName(customer.getName());
        cd.setAge(customer.getAge());
        cd.setGender(customer.getGender());
        cd.setMobileNo(customer.getMobileNo());
        cd.setEmailId(customer.getEmailId());
        dto.setCustomer(cd);

        List<VehicleDetails> detailsList = new ArrayList<>();

        for (Vehicle v : vehicleList) {

        	VehicleDto vdto = new VehicleDto();
        	vdto.setVehicleName(v.getVehicleName());
        	vdto.setVehicleNo(v.getVehicleNo());
        	vdto.setVehicleType(v.getVehicleType());
        	vdto.setVehicleModel(v.getVehicleModel());
        	vdto.setVehicleCapacity(v.getVehiclecapaCity());
        	vdto.setPricePerKm(v.getPricePerKm());
        	vdto.setAverageSpeed(v.getAverageSpeed());


            double fare = v.getPricePerKm() * distance;
            double time = distance / v.getAverageSpeed();

            VehicleDetails vd = new VehicleDetails();
            vd.setVehicle(vdto);
            vd.setFare(fare);
            vd.setEstimatedTime(time);

            detailsList.add(vd);
        }

        dto.setAvailableVehicles(detailsList);

        return dto;
    }
}
