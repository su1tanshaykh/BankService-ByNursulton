package com.example.bankservise.service;

import com.example.bankservise.exception.BadRequest;
import com.example.bankservise.model.Atm;
import com.example.bankservise.model.Card;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;

@Component
public class AtmService {
    private final List<Atm> atmList;
    private static int id = 1;

    public AtmService() {
        this.atmList = new LinkedList<>();
    }

    public Atm getAtm(Integer id) {
        return findAtm(id);
    }

    public Atm findAtm(Integer id) {
        for (Atm atm : atmList) {
            if (atm.getId().equals(id)) return atm;
        }
        throw new BadRequest("Atm not found");
    }
    public String createAtm(Atm atm) {
        checkAtm(atm);
        atm.setId(id++);
        atm.setAmount(0.0);
        atm.setStatus(false);
        atmList.add(atm);
        return "Atm created";
    }

    public void checkAtm(Atm atm){
        if (atm.getNumber().length() != 25) {
            throw new BadRequest("Atm number error");
        }
        if (String.valueOf(atm.getPinCode()).length() != 6) {
            throw new BadRequest("Atm PinCode error");
        }

    }

    public String updateAtm(Integer id, Atm atm) {
        checkAtm(atm);
        Atm updatedAtm = findAtm(id);
        updatedAtm.setPinCode(atm.getPinCode());
        updatedAtm.setNumber(atm.getNumber());
        updatedAtm.setAddress(atm.getAddress());
        return "Atm updated";
    }

    public String deleteAtm(Integer id) {
        for (Atm atm : atmList) {
            if (atm.getId().equals(id)) {
                atmList.remove(atm);
                return "Card deleted";

            }
        }
        throw new BadRequest("Card not found");
    }

    public List<Atm> getAll() {
        if (atmList.isEmpty()) {
            throw new BadRequest("Cards not found");
        }
        return atmList;
    }
//    public String startAtm(Integer id){
//        Atm atm = findAtm(id);
//        atm.setStatus(true);
//        return "Atm is activated";
//    }

//    public String finishAtm(Integer id){
//        Atm atm = findAtm(id);
//        Double amount = atm.getAmount();
//        return "";
//    }

}
