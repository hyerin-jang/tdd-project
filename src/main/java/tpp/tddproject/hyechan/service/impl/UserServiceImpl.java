package tpp.tddproject.hyechan.service.impl;

import lombok.RequiredArgsConstructor;
import org.mapstruct.factory.Mappers;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import tpp.tddproject.domain.entity.user.User;
import tpp.tddproject.hyechan.dto.UserDto;
import tpp.tddproject.hyechan.mapper.UserMapper;
import tpp.tddproject.hyechan.repository.UserRepository;
import tpp.tddproject.hyechan.service.UserService;
import tpp.tddproject.vo.user.UserVO;

import java.util.List;
import java.util.stream.Collectors;

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
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final UserMapper mapper = Mappers.getMapper(UserMapper.class);

    // 수동 매핑
    @Override
    public Long add(UserVO userVO) {

        User user = User.builder()
                .userId(userVO.getUserId())
                .userEmail(userVO.getUserEmail())
                .userName(userVO.getUserName())
                .userPhone(userVO.getUserPhone())
                .userPw(bCryptPasswordEncoder.encode(userVO.getUserPw()))
                .build();
        return userRepository.save(user).getUserNo();
    }

    // MapStruct
    @Override
    @Transactional(readOnly = true)
    public List<UserDto> findAll() {
        return mapper.toDtoList(userRepository.findAll());
    }

    @Override
    @Transactional(readOnly = true)
    public UserDto findById(Long userNo) {
        User user = userRepository.findById(userNo)
                .orElseThrow(() -> new IllegalArgumentException("해당 사용자가 존재하지 않습니다."));
        return UserDto.builder()
                .userNo(user.getUserNo())
                .userName(user.getUserName())
                .userEmail(user.getUserEmail())
                .userId(user.getUserId())
                .userPhone(user.getUserPhone())
                .userId(user.getUserId()).build();
    }

    @Override
    public void update(Long userNo, UserVO userVO) {
        User user = userRepository.findById(userNo)
                .orElseThrow(() -> new IllegalArgumentException("해당 사용자가 존재하지 않습니다."));
        user.update(
                userVO.getUserName(),
                userVO.getUserEmail(),
                userVO.getUserPhone()
        );
    }

    @Override
    public void delete(Long userNo) {
        userRepository.deleteById(userNo);
    }
}
