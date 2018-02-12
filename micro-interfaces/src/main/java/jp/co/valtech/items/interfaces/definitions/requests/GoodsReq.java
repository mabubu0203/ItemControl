package jp.co.valtech.items.interfaces.definitions.requests;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@Data
@ToString
@XmlRootElement(name = "goods")
@ApiModel(description = "商品情報")
public class GoodsReq {

    @XmlElement(name = "code", required = true)
    @NotEmpty
    @Length(min = 1, max = 10)
    @Pattern(regexp = "[a-zA-Z0-9]+")
    @ApiModelProperty(example = "AA01", value = "CODE")
    private String code;

    @XmlElement(name = "name", required = true)
    @NotEmpty
    @Length(min = 1, max = 25)
    @ApiModelProperty(example = "明石焼き", value = "NAME")
    private String name;

    @XmlElement(name = "price", required = true)
    @NotNull
    @Range(min = 0, max = 9999)
    @ApiModelProperty(example = "200", value = "PRICE")
    private int price;


    @XmlElement(name = "note")
    @Length(min = 1, max = 64)
    @ApiModelProperty(example = "タコが入っています。", value = "NOTE")
    private String note;

}
