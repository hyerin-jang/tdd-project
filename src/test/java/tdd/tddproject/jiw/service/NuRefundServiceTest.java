package tdd.tddproject.jiw.service;

import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.mock.mockito.MockBean;

import tdd.tddproject.inwoo.service.NuRefundService;

@ExtendWith(MockitoExtension.class)
public class NuRefundServiceTest {

	@Mock NuRefundRepository nuRefundRepository;

	//TODO @MockBean, @InjectMock 차이 찾아보기

}
