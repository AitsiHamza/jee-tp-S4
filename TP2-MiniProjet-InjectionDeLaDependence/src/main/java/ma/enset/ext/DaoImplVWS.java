package ma.enset.ext;

import ma.enset.dao.IDao;
import org.springframework.stereotype.Component;

@Component("daoImplVWS")
public class DaoImplVWS implements IDao {
    @Override
    public double getData() {
        System.out.println("DaoImplVWS");
        return 0;
    }
}
