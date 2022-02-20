package com.btg.pactual.pqrs.model;

import com.btg.pactual.pqrs.validation.EnumPqrType;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.NotBlank;
import java.time.LocalDate;

@Data
@Document
public class Pqr {

    @Id
    private String id;

    @NotBlank(message = "userId is required")
    private String userId;

    @EnumPqrType(pqrOf = {PqrType.REQUEST, PqrType.COMPLAIN})
    private PqrType type;

    private String filingNumber;

    @NotBlank(message = "body is required")
    private String body;

    private LocalDate createdAt;
    private Claim claim;
    private AdminResponse adminResponse;

}
