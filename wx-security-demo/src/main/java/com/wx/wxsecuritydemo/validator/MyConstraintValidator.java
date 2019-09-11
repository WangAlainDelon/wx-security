/**
 *
 */
package com.wx.wxsecuritydemo.validator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import com.wx.wxsecuritydemo.service.HelloService;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author zhailiang
 */
//ConstraintValidator<MyConstraint, Object>：
// MyConstraint注解可以放在任何一个Object类型的字段上面，并且不用打@Compoment注解就能使用自动注入
public class MyConstraintValidator implements ConstraintValidator<MyConstraint, Object> {

    @Autowired
    private HelloService helloService;

    @Override
    public void initialize(MyConstraint constraintAnnotation) {
        System.out.println("my validator init");
    }

    @Override
    public boolean isValid(Object value, ConstraintValidatorContext context) {
        helloService.greeting("tom");
        System.out.println(value);
        return false;
    }

}
