package tdd.tddproject.hyechan.repository;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import tdd.tddproject.domain.entity.user.Address;
import tdd.tddproject.global.exception.IdNotFoundException;
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

    // 테스트 주도방식이면 이 코드 짤 필요 없음
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


    // @author: hyechan, @since: 2022/05/05 12:17 오후 // ? 검증
    @Test
    void 주소록_업데이트() throws Exception{
        //given
        Address entity = createEntity(createParam());
        addressRepository.save(entity);
        //when
        Address address = addressRepository.findById(id)
                .orElseThrow(() -> new IdNotFoundException("해당 주소가 존재하지 않습니다"));
        address.update(updateParam());
        //then
        Assertions.assertEquals(address.getAddressId(), id);
        Assertions.assertEquals(address.getAddressZip(), ADDRESS_ZIP);
        Assertions.assertEquals(address.getAddressCity(), ADDRESS_CITY);
        Assertions.assertEquals(address.getAddressStreet(), UPDATE_ADDRESS_STREET);
        Assertions.assertEquals(address.getAddressReceiver(), UPDATE_ADDRESS_RECEIVER);
        Assertions.assertEquals(address.getAddressPhone(), UPDATE_ADDRESS_PHONE);
    }

}
