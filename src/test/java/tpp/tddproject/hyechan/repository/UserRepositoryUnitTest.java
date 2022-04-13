package tpp.tddproject.hyechan.repository;


// 단위 테스트 ( DB 관련 bean IoC에 등록되면 됨)

import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.ANY) // 가짜 DB, Replace.NONE 실제 DB
@DataJpaTest // jpa 관련 빈,SpringExtension 포함
public class UserRepositoryUnitTest {

    @Autowired
    private UserRepository userRepository;
}
