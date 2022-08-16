package com.example.bankservise.service;

import com.example.bankservise.exception.BadRequest;
import com.example.bankservise.model.Card;
import com.example.bankservise.model.PaymentCardToCard;
import org.springframework.stereotype.Component;


import java.time.LocalDate;
import java.util.LinkedList;
import java.util.List;
@Component
public class CardService {
    private final List<Card> cardList;
    private static int id = 1;

    public CardService() {
        this.cardList = new LinkedList<>();
    }

    public Card getCard(Integer id) {

        return findCard(id);


    }

    public Card findCard(Integer id) {
        for (Card card : cardList) {
            if (card.getId().equals(id)) return card;
        }
        throw new BadRequest("Card not found");
    }


    public String createCard(Card card) {
        checkCard(card);
        card.setId(id++);
        card.setDate(LocalDate.now().plusYears(5));
        card.setAmount(0.0);
        cardList.add(card);
        return "Card created";
    }

    public void checkCard(Card card){
        if (card.getNumber().length() != 16) {
            throw new BadRequest("Card number error");
        }
        if (String.valueOf(card.getPinCode()).length() != 4) {
            throw new BadRequest("Card pincode error");
        }
        if (card.getName().length() > 25) {
            card.setName(card.getName().substring(0, 25));
        }
    }

    public String updateCard(Integer id, Card card) {
        checkCard(card);
        Card updatedCard = findCard(id);
        updatedCard.setName(card.getName());
        updatedCard.setPinCode(card.getPinCode());
        updatedCard.setNumber(card.getNumber());
        return "Card updated";
    }

    public String deleteCard(Integer id) {
        for (Card card : cardList) {
            if (card.getId().equals(id)) {
                cardList.remove(card);
                return "Card deleted";

            }
        }
        throw new BadRequest("Card not found");

    }

    public List<Card> getAll() {
        if (cardList.isEmpty()) {
            throw new BadRequest("Cards not found");
        }
        return cardList;
    }
    public String payment(PaymentCardToCard payment) {
        Card fromCard = findCard(payment.getFromId());
        if (!fromCard.getStatus()){
            throw new BadRequest("Card is not active");
        }
        if (!fromCard.getPinCode().equals(payment.getFromPassword())){
            throw new BadRequest("Password error");
        }
        Card toCard = findCard(payment.getToId());
        if (!toCard.getStatus()){
            throw new BadRequest("Card is not active");
        }
        if (fromCard.getAmount() < payment.getAmount()){
            throw new BadRequest("First card amount invalid");
        }
        fromCard.setAmount(fromCard.getAmount() - payment.getAmount());
        toCard.setAmount(toCard.getAmount() + payment.getAmount());
        return "Successful payment";
    }
}

