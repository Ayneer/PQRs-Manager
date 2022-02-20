package com.btg.pactual.pqrs.customRepository;

import com.btg.pactual.pqrs.model.AdminResponse;
import com.btg.pactual.pqrs.model.Claim;
import com.btg.pactual.pqrs.model.Pqr;

public interface PqrRepositoryCustom {

    AdminResponse registerAdminResponse(String body, String filingNumber);
    Claim registerClaim(String body, String filingNumber);
    Pqr getPqrByFilingNumber(String userId, String filingNumber);
    Pqr getPqrOnlyByFilingNumber(String filingNumber);
    Pqr getClaimByFilingNumber(String userId, String filingNumber);

}
