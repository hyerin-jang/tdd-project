package tdd.tddproject.rin.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tdd.tddproject.rin.dto.ItemDto;
import tdd.tddproject.rin.service.ItemService;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/item")
@RequiredArgsConstructor
public class ItemController {

    private final ItemService itemService;

    @GetMapping
    public ResponseEntity<List<ItemDto>> findAllItem() {

        try {
            List<ItemDto> response = itemService.findAllItem();

            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            log.error("error: " + e);

            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{itemName}")
    public ResponseEntity<ItemDto> findItemByItemName(@PathVariable String itemName) {

        try {
            ItemDto response = itemService.findItemByItemName(itemName);

            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            log.error("error: " + e);

            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping
    public ResponseEntity<Void> addItem(@RequestBody ItemDto itemDto) {
        try {
            itemService.addItem(itemDto);

            return new ResponseEntity<>(HttpStatus.OK);

        } catch (Exception e) {
            log.error("error: " + e);

            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/{itemNo}")
    public ResponseEntity<Void> updateItem(@PathVariable Long itemNo, @RequestBody ItemDto itemDto) {
        try {
            itemService.updateItem(itemDto);

            return new ResponseEntity<>(HttpStatus.OK);

        } catch (Exception e) {
            log.error("error: " + e);

            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/{itemNo}")
    public ResponseEntity<Void> deleteItem(@PathVariable Long itemNo) {
        try {
            itemService.deleteItem(itemNo);

            return new ResponseEntity<>(HttpStatus.OK);

        } catch (Exception e) {
            log.error("error: " + e);

            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
