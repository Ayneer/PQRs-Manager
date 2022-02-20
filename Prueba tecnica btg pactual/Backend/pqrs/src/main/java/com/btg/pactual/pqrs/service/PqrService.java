package com.btg.pactual.pqrs.service;

import com.btg.pactual.pqrs.ecxeption.cutomException.PqrNotFoundException;
import com.btg.pactual.pqrs.ecxeption.cutomException.RequiredValueException;
import com.btg.pactual.pqrs.model.AdminResponse;
import com.btg.pactual.pqrs.model.Pqr;
import com.btg.pactual.pqrs.model.Claim;
import com.btg.pactual.pqrs.repository.PqrRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.time.LocalDate;
import java.util.Date;

@Service
@Validated
public class PqrService {

    @Autowired
    private PqrRepository pqrRepository;

    @Autowired
    private UserService userService;

    public Pqr createPqr(Pqr pqr){
        LocalDate today = LocalDate.now();
        String userId = pqr.getUserId();

        userService.getUserById(userId);

        pqr.setFilingNumber(pqr.getType()+"#"+userId+"#"+(new Date().getTime()));
        pqr.setCreatedAt(today);

        return pqrRepository.save(pqr);
    }

    public AdminResponse registerAdminResponse(String body, String filingNumber){
        return pqrRepository.registerAdminResponse(body, filingNumber);
    }

    public Claim registerClaim(String body, String filingNumber) throws RequiredValueException{
        validatePostClaim(body, filingNumber);
        return pqrRepository.registerClaim(body, filingNumber);
    }

    public Pqr getPqrByFilingNumber(String userId, String filingNumber) throws RequiredValueException {

        validateGetPqr(userId, filingNumber);

        Pqr pqr = pqrRepository.getPqrByFilingNumber(userId, filingNumber);

        if(pqr == null){
            throw new PqrNotFoundException("PQR Not Found");
        }

        return pqr;
    }

    public Pqr getClaimByFilingNumber(String userId, String filingNumber) throws RequiredValueException {

        validateGetPqr(userId, filingNumber);

        Pqr pqr = pqrRepository.getClaimByFilingNumber(userId, filingNumber);

        if(pqr == null){
            throw new PqrNotFoundException("PQR Not Found");
        }

        return pqr;
    }

    private void validateGetPqr(String userId, String filingNumber) throws RequiredValueException{
        if(userId.isEmpty()){
            throw new RequiredValueException("userId");
        }
        if(filingNumber.isEmpty()){
            throw new RequiredValueException("filingNumber");
        }

        userService.getUserById(userId);
    }

    private void validatePostClaim(String body, String filingNumber) throws RequiredValueException{

        LocalDate today = LocalDate.now();

        if(body.isEmpty()){
            throw new RequiredValueException("body");
        }
        if(filingNumber.isEmpty()){
            throw new RequiredValueException("filingNumber");
        }

        Pqr pqr = pqrRepository.getPqrOnlyByFilingNumber(filingNumber);

        if(pqr == null){
            throw new PqrNotFoundException("PQR Not Found");
        }

        LocalDate date = pqr.getCreatedAt();
        AdminResponse adminResponse = pqr.getAdminResponse();

        if(adminResponse == null && today.compareTo(date) <= 5){
            throw new PqrNotFoundException("Solo se puede registrar un reclamo sobre una solicitud realizada hace más de 5 días, o que tenga respuesta del area administrativa!");
        }

    }


}
