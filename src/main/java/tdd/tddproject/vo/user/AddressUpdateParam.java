package tdd.tddproject.vo.user;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import tdd.tddproject.domain.entity.user.Address;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AddressUpdateParam {
    private String addressZip;
    private String addressCity;
    private String addressStreet;
    private String addressReceiver;
    private String addressPhone;
}
