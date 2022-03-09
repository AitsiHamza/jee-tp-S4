package ma.enset.res;

import ma.enset.config.Dirs;
import ma.enset.dao.IDao;
import ma.enset.metier.IMetier;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.File;
import java.lang.annotation.ElementType;
import java.lang.annotation.Target;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class PresJAXBXML {
    public static void main(String[] args) throws JAXBException, ClassNotFoundException, InstantiationException, IllegalAccessException, InvocationTargetException, NoSuchMethodException {
        JAXBContext context=JAXBContext.newInstance(Dirs.class);
        Unmarshaller unmarshaller=context.createUnmarshaller();
        Dirs dirs=(Dirs)unmarshaller.unmarshal(new File("config.xml"));

        String daoImplClassName=dirs.getName()+"."+dirs.getList().get(0).getName()+"."+dirs.getList().get(0).getClassName().getName();
        //System.out.println(daoImplClassName);
        Class cDao = Class.forName(daoImplClassName);
        IDao dao=(IDao) cDao.newInstance();

        String metierImplClassName=dirs.getName()+"."+dirs.getList().get(1).getName()+"."+dirs.getList().get(1).getClassName().getName();
        //System.out.println(metierImplClassName);
        Class cMetier = Class.forName(metierImplClassName);
        IMetier metier=(IMetier) cMetier.newInstance();

        Method method=cMetier.getMethod("setDao",IDao.class);
        method.invoke(metier,dao);

        System.out.println("Resultat => "+metier.calcul());

    }
}
