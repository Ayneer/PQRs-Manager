package com.btg.pactual.pqrs.model;

import lombok.Data;

import java.util.Date;

@Data
public class Claim {
    private String filingNumber;
    private String body;
    private Date createdAt;
}
