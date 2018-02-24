package jp.co.valtech.items.interfaces.goods.responses;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.validator.constraints.Range;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@Data
@XmlRootElement(name = "response")
@ApiModel(description = "レスポンス情報")
public class GoodsCreateResponse {

    @XmlElement(name = "goods")
    @ApiModelProperty
    private Goods goods;

    @Data
    @XmlRootElement(name = "goods")
    @ApiModel(description = "商品情報")
    public class Goods {

        @XmlElement(name = "id")
        @Range(min = 0, max = 999999999)
        @ApiModelProperty(example = "1", value = "GOODS_ID")
        private long id;

    }

}
