package tdd.tddproject.global.util;

import java.util.HashMap;
import java.util.Map;

/**
 * fileName    : Util
 * author      : hyechan
 * date        : 2022/04/13
 * description :
 * ====================================================
 * DATE              AUTHOR               NOTE
 * ----------------------------------------------------
 * 2022/04/13 4:14 오후  hyechan     최초 생성
 * 2022/04/13 4:30 오후  hyechan     Map<String, Object> 반환해주는 메서드
 */
public class Util {

    // Map<String, Object> 반환해주는 메서드
    public static <T> Map<String, Object> getMap(T value){
        Map<String, Object> result = new HashMap<>();
        result.put("result", value);
        return result;
    }
    public static <T> Map<String, Object> getMap(String key, T value){
        Map<String, Object> result = new HashMap<>();
        result.put(key, value);
        return result;
    }
}
