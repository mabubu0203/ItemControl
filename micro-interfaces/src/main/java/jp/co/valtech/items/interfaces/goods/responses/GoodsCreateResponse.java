package jp.co.valtech.items.interfaces.goods.responses;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.validator.constraints.Range;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
@XmlRootElement(name = "response")
@ApiModel(description = "レスポンス情報")
public class GoodsCreateResponse {

    @XmlElement(name = "goods")
    @ApiModelProperty
    private Goods goods;

    @Data
    @ToString
    @NoArgsConstructor
    @AllArgsConstructor
    @XmlRootElement(name = "goods")
    @ApiModel(description = "商品情報")
    public class Goods {

        @XmlElement(name = "id")
        @Range(min = 0, max = 999999999)
        @ApiModelProperty(example = "1")
        private long id;

    }

}
