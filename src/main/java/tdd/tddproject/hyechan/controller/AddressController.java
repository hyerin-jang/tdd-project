package tdd.tddproject.hyechan.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import static tdd.tddproject.global.util.Util.getMap;

@RestController
public class AddressController {

    @GetMapping("/address/{id}")
    public ResponseEntity<?> getById(@PathVariable Long id){
        return ResponseEntity.ok().build();
    }
}
