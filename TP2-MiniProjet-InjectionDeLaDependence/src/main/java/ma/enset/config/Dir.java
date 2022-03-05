package ma.enset.config;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.xml.bind.annotation.*;

@XmlRootElement(name="dir")
@XmlAccessorType(XmlAccessType.FIELD)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Dir {
    @XmlElement(name="class")
    private ClassName className;
    @XmlAttribute
    private String name;

}
