package com.flower.mine.service;

import com.flower.mine.bean.Address;
import com.flower.mine.param.AddressParam;
import com.flower.mine.repository.AddressRepository;
import com.flower.mine.ret.DataResult;
import com.flower.mine.session.SessionUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AddressService {

    @Autowired
    private AddressRepository addressRepository;

    public void addOrUpdate(AddressParam param) {
        String mobile = SessionUtil.currentUserId();
        Address address;
        if ( addressRepository.existsById(mobile) ) {
            address = addressRepository.findById(mobile).get();
        } else {
            address = new Address();
            address.setMobile(mobile);
        }
        address.setAddress(param.getAddress());
        addressRepository.save(address);
    }

    /**
     * 删除地址，物理删除
     */
    public void delete() {
        String mobile = SessionUtil.currentUserId();
        addressRepository.deleteById(mobile);
    }

    /**
     * 查询自己的地址
     * @return
     */
    public DataResult<Address> address() {
        DataResult<Address> dataResult = new DataResult<>();
        Optional<Address> addressOptional = addressRepository.findById(SessionUtil.currentUserId());
        if (addressOptional.isPresent()) {
            dataResult.setData(addressOptional.get());
        }
        return dataResult;
    }
}
