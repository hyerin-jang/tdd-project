package tpp.tddproject.hyechan.util;

import org.mapstruct.factory.Mappers;
import tpp.tddproject.domain.entity.user.User;
import tpp.tddproject.hyechan.mapper.UserMapper;
import tpp.tddproject.vo.user.UserParam;

import java.util.ArrayList;

/**
 * fileName    : UtilCreate
 * author      : hyechan
 * date        : 2022/04/17
 * description :
 * ====================================================
 * DATE              AUTHOR               NOTE
 * ----------------------------------------------------
    04/17           hyechan         util 클래스를 공통화 하기 위한 interface 정의
 */

public interface ConstructorCreate<E, P> {
    E createEntity(P param);
    ArrayList<E> createEntity(ArrayList<P> paramList);
    P createParam();
    ArrayList<P> createParam(int count);
}
