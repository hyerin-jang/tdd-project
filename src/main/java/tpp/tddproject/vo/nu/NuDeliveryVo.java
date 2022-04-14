package tpp.tddproject.vo.nu;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.ToString;

@Builder
@ToString
@AllArgsConstructor
public class NuDeliveryVo {

    private Long nuDeliveryNo;

    private Long nuOrderNo;

    private String nuDeliveryName;

    private String nuDeliveryPhone;

    private String nuDeliveryCity;

    private String nuDeliveryStreet;

    private String nuDeliveryDecode;

    private String nuDeliveryDate;

}
