package tpp.tddproject.hyechan.repository.impl;

import com.querydsl.core.dml.UpdateClause;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.querydsl.jpa.impl.JPAUpdateClause;
import io.micrometer.core.instrument.util.StringUtils;
import javafx.beans.binding.BooleanExpression;
import lombok.RequiredArgsConstructor;
import org.mapstruct.factory.Mappers;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import tpp.tddproject.domain.entity.user.QUser;
import tpp.tddproject.domain.entity.user.User;
import tpp.tddproject.hyechan.dto.UserDto;
import tpp.tddproject.hyechan.mapper.UserMapper;
import tpp.tddproject.hyechan.repository.UserRepositorySupport;
import tpp.tddproject.vo.user.UserParam;

import javax.persistence.EntityManager;

import static tpp.tddproject.domain.entity.user.QUser.*;

/**
 * fileName    : UserRepositorySupportImpl
 * author      : hyechan
 * date        : 2022/04/14
 * description :
 * ====================================================
 * DATE              AUTHOR               NOTE
 * ----------------------------------------------------
 * 2022/04/14 7:34 오후  hyechan        최초 생성
 */
@Transactional(rollbackFor = {Exception.class}, propagation = Propagation.REQUIRED)
@RequiredArgsConstructor
public class UserRepositorySupportImpl implements UserRepositorySupport {

    private final EntityManager em;

    private final UserMapper mapper = Mappers.getMapper(UserMapper.class);


    @Override
    public void updateUser(Long userNo, UserParam userParam) {
        UpdateClause<JPAUpdateClause> updateBuilder = new JPAUpdateClause(em, user);

        if(StringUtils.isNotBlank(userParam.getUserName())){
            updateBuilder.set(user.userName, userParam.getUserName());
        }
        if(StringUtils.isNotBlank(userParam.getUserEmail())){
            updateBuilder.set(user.userEmail, userParam.getUserEmail());
        }
        if(StringUtils.isNotBlank(userParam.getUserPhone())){
            updateBuilder.set(user.userPhone, userParam.getUserPhone());
        }
        // update 값 하나도 없으면 예외터짐
        updateBuilder
                .where(user.userNo.eq(userNo))
                .execute();

        em.clear();
    }

}
