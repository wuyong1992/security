package com.wuyong.security.validator;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * created by JianGuo
 * on 2018/1/17
 * description:
 */
@Slf4j
public class MyConstraintValidator implements ConstraintValidator<MyConstraint, Object> {

//    @Autowired

    @Override
    public void initialize(MyConstraint constraintAnnotation) {
        log.info("自定义的校验器初始化");
    }

    @Override
    public boolean isValid(Object o, ConstraintValidatorContext constraintValidatorContext) {
        log.info("校验的值是：{}", o);
        return false;
    }
}
