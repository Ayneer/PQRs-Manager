package com.btg.pactual.pqrs.validation;

import com.btg.pactual.pqrs.model.PqrType;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;
import java.lang.annotation.ElementType;

@Target({ElementType.METHOD, ElementType.FIELD, ElementType.ANNOTATION_TYPE, ElementType.CONSTRUCTOR, ElementType.PARAMETER, ElementType.TYPE_USE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Constraint(validatedBy = EnumPqrTypeValidator.class)
public @interface EnumPqrType  {
    PqrType[] pqrOf();
    String message() default "must be any of {pqrOf}";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
