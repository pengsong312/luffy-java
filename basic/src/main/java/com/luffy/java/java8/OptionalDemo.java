package com.luffy.java.java8;

import com.google.common.base.Predicate;
import com.google.common.base.Predicates;

import javax.annotation.Nullable;
import java.util.Optional;

/**
 * @author Luffy
 * @date 2018/7/10
 * @description todo
 */
public class OptionalDemo {

    public static void main(String[] args) {
        Optional<Object> optional = Optional.ofNullable(null);
        System.out.println(optional.isPresent());
        if(optional.isPresent()){
            System.out.println(optional.get());
        }else{
            System.out.println(optional.orElse("is null"));
        }

        Optional<Object> optional1 = Optional.ofNullable("hello world !");
        System.out.println(optional1.isPresent());
        if(optional1.isPresent()){
            System.out.println(optional1.get());
        }else{
            System.out.println(optional1.orElse("is null"));
        }

        Predicate<Object> predicate1 = new Predicate<Object>() {
            @Override
            public boolean apply(@Nullable Object o) {
                return false;
            }
        };

        Predicate<Object> predicate2 = new Predicate<Object>() {
            @Override
            public boolean apply(@Nullable Object o) {
                return true;
            }
        };

        System.out.println(Predicates.and(predicate1,predicate2).test(true));
        System.out.println(Predicates.or(predicate1,predicate2).test(true));
    }
}
