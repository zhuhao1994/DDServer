package com.globalite.dangdang.json.builder;

/**
 * Created by zhu on 2016/9/5.
 */
import com.globalite.dangdang.entity.Consignmentaddress;
import com.globalite.dangdang.json.builder.Builder;
import com.oracle.dd.tool.json.response.entity.AddressFacadeResponseParams;
import com.oracle.dd.tool.json.response.entity.AddressResponseParams;
import java.util.ArrayList;
import java.util.List;

public class AddressParamsBuilder implements Builder {
    public static final int OPTION_ADDRESS_DEFAULT = 0;
    public static final int OPTION_ADDRESS_ALL = 1;

    public AddressParamsBuilder() {
    }

    public AddressFacadeResponseParams build(Object entity, int optional) {
        List addresses = (List)entity;
        AddressFacadeResponseParams params = new AddressFacadeResponseParams();
        ArrayList addressParams = new ArrayList();
        params.setAddresses(addressParams);
        if(optional == 1) {
            params.setOp("ALL");
        }

        if(addresses != null && addresses.size() != 0) {
            int defaultIndex = 0;

            for(int defaultAddress = 0; defaultAddress < addresses.size(); ++defaultAddress) {
                AddressResponseParams singleAddress = new AddressResponseParams();
                singleAddress.setAddress(((Consignmentaddress)addresses.get(defaultAddress)).getAddress());
                singleAddress.setReceiver(((Consignmentaddress)addresses.get(defaultAddress)).getConsignmentname());
                singleAddress.setAddressid(String.valueOf(((Consignmentaddress)addresses.get(defaultAddress)).getId()));
                addressParams.add(singleAddress);
                if(((Consignmentaddress)addresses.get(defaultAddress)).getDefault_().byteValue() == 0) {
                    defaultIndex = defaultAddress;
                }
            }

            if(optional == 1) {
                return params;
            } else {
                AddressResponseParams var9 = (AddressResponseParams)addressParams.get(defaultIndex);
                ArrayList var10 = new ArrayList();
                var10.add(var9);
                params.setAddresses(var10);
                return params;
            }
        } else {
            return params;
        }
    }
}
