package tdd.tddproject.hyechan.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import tdd.tddproject.hyechan.service.AddressService;

import static tdd.tddproject.global.util.Util.getMap;

@RestController
@RequiredArgsConstructor
public class AddressController {

    private final AddressService addressService;

    @GetMapping("/address/{id}")
    public ResponseEntity<?> getById(@PathVariable Long id){
        return ResponseEntity.ok()
                .body(getMap(addressService.getById(id)));
    }
}
