
import co.com.ps.c24a.entity.Address;
import co.com.ps.c24a.entity.Person;
import co.com.ps.c24a.repository.AddressRepository;
import co.com.ps.c24a.repository.PersonRepository;
import co.com.ps.c24a.service.AddressServiceImp;
import co.com.ps.c24a.service.PersonServiceImp;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class AddressServiceTest {

    @Mock
    private AddressRepository addressRepository;

    @InjectMocks
    private AddressServiceImp addressService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

   @Test
    void testGetAllAddress() {
        Iterable<Address> address = List.of(new Address(), new Address());
        when(addressRepository.findAll()).thenReturn(address);

        Iterable<Address> result = addressService.getAddressAll();
        assertNotNull(result);
        verify(addressRepository, times(1)).findAll();
    }

    @Test
    void testGetAddressById() {

        Address address = new Address();
        address.setId(1L);
        address.setCity("Florencia");


        when(addressRepository.findById(1L)).thenReturn(Optional.of(address));

        Optional<Address> result = addressService.getAddressById(1L);
        assertTrue(result.isPresent());
        assertEquals(1L, result.get().getId());


        verify(addressRepository, times(1)).findById(1L);
    }

    @Test
    void testGetAddressById_NotFound() {
        when(addressRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(RuntimeException.class, () -> addressService.getAddressById(1L));
        verify(addressRepository, times(1)).findById(1L);
    }

    @Test
    void testSaveAddress() {
        Address address = new Address();
        address.setId(1L);
        address.setCity("New York");


        when(addressRepository.save(any(Address.class))).thenReturn(address);

        Address result = addressService.saveAddress(address);
        assertNotNull(result);
        assertEquals("New York", result.getCity());
        assertEquals(1L, result.getId());

        verify(addressRepository, times(1)).save(address);
    }

    @Test
    void testDeleteAddress() {
        when(addressRepository.existsById(1L)).thenReturn(true);
        doNothing().when(addressRepository).deleteById(1L);

        addressService.deleteAddress(1L);
        verify(addressRepository, times(1)).existsById(1L);
        verify(addressRepository, times(1)).deleteById(1L);
    }

    @Test
    void testDeleteAddress_NotFound() {
        when(addressRepository.existsById(1L)).thenReturn(false);

        assertThrows(RuntimeException.class, () -> addressService.deleteAddress(1L));
        verify(addressRepository, times(1)).existsById(1L);
        verify(addressRepository, never()).deleteById(1L);
    }
}