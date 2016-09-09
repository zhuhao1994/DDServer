package com.globalite.dangdang.dao;

/**
 * Created by zhu on 2016/9/5.
 */
import com.globalite.dangdang.entity.Consignmentaddress;
import java.util.List;

public interface AddressDao {
    List<?> findAllCustomerAddress(Long var1);

    Consignmentaddress findAddressById(Long var1);

    void save(Consignmentaddress var1);
}