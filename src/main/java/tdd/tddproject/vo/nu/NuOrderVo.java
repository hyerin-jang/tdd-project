package tdd.tddproject.vo.nu;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.ToString;

@Builder
@ToString
@AllArgsConstructor
public class NuOrderVo {

    private Long nuOrderNo;

    private String nuOrderDate;

}
