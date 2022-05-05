package tdd.tddproject.hyechan.repository;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import tdd.tddproject.domain.entity.user.Address;
import tdd.tddproject.global.exception.ErrorCode;
import tdd.tddproject.global.exception.IdNotFoundException;
import tdd.tddproject.hyechan.util.AddressConstructor;
import tdd.tddproject.vo.user.AddressParam;
import tdd.tddproject.vo.user.AddressUpdateParam;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.Optional;

@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.ANY) // 가짜 DB, Replace.NONE 실제 DB
@DataJpaTest
public class AddressRepositoryUnitTest extends AddressConstructor {

    @Autowired
    private AddressRepository addressRepository;

    @Autowired
    private EntityManager entityManager;

    Long id;

    @BeforeEach
    public void init(){
        id = 1L;
        entityManager.createNativeQuery("ALTER SEQUENCE HIBERNATE_SEQUENCE RESTART WITH 1 ").executeUpdate();
    }

    // 테스트 주도방식이면 이 코드 짤 필요 없음
    @Test
    void 주소록_단건_조회_성공() throws Exception{
        //given
        Address address = addressRepository.save(createEntity(createParam()));
        //when
        //then
        Assertions.assertEquals(id,address.getAddressId());
        Assertions.assertEquals(ADDRESS_ZIP,address.getAddressZip());
        Assertions.assertEquals(ADDRESS_CITY,address.getAddressCity());
        Assertions.assertEquals(ADDRESS_STREET,address.getAddressStreet());
        Assertions.assertEquals(ADDRESS_RECEIVER,address.getAddressReceiver());
        Assertions.assertEquals(ADDRESS_PHONE,address.getAddressPhone());
    }


    // @author: hyechan, @since: 2022/05/05 12:17 오후 // ? 검증
    @Test
    void 주소록_업데이트() throws Exception{
        //given
        Address entity = createEntity(createParam());
        Address address = addressRepository.save(entity);
        //when

        AddressUpdateParam updateParam = new AddressUpdateParam();
        updateParam.setAddressPhone(UPDATE_ADDRESS_PHONE);
        updateParam.setAddressReceiver(UPDATE_ADDRESS_RECEIVER);
        updateParam.setAddressStreet(UPDATE_ADDRESS_STREET);
        address.update(updateParam);

        entityManager.flush();
        entityManager.clear();
        Address updatedAddress = addressRepository.findById(id).orElseThrow(() -> new IdNotFoundException(ErrorCode.ADDRESS_NOT_EXIST));
        //then
        Assertions.assertEquals(id,address.getAddressId());
        Assertions.assertEquals(ADDRESS_ZIP,address.getAddressZip());
        Assertions.assertEquals(ADDRESS_CITY,address.getAddressCity());
        Assertions.assertEquals(UPDATE_ADDRESS_STREET,address.getAddressStreet());
        Assertions.assertEquals(UPDATE_ADDRESS_RECEIVER, address.getAddressReceiver());
        Assertions.assertEquals(UPDATE_ADDRESS_PHONE,address.getAddressPhone());
    }

}
