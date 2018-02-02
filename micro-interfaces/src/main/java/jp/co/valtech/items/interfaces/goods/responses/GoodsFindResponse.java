package jp.co.valtech.items.interfaces.goods.responses;

import jp.co.valtech.items.interfaces.definitions.responses.GoodsRes;
import lombok.Data;
import lombok.ToString;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@Data
@ToString
@XmlRootElement(name = "response")
public class GoodsFindResponse {

    @XmlElement(name = "goods")
    private GoodsRes goods;

}
