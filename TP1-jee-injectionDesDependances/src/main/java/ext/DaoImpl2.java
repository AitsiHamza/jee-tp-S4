package ext;

import dao.IDao;
import org.springframework.stereotype.Component;

@Component
public class DaoImpl2 implements IDao {
    @Override
    public double getData() {
        System.out.println("Versions capteurs");
        double temp=6000;
        return temp;
    }
}
