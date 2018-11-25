package Common;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlValue;

@XmlRootElement (name = "broken")
public class Broken {

    @XmlElement public String name;
    @XmlElement public int age;
    @XmlAttribute public int id;

}

