package com.example.bankservise.model;


import lombok.Getter;
import lombok.Setter;

import java.util.LinkedList;

@Getter
@Setter
public class Atm {
    private Integer id;
    private String number;
    private Integer pinCode;
    private String address;
    private Double amount;
    private Boolean status;



}
