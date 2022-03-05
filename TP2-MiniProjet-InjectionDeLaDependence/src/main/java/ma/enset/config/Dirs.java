package ma.enset.config;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.xml.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class Dirs {
    @XmlAttribute
    private String name;
    @XmlElement(name="dir")
    private List<Dir> list=new ArrayList<>();

   /* public void addDir(Dir dir){
        list.add(dir);
    }*/

}
