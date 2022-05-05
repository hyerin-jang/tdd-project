package tdd.tddproject.hyechan.service;

import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.mapstruct.factory.Mappers;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import tdd.tddproject.domain.entity.user.User;
import tdd.tddproject.global.exception.ErrorCode;
import tdd.tddproject.global.exception.IdNotFoundException;
import tdd.tddproject.hyechan.dto.UserDto;
import tdd.tddproject.hyechan.mapper.UserMapper;
import tdd.tddproject.hyechan.repository.UserRepository;
import tdd.tddproject.vo.user.UserParam;

import java.util.List;

/**
 * fileName    : UserServiceImpl
 * author      : hyechan
 * date        : 2022/04/09
 * description :
 * ====================================================
 * DATE              AUTHOR               NOTE
 * ----------------------------------------------------
 * 2022/04/09 3:45 오후  hyechan        최초 생성
 */
@Service
@Transactional(rollbackFor = {Exception.class}, propagation = Propagation.REQUIRED)
@RequiredArgsConstructor
public class UserService{

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final UserMapper mapper = Mappers.getMapper(UserMapper.class);
//    private final UserParamMapper paramMapper = Mappers.getMapper(UserParamMapper.class);
    private final JPAQueryFactory queryFactory;

    // 수동 매핑
    public Long add(UserParam userParam) {
        User user = User.builder()
                .userId(userParam.getUserId())
                .userEmail(userParam.getUserEmail())
                .userName(userParam.getUserName())
                .userPhone(userParam.getUserPhone())
                .userPw(bCryptPasswordEncoder.encode(userParam.getUserPw()))
                .build();
        return userRepository.save(user).getUserNo();
    }

    // MapStruct

    @Transactional(readOnly = true)
    public List<UserDto> findAll() {
        return mapper.toDtoList(userRepository.findAll());
    }

    @Transactional(readOnly = true)
    public UserDto findById(Long userNo) {
        User user = userRepository.findById(userNo)
                .orElseThrow(() -> new IdNotFoundException(ErrorCode.USER_NOT_EXIST));
        return UserDto.builder()
                .userNo(user.getUserNo())
                .userName(user.getUserName())
                .userEmail(user.getUserEmail())
                .userId(user.getUserId())
                .userPhone(user.getUserPhone())
                .userId(user.getUserId()).build();
    }

    public UserDto update(Long userNo, UserParam userParam) {
        /* == Spring Data JPA -> Querydsl 리팩토링
        User user = userRepository.findById(userNo)
                .orElseThrow(() -> new IllegalArgumentException("해당 사용자가 존재하지 않습니다."));
        user.update(userParam.getUserName(),
                userParam.getUserEmail(),
                userParam.getUserPhone());
        return mapper.toDto(userRepository.save(user));
         */
        userRepository.updateUser(userNo, userParam);
        return mapper.toDto(userRepository.findById(userNo)
                .orElseThrow(() ->  new IdNotFoundException(ErrorCode.USER_NOT_EXIST)));
    }

    public void delete(Long userNo) {
        userRepository.deleteById(userNo);
    }


}
