package ma.enset.metier;

import ma.enset.dao.IDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.lang.annotation.ElementType;
import java.lang.annotation.Target;

@Component
public class MetierImpl implements IMetier{
    // couplage faible
    @Autowired
    @Qualifier("daoImplVWS")
    private IDao dao;
    public MetierImpl(){}
    public MetierImpl(IDao dao){
        this.dao=dao;
    }
    @Override
    public double calcul() {
        double tmp= dao.getData();
        double res=tmp*540/Math.cos(tmp);
        return res;
    }

    /*
    * Injecter dans la variable dans
    *  un objet d'une classe qui implemante
    * l'interface IDao
    * */
    public void setDao(IDao dao) {
        this.dao = dao;
    }
}
