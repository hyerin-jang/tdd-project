package tdd.tddproject.hyechan.util;

import com.fasterxml.jackson.core.JsonProcessingException;

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
    String toJson(P param) throws JsonProcessingException;
    P updateParam();
}
