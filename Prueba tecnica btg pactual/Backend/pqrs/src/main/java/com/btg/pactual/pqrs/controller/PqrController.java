package com.btg.pactual.pqrs.controller;

import com.btg.pactual.pqrs.ecxeption.cutomException.RequiredValueException;
import com.btg.pactual.pqrs.model.AdminResponse;
import com.btg.pactual.pqrs.model.Claim;
import com.btg.pactual.pqrs.model.Pqr;
import com.btg.pactual.pqrs.service.PqrService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;

@CrossOrigin("*")
@RestController
@RequestMapping("/pqr")
public class PqrController {

    @Autowired
    private PqrService pqrService;

    @PostMapping("")
    Pqr createPqr(@Valid @RequestBody Pqr pqr){
        return pqrService.createPqr(pqr);
    }

    @PostMapping("admin-response")
    AdminResponse registerAdminResponse(@RequestBody String body, @RequestParam String filingNumber){
        return pqrService.registerAdminResponse(body, filingNumber);
    }

    @PostMapping("claim")
    Claim createClaim(@RequestBody String body, @RequestParam String filingNumber) throws RequiredValueException {
        return pqrService.registerClaim(body, filingNumber);
    }

    @GetMapping("{id}")
    Pqr getPqr(@PathVariable String id, @RequestParam String filingNumber) throws RequiredValueException {
        return pqrService.getPqrByFilingNumber(id, filingNumber);
    }

    @GetMapping("claim/{id}")
    Pqr getClaim(@PathVariable String id, @RequestParam String filingNumber) throws RequiredValueException {
        return pqrService.getClaimByFilingNumber(id, filingNumber);
    }

}
