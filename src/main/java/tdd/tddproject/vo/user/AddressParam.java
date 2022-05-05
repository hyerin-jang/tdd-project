package tdd.tddproject.vo.user;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import tdd.tddproject.domain.entity.user.Address;
import tdd.tddproject.domain.entity.user.User;

import javax.validation.constraints.NotNull;

@Slf4j
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AddressParam {

    @NotNull
    private String addressZip;

    @NotNull
    private String addressCity;

    @NotNull
    private String addressStreet;

    @NotNull
    private String addressReceiver;

    @NotNull
    private String addressPhone;

    public Address toEntity(){
        return Address.builder()
                .addressZip(addressZip)
                .addressCity(addressCity)
                .addressStreet(addressStreet)
                .addressReceiver(addressReceiver)
                .addressPhone(addressPhone).build();
    }

}
