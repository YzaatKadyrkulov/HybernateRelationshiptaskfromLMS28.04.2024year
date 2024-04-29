package hybernate.dao;

import hybernate.entity.Address;

import java.util.List;

public interface AddressDao {
    Address saveAddress(Address address);

    Address findAddressById(Long addressId);

    List<Address> getAllAddresses();

    String updateAddress(Long addressId, Address newAddressName);

    String deleteAddressById(Long addressId);

    String cleanAddress();
}
