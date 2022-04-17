package tdd.tddproject.vo.nu;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.ToString;

@Builder
@ToString
@AllArgsConstructor
public class NuRefundVo {

    private Long nuRefundNo;

    private Long nuOrderNo;

    private char nuRefundYn;

}
