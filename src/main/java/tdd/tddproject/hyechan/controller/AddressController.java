package tdd.tddproject.hyechan.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import tdd.tddproject.hyechan.service.AddressService;
import tdd.tddproject.vo.user.AddressParam;

import javax.validation.Valid;

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
    public ResponseEntity<?> add(@Valid @RequestBody AddressParam addressParam, BindingResult bindingResult) throws BindException {
        if(bindingResult.hasErrors()){
            throw new BindException(bindingResult);
        }
        return ResponseEntity.ok()
                .body(getMap(addressService.add(addressParam)));
    }
}
