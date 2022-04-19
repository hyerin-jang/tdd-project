package tdd.tddproject.inwoo.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
public class ResponseDataDto<T> extends tdd.tddproject.inwoo.dto.ResponseStatusDto {

    private List<T> dataList;
    private T Data;

}
