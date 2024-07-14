package co.com.ps.c24a.service;

import co.com.ps.c24a.entity.Address;
import co.com.ps.c24a.entity.Person;

import java.util.List;
import java.util.Optional;

public interface AddressService {

    Optional<Address> getAddressById(Long id);
    Address saveAddress(Address address);
    Address updateAddress(Long id, Address address);
    void deleteAddress(Long id);
    List<Address> getAddressAll();




}
