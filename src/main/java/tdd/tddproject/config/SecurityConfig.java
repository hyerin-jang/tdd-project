package tdd.tddproject.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import tdd.tddproject.config.auth.PrincipalDetailService;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Bean
    public BCryptPasswordEncoder encodePwd(){
        return new BCryptPasswordEncoder();
    }

    private final PrincipalDetailService principalDetailService;


    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().ignoringAntMatchers("/h2-console/**").disable();
        http.cors().disable();
        http.authorizeRequests()
                //주소록 관련 요청은 인증 필요
                .antMatchers("/css/**","/images/**","/js/**").permitAll()
                .antMatchers("/address/**").authenticated()
                //user 관련 요청 인증
                .antMatchers(HttpMethod.POST,"/user").permitAll()
                .antMatchers(HttpMethod.GET,"/user/{userNo}").authenticated()
                .antMatchers(HttpMethod.DELETE,"/user/{userNo}").authenticated()
                .antMatchers(HttpMethod.PUT,"/user/{userNo}").authenticated()
                //user list 조회는 관리자가 사용할 수 있도록 한다.
                .antMatchers(HttpMethod.GET,"/user").access("hasRole('ROLE_ADMIN')")


                .antMatchers("/h2-console/**").permitAll()
                .anyRequest().permitAll()
                .and()
                .formLogin()
//                .loginPage("/home/loginForm")
                .loginProcessingUrl("/login");
//                .usernameParameter("userId").passwordParameter("userPw")
//                .defaultSuccessUrl("/");
//                    .defaultSuccessUrl("/home/main")  //로그인 성공시 기본 리다이렉트 주소
//                .and()
//                .logout()
//                .logoutUrl("/logout")
//                .logoutSuccessUrl("/") //로그아웃 성공시 리다이렉트 주소
//                    .logoutSuccessHandler(customLogoutSuccessHandler)
//                .invalidateHttpSession(true);//세션 날리기
        // frame, iframe, embed, object 태그 페이지 랜더링 허용 여부
        // -> embedded h2 사용하기 위해 disable
        http.headers()
                .frameOptions().disable();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(principalDetailService).passwordEncoder(encodePwd());
    }
}
