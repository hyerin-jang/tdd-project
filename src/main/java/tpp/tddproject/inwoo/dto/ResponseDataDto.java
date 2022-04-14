package tpp.tddproject.inwoo.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
public class ResponseDataDto<T> extends ResponseStatusDto {

    private List<T> dataList;
    private T Data;

}
