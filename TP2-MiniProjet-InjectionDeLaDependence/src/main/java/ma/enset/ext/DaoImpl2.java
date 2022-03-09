package ma.enset.ext;

import ma.enset.dao.IDao;
import org.springframework.stereotype.Component;


@Component("daoImpl2")
public class DaoImpl2 implements IDao {
    @Override
    public double getData() {
        System.out.println("DaoImpl2");
        double temp=6000;
        return temp;
    }
}
