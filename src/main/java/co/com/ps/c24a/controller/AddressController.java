package co.com.ps.c24a.controller;

import co.com.ps.c24a.entity.Address;
import co.com.ps.c24a.entity.Person;
import co.com.ps.c24a.service.AddressService;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.amqp.RabbitConnectionDetails;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController //Esta clase va a representar servicios Ress, va a ser un controlador Ress
@RequestMapping("/address") // Este es el Phat por el que vamos a buscar
@RequiredArgsConstructor//Inyectamos el servicio que es el que se encarga de la lógina

public class AddressController {

    private final AddressService addressService;

    @GetMapping("/{id}")
    public Address getAddressById(@PathVariable Long id){
        return addressService.getAddressById(id).orElseThrow(()-> new RuntimeException("No encontró el id"));
    }

    @PostMapping
    public Address saveAddress(@RequestBody Address address){
        return addressService.saveAddress(address);
    }

    @DeleteMapping("/{id}")
    public void deleteAddress(@PathVariable Long id){
        addressService.deleteAddress(id);
    }

    @PutMapping("/{id}")

    public Address updateAddress(@PathVariable Long id, @RequestBody Address address){
        return addressService.updateAddress(id,address);

    }

    @GetMapping()
    public List<Address> getAddress(){
        return addressService.getAddressAll();
    }
}