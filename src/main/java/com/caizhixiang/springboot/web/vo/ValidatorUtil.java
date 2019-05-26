 package com.caizhixiang.springboot.web.vo;

 import org.hibernate.validator.HibernateValidator;

 import javax.validation.ConstraintViolation;
 import javax.validation.Validation;
 import javax.validation.Validator;
 import java.util.Set;

 /**
  * @author shaotongyao
  *
  * @date 2018/10/16
  */
 public class ValidatorUtil {

     /**
      * 使用hibernate的注解来进行验证
      *
      */
     private static Validator validator = Validation
             .byProvider(HibernateValidator.class).configure().failFast(false).buildValidatorFactory().getValidator();


     public static <T> Set<ConstraintViolation<T>>  validate(T obj) {

         Set<ConstraintViolation<T>> constraintViolations = validator.validate(obj);

         return  constraintViolations;
     }

 }
