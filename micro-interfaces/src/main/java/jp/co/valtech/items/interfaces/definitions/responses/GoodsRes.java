package jp.co.valtech.items.interfaces.definitions.responses;

import lombok.Data;
import lombok.ToString;

import javax.xml.bind.annotation.XmlElement;

@Data
@ToString
public class GoodsRes {

    @XmlElement(name = "id")
    private long id;

    @XmlElement(name = "code")
    private String code;

    @XmlElement(name = "name")
    private String name;

    @XmlElement(name = "price")
    private int price;

    @XmlElement(name = "note")
    private String note;

}
