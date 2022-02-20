package com.btg.pactual.pqrs.validation;

import com.btg.pactual.pqrs.model.PqrType;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Arrays;

public class EnumPqrTypeValidator implements ConstraintValidator<EnumPqrType, PqrType> {

    private PqrType[] subset;

    @Override
    public void initialize(EnumPqrType constraint) {
        this.subset = constraint.pqrOf();
    }

    @Override
    public boolean isValid(PqrType value, ConstraintValidatorContext context) {
        return value == null || Arrays.asList(subset).contains(value);
    }
}
