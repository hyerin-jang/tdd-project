package tdd.tddproject.config.auth;


import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import tdd.tddproject.domain.entity.user.User;
import tdd.tddproject.global.exception.ErrorCode;
import tdd.tddproject.global.exception.IdNotFoundException;
import tdd.tddproject.hyechan.repository.UserRepository;

import javax.servlet.http.HttpSession;

@Service
@RequiredArgsConstructor
public class PrincipalDetailService implements UserDetailsService {

    private final UserRepository userRepository;
    private final HttpSession httpSession;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User principalUser = userRepository.findByUserId(username)
                .orElseThrow(() -> {
                    return new IdNotFoundException(ErrorCode.USER_NOT_EXIST);
                });

        httpSession.setAttribute("no", principalUser.getUserNo());
        httpSession.setAttribute("user", principalUser.getUserId());
        return new PrincipalDetail(principalUser);
    }
}
