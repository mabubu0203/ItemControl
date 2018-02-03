package jp.co.valtech.items.interfaces.goods.responses;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@ApiModel(description = "レスポンス情報")
@Data
@ToString
@XmlRootElement(name = "response")
public class GoodsUpdateResponse {

    @XmlElement(name = "goods")
    @ApiModelProperty
    private Goods goods;

    @ApiModel(description = "商品情報")
    @Data
    @ToString
    @XmlRootElement(name = "goods")
    public class Goods {

        @XmlElement(name = "id")
        @ApiModelProperty(example = "1")
        private long id;

    }

}
