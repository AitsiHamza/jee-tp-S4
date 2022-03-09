package ma.enset.res;

import ma.enset.metier.IMetier;
import ma.enset.metier.MetierImpl;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class PresAnnotation {
    public static void main(String[] args) {
        ApplicationContext context= new AnnotationConfigApplicationContext("ma.enset.dao","ma.enset.ext","ma.enset.metier");
        IMetier metier=context.getBean(MetierImpl.class);
        System.out.println(metier.calcul());
    }
}

