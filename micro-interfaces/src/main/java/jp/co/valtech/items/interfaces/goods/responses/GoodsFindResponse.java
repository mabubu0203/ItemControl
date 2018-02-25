package jp.co.valtech.items.interfaces.goods.responses;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import jp.co.valtech.items.interfaces.definitions.responses.GoodsRes;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.time.LocalDateTime;

@Data
@XmlRootElement(name = "response")
@ApiModel(description = "レスポンス情報")
public class GoodsFindResponse {

    @XmlElement(name = "goods")
    @ApiModelProperty
    private GoodsDetail goods;

    @Data
    @XmlRootElement(name = "goods")
    @ApiModel(description = "商品情報")
    public class GoodsDetail extends GoodsRes {

        @XmlElement(name = "goodsCode")
        @Length(min = 1, max = 10)
        @ApiModelProperty(example = "AA01", value = "GOODS_CODE")
        private String goodsCode;

        @XmlElement(name = "categoryCode")
        @Length(min = 1, max = 10)
        @ApiModelProperty(example = "AA01", value = "CATEGORY_CODE")
        private String categoryCode;

        @XmlElement(name = "note")
        @Length(min = 1, max = 64)
        @ApiModelProperty(example = "タコが入っています。", value = "NOTE")
        private String note;

        @XmlElement(name = "create_datetime")
        @ApiModelProperty(example = "2018-02-03T12:14:09.190Z")
        private LocalDateTime createDatetime;

        @XmlElement(name = "update_datetime")
        @ApiModelProperty(example = "2018-02-03T12:14:09.190Z")
        private LocalDateTime updateDatetime;

    }

}
