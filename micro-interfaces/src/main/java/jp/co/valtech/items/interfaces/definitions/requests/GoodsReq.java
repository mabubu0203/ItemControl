package jp.co.valtech.items.interfaces.definitions.requests;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;

/**
 * @author uratamanabu
 * @version 1.0
 * @since 1.0
 */
@Data
@XmlRootElement(name = "goods")
@ApiModel(description = "商品情報")
public class GoodsReq
        implements Serializable {

    private static final long serialVersionUID = -8223443889749129561L;

    @XmlElement(name = "goodsCode", required = true)
    @NotEmpty
    @Length(min = 1, max = 10)
    @Pattern(regexp = "[a-zA-Z0-9]+")
    @ApiModelProperty(example = "AA01", value = "GOODS_CODE")
    private String goodsCode;

    @XmlElement(name = "categoryCode")
    @Length(min = 1, max = 10)
    @Pattern(regexp = "[a-zA-Z0-9]+")
    @ApiModelProperty(example = "AA01", value = "CATEGORY_CODE")
    private String categoryCode;

    @XmlElement(name = "name", required = true)
    @NotEmpty
    @Length(min = 1, max = 25)
    @ApiModelProperty(example = "明石焼き", value = "NAME")
    private String name;

    @XmlElement(name = "price", required = true)
    @NotNull
    @Range(min = 0, max = 9999)
    @ApiModelProperty(example = "200", value = "PRICE")
    private Integer price;

    @XmlElement(name = "note")
    @Length(min = 1, max = 64)
    @ApiModelProperty(example = "タコが入っています。", value = "NOTE")
    private String note;

}
