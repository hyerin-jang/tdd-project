package tpp.tddproject.vo.nu;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.ToString;

@Builder
@ToString
@AllArgsConstructor
public class NuOrderItemVo {

    private Long nuOrderItemNo;

    private Long productNo;

    private Long nuOrderNo;

    private int nuOrderItemCount;

    private int nuOrderItemPrice;

    private char nuOrderItemStatus;

    private char nuOrderItemRefund;

}
