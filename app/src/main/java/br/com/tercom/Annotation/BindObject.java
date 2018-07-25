package br.com.tercom.Annotation;



import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface BindObject {



    String value();
    TYPE type() default TYPE.OBJECT;

    public enum TYPE
    {
        OBJECT, LIST
    }

}
