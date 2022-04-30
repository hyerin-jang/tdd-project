package tdd.tddproject.hyechan.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.test.util.ReflectionTestUtils;
import tdd.tddproject.domain.entity.user.Address;
import tdd.tddproject.vo.user.AddressParam;

import java.util.ArrayList;

public class AddressConstructor implements ConstructorCreate<Address, AddressParam>{

    protected String ADDRESS_ZIP = "000000";
    protected String ADDRESS_CITY = "경상북도";
    protected String ADDRESS_STREET = "진평동 에이파크 501호";
    protected String ADDRESS_RECEIVER = "test";
    protected String ADDRESS_PHONE = "010-0000-0000";

    @Override
    public Address createEntity(AddressParam param) {
        Address address = param.toEntity();
        ReflectionTestUtils.setField(address, "addressId", 1L);
        return address;
    }

    @Override
    public ArrayList<Address> createEntity(ArrayList<AddressParam> paramList) {
        ArrayList<Address> arrayList = new ArrayList<>();
        for (AddressParam param : paramList) {
            arrayList.add(param.toEntity());
        }
        return arrayList;
    }

    @Override
    public ArrayList<Address> createEntity(ArrayList<AddressParam> paramList, String id) {
        return null;
    }

    @Override
    public AddressParam createParam() {
        AddressParam param = new AddressParam();
        param.setAddressCity(ADDRESS_CITY);
        param.setAddressReceiver(ADDRESS_RECEIVER);
        param.setAddressPhone(ADDRESS_PHONE);
        param.setAddressStreet(ADDRESS_STREET);
        param.setAddressZip(ADDRESS_ZIP);
        return param;
    }

    @Override
    public ArrayList<AddressParam> createParam(int count) {
        return null;
    }

    @Override
    public AddressParam updateParam() {
        return null;
    }
    @Override
    public String toJson(AddressParam param) throws JsonProcessingException {
        return new ObjectMapper().writeValueAsString(param);
    }

}
