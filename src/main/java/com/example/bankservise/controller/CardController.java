package com.example.bankservise.controller;

import com.example.bankservise.model.Card;
import com.example.bankservise.model.PaymentCardToCard;
import com.example.bankservise.service.CardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/card")
public class CardController {


    @Autowired
    private  CardService cardService;

//    public CardController(){
//        this.cardService=new CardService();
//    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getCard(@PathVariable("id")Integer id){
        Card result = cardService.getCard(id);
        return ResponseEntity.ok(result);

    }

    @PostMapping
    public ResponseEntity<?> createCard(@RequestBody Card card) {
        String result = cardService.createCard(card);
        return ResponseEntity.ok(result);

    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateCard(@PathVariable("id")Integer id,@RequestBody Card card) {
        String result = cardService.updateCard(id,card);
        return ResponseEntity.ok(result);

    }
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteCard(@PathVariable("id")Integer id){
        String result = cardService.deleteCard(id);
        return ResponseEntity.ok(result);
    }

    @GetMapping("/all")
    public ResponseEntity  getAll(){
        List<Card> result =  cardService.getAll();
        return ResponseEntity.ok(result);
    }

    @PostMapping("/payment")
    public ResponseEntity<?> payment(@RequestBody PaymentCardToCard payment){
        String result = cardService.payment(payment);
        return ResponseEntity.ok(result);
    }





}
