package tdd.tddproject.hyechan.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.platform.commons.util.StringUtils;
import org.mapstruct.factory.Mappers;
import org.springframework.test.util.ReflectionTestUtils;
import tdd.tddproject.domain.entity.user.Address;
import tdd.tddproject.hyechan.mapper.AddressMapper;
import tdd.tddproject.hyechan.mapper.UserMapper;
import tdd.tddproject.vo.user.AddressParam;
import tdd.tddproject.vo.user.AddressUpdateParam;
import tdd.tddproject.vo.user.UserParam;

import java.util.ArrayList;

public class AddressConstructor implements ConstructorCreate<Address, AddressParam>{

    AddressMapper mapper = Mappers.getMapper(AddressMapper.class);

    protected String ADDRESS_ZIP = "000000";
    protected String ADDRESS_CITY = "경상북도";
    protected String ADDRESS_STREET = "진평동 에이파크 501호";
    protected String ADDRESS_RECEIVER = "test";
    protected String ADDRESS_PHONE = "010-0000-0000";

    protected String UPDATE_ADDRESS_PHONE = "999-9999-9999";
    protected String UPDATE_ADDRESS_RECEIVER = "update";
    protected String UPDATE_ADDRESS_STREET = "업데이트 501호";

    @Override
    public Address createEntity(AddressParam param) {
        Address address = mapper.toEntity(param);
        ReflectionTestUtils.setField(address, "addressId", 1L);
        return address;
    }

    @Override
    public ArrayList<Address> createEntity(ArrayList<AddressParam> paramList) {
        ArrayList<Address> arrayList = new ArrayList<>();
        for (AddressParam param : paramList) {
            arrayList.add(mapper.toEntity(param));
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
        ArrayList<AddressParam> arrayList = new ArrayList<>();
        for(int i = 0; i < count; i ++){
            AddressParam param = new AddressParam();
            param.setAddressCity(ADDRESS_CITY+i);
            param.setAddressReceiver(ADDRESS_RECEIVER+i);
            param.setAddressPhone(ADDRESS_PHONE.substring(0, ADDRESS_PHONE.length() -1)+i);
            param.setAddressStreet(ADDRESS_STREET+i);
            param.setAddressZip(ADDRESS_ZIP.substring(0,ADDRESS_ZIP.length() -1)+i);
            arrayList.add(param);
        }
        return arrayList;
    }


    @Override
    public String toJson(AddressParam param) throws JsonProcessingException {
        return new ObjectMapper().writeValueAsString(param);
    }

}
