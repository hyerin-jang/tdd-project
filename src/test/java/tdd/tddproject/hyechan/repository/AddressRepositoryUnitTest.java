package tdd.tddproject.hyechan.repository;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import tdd.tddproject.domain.entity.user.Address;
import tdd.tddproject.hyechan.util.AddressConstructor;
import tdd.tddproject.vo.user.AddressParam;

import java.util.Optional;

@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.ANY) // 가짜 DB, Replace.NONE 실제 DB
@DataJpaTest
public class AddressRepositoryUnitTest extends AddressConstructor {

    @Autowired
    private AddressRepository addressRepository;

    Long id;

    @BeforeEach
    public void init(){
        id = 1L;
    }

    @Test
    void 주소록_단건_조회_성공() throws Exception{
        //given
        addressRepository.save(createEntity(createParam()));
        //when
        Address address = addressRepository.findById(id)
                .orElseThrow(RuntimeException::new);
        //then
        Assertions.assertEquals(address.getAddressId(), id);
        Assertions.assertEquals(address.getAddressZip(), ADDRESS_ZIP);
        Assertions.assertEquals(address.getAddressCity(), ADDRESS_CITY);
        Assertions.assertEquals(address.getAddressStreet(), ADDRESS_STREET);
        Assertions.assertEquals(address.getAddressReceiver(), ADDRESS_RECEIVER);
        Assertions.assertEquals(address.getAddressPhone(), ADDRESS_PHONE);
    }

}
