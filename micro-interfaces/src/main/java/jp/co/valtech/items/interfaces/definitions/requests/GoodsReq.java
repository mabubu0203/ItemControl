package jp.co.valtech.items.interfaces.definitions.requests;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@ApiModel(description = "商品情報")
@Data
@ToString
@XmlRootElement(name = "goods")
public class GoodsReq {

    @NotEmpty
    @Length(min = 1, max = 10)
    @Pattern(regexp = "[a-zA-Z0-9]+")
    @XmlElement(name = "code", required = true)
    @ApiModelProperty(example = "AA01")
    private String code;

    @NotEmpty
    @Length(min = 1, max = 25)
    @XmlElement(name = "name", required = true)
    @ApiModelProperty(example = "明石焼き")
    private String name;

    @XmlElement(name = "price", required = true)
    @Range(min = 0, max = 9999)
    @ApiModelProperty(example = "200")
    private int price;

    @NotEmpty
    @Length(min = 1, max = 64)
    @XmlElement(name = "note")
    @ApiModelProperty(example = "タコが入っています。")
    private String note;

}
