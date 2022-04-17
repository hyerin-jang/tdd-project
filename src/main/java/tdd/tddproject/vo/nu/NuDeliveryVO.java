package tdd.tddproject.vo.nu;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.ToString;

@Builder
@ToString
@AllArgsConstructor
public class NuDeliveryVO {

    private Long nuDeliveryNo;

    private Long nuOrderNo;

    private String nuDeliveryName;

    private String nuDeliveryPhone;

    private String nuDeliveryCity;

    private String nuDeliveryStreet;

    private String nuDeliveryDecode;

    private String nuDeliveryDate;

}
