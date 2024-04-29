package hybernate.service.impl;

import hybernate.dao.AddressDao;
import hybernate.dao.impl.AddressDaoImpl;
import hybernate.entity.Address;
import hybernate.service.AddressService;

import java.util.List;

public class AddressServiceImpl implements AddressService {
    AddressDao addressDao = new AddressDaoImpl();
    @Override
    public Address saveAddress(Address address) {
        return addressDao.saveAddress(address);
    }

    @Override
    public Address findAddressById(Long addressId) {
        return addressDao.findAddressById(addressId);
    }

    @Override
    public List<Address> getAllAddresses() {
        return addressDao.getAllAddresses();
    }

    @Override
    public String updateAddress(Long addressId, Address newAddressName) {
        return addressDao.updateAddress(addressId,newAddressName);
    }

    @Override
    public String deleteAddressById(Long addressId) {
        return addressDao.deleteAddressById(addressId);
    }

    @Override
    public String cleanAddress() {
        return addressDao.cleanAddress();
    }
}
