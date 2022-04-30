package tdd.tddproject.hyechan.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class AddressDto {
    private Long addressId;
    private String addressZip;
    private String addressCity;
    private String addressStreet;
    private String addressReceiver;
    private String addressPhone;
}
