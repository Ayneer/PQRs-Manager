package com.btg.pactual.pqrs.model;

import lombok.Data;

import java.util.Date;

@Data
public class AdminResponse {
    private String body;
    private Date createdAt;
}
