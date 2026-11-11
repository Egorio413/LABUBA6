package lab6.reflection;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.METHOD)  //онли на методы
//  аннотация должна сохраниться в байт-коде и быть доступной во время выполнения программы
@Retention(RetentionPolicy.RUNTIME)
public @interface Repeat {
    int times() default 1;
}
