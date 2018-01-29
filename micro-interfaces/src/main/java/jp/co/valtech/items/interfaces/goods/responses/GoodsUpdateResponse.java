package jp.co.valtech.items.interfaces.goods.responses;

import jp.co.valtech.items.interfaces.definitions.responses.GoodsRes;
import lombok.Data;
import lombok.ToString;

import javax.xml.bind.annotation.XmlElement;

@Data
@ToString
public class GoodsUpdateResponse {

    @XmlElement(name = "goods")
    private GoodsRes goods;
}
