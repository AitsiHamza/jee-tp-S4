package ext;

import dao.IDao;
import org.springframework.stereotype.Component;

@Component
public class DaoImplVWS implements IDao {
    @Override
    public double getData() {
        System.out.println("DaoImplVWS");
        return Math.random()*10;
    }
}
