package com.example.bankservise.controller;

import com.example.bankservise.model.Atm;
import com.example.bankservise.service.AtmService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/atm")
public class AtmController {
@Autowired
    private  AtmService atmService;

    @GetMapping("/{id}")
    public ResponseEntity<?> getAtm(@PathVariable("id")Integer id){
        Atm result = atmService.getAtm(id);
        return ResponseEntity.ok(result);

    }

    @PostMapping
    public ResponseEntity<?> createAtm(@RequestBody Atm atm) {
        String result = atmService.createAtm(atm);
        return ResponseEntity.ok(result);

    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateAtm(@PathVariable("id")Integer id, @RequestBody Atm atm) {

        String result = atmService.updateAtm(id,atm);
        return ResponseEntity.ok(result);

    }
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteAtm(@PathVariable("id")Integer id){
        String result = atmService.deleteAtm(id);
        return ResponseEntity.ok(result);
    }

    @GetMapping("/all")
    public ResponseEntity  getAll(){
        List<Atm> result =  atmService.getAll();
        return ResponseEntity.ok(result);
    }
}
