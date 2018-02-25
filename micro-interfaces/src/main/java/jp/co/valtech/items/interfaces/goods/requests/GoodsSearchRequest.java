package jp.co.valtech.items.interfaces.goods.requests;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.Range;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;

@Data
@XmlRootElement(name = "request")
@ApiModel(description = "リクエスト情報")
public class GoodsSearchRequest
        implements Serializable {

    private static final long serialVersionUID = -2087871968180022094L;

    @Valid
    @XmlElement(name = "condition", required = true)
    @NotNull
    @ApiModelProperty
    private Goods condition;

    @Data
    @XmlRootElement(name = "condition")
    @ApiModel(description = "商品情報検索")
    public class Goods
            implements Serializable {

        private static final long serialVersionUID = 4548750832966381796L;

        @XmlElement(name = "goodsCode")
        @Length(min = 1, max = 10)
        @Pattern(regexp = "[a-zA-Z0-9]+")
        @ApiModelProperty(example = "AA01", value = "GOODS_CODE")
        private String goodsCode;

        @XmlElement(name = "name")
        @Length(min = 1, max = 25)
        @ApiModelProperty(example = "明石焼き", value = "NAME")
        private String name;

        @XmlElement(name = "price_from")
        @Range(min = 0, max = 9999)
        @ApiModelProperty(example = "200", value = "PRICE")
        private Integer price_from;

        @XmlElement(name = "price_to")
        @Range(min = 0, max = 9999)
        @ApiModelProperty(example = "200", value = "PRICE")
        private Integer price_to;

        @XmlElement(name = "note")
        @Length(min = 1, max = 64)
        @ApiModelProperty(example = "タコが入っています。", value = "NOTE")
        private String note;

    }
}
