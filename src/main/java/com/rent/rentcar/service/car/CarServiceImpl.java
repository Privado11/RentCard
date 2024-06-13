    package com.rent.rentcar.service.car;

    import com.rent.rentcar.dto.car.CarDto;
    import com.rent.rentcar.dto.car.CarMapper;
    import com.rent.rentcar.dto.car.CarToSaveDto;
    import com.rent.rentcar.exception.NotAbleToDeleteException;
    import com.rent.rentcar.exception.NotFoundExceptionEntity;
    import com.rent.rentcar.models.Car;
    import com.rent.rentcar.repository.CarRepository;
    import org.springframework.beans.factory.annotation.Autowired;
    import org.springframework.stereotype.Service;

    import java.time.LocalDateTime;
    import java.util.List;

    @Service
    public class CarServiceImpl implements CarService{

        private final CarRepository carRepository;
        private final CarMapper carMapper;

        @Autowired
        public CarServiceImpl(CarRepository carRepository, CarMapper carMapper) {
            this.carRepository = carRepository;
            this.carMapper = carMapper;
        }

        @Override
        public CarDto getCarById(Long id) throws NotFoundExceptionEntity {
            Car car = carRepository.findById(id)
                    .orElseThrow(() -> new NotFoundExceptionEntity("Car not found."));
            return carMapper.toDto(car);
        }

        @Override
        public CarDto addCar(CarToSaveDto carToSaveDto) {
            Car car = carMapper.saveDtoToEntity(carToSaveDto);
            return carMapper.toDto(carRepository.save(car));
        }

        @Override
        public CarDto updateCar(Long id, CarToSaveDto carToSaveDto) throws NotFoundExceptionEntity {
           return carRepository.findById(id)
                    .map(car -> {
                        car.setBrand(carToSaveDto.brand());
                        car.setModel(carToSaveDto.model());
                        car.setColor(carToSaveDto.color());
                        car.setLicensePlate(carToSaveDto.licensePlate());
                        car.setDescription(carToSaveDto.description());
                        car.setYear(carToSaveDto.year());
                        car.setPrice(carToSaveDto.price());
                        car.setAvailable(carToSaveDto.available());

                        Car carGuardado = carRepository.save(car);

                        return carMapper.toDto(carGuardado);
                    }).orElseThrow(() -> new NotFoundExceptionEntity("Car not found."));
        }

        @Override
        public void deleteCar(Long id){
            Car car = carRepository.findById(id)
                    .orElseThrow(() -> new NotAbleToDeleteException("Car not found."));
            carRepository.delete(car);
        }

        @Override
        public List<CarDto> getAllCars() {
            return carRepository.findAll()
                    .stream()
                    .map(carMapper::toDto)
                    .toList();
        }

        @Override
        public List<CarDto> getAllAvailableCars() {
            return carRepository.findByAvailableTrue()
                    .stream()
                    .map(carMapper::toDto)
                    .toList();
        }

        @Override
        public List<CarDto> getAllCarsByCityName(String city) {
            return carRepository.findBySalesBranch_City_Name(city)
                    .stream()
                    .map(carMapper::toDto)
                    .toList();
        }

        @Override
        public List<CarDto> findAvailableCarsInCity(Long cityId, LocalDateTime startDate, LocalDateTime endDate) {
            return carRepository.findAvailableCarsInCity(cityId, startDate, endDate)
                    .stream()
                    .map(carMapper::toDto)
                    .toList();
        }
    }
