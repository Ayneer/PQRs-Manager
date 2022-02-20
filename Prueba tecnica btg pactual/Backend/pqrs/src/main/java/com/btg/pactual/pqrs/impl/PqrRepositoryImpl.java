package com.btg.pactual.pqrs.impl;

import com.btg.pactual.pqrs.customRepository.PqrRepositoryCustom;
import com.btg.pactual.pqrs.model.AdminResponse;
import com.btg.pactual.pqrs.model.Claim;
import com.btg.pactual.pqrs.model.Pqr;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Repository;

import java.util.Date;

@Repository
public class PqrRepositoryImpl implements PqrRepositoryCustom {

    @Autowired
    private MongoTemplate mongoTemplate;

    @Override
    public AdminResponse registerAdminResponse(String body, String filingNumber) {
        Date today = new Date();
        Query query = new Query();
        Update update = new Update();
        AdminResponse adminResponse = new AdminResponse();

        query.addCriteria(Criteria.where("filingNumber").is(filingNumber));

        adminResponse.setBody(body);
        adminResponse.setCreatedAt(today);

        update.set("adminResponse", adminResponse);

        mongoTemplate.updateFirst(
                query,
                update,
                Pqr.class
        );

        return adminResponse;
    }

    @Override
    public Claim registerClaim(String body, String filingNumber) {

        Date today = new Date();
        Query query = new Query();
        Update update = new Update();
        Claim claim = new Claim();

        query.addCriteria(Criteria.where("filingNumber").is(filingNumber));

        claim.setFilingNumber("CLAIM"+"#"+today.getTime());
        claim.setBody(body);
        claim.setCreatedAt(today);

        update.set("claim", claim);

        mongoTemplate.updateFirst(
                query,
                update,
                Pqr.class
        );

        return claim;
    }

    @Override
    public Pqr getPqrByFilingNumber(String userId, String filingNumber) {
        Query query = new Query();
        query.addCriteria(Criteria.where("userId").is(userId));
        query.addCriteria(Criteria.where("filingNumber").is(filingNumber));

        return  mongoTemplate.findOne(query, Pqr.class);
    }

    @Override
    public Pqr getClaimByFilingNumber(String userId, String filingNumber) {
        Query query = new Query();
        query.addCriteria(Criteria.where("userId").is(userId));
        query.addCriteria(Criteria.where("claim.filingNumber").is(filingNumber));

        return mongoTemplate.findOne(query, Pqr.class);
    }

    @Override
    public Pqr getPqrOnlyByFilingNumber(String filingNumber) {
        Query query = new Query();
        query.addCriteria(Criteria.where("filingNumber").is(filingNumber));

        return  mongoTemplate.findOne(query, Pqr.class);
    }
}
