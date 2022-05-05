package tdd.tddproject.hyechan.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tdd.tddproject.hyechan.service.AddressService;
import tdd.tddproject.vo.user.AddressParam;

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

    @GetMapping("/address")
    public ResponseEntity<?> getList(){
        return ResponseEntity.ok()
                .body(getMap(addressService.getList()));
    }

    @PostMapping("/address")
    public ResponseEntity<?> add(@RequestBody AddressParam addressParam){
        return ResponseEntity.ok()
                .body(getMap(addressService.add(addressParam)));
    }
}
