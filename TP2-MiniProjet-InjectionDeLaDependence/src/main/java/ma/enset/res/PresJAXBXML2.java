package ma.enset.res;

import ma.enset.config.Dir;
import ma.enset.config.Dirs;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.File;

public class PresJAXBXML2 {
    public static void main(String[] args) throws JAXBException {
        JAXBContext context=JAXBContext.newInstance(Dir.class);
        Unmarshaller unmarshaller=context.createUnmarshaller();
        Dir dirs=(Dir) unmarshaller.unmarshal(new File("config.xml"));
        System.out.println(dirs.getName());
    }
}
