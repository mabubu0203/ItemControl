package jp.co.valtech.items.interfaces.definitions.requests;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlElement;

@ApiModel(description = "商品情報")
@Data
@ToString
public class GoodsReq {

    @NotEmpty
    @Length(min = 1, max = 10)
    @XmlElement(name = "code", required = true)
    @ApiModelProperty(example = "AA01")
    private String code;

    @NotEmpty
    @Length(min = 1, max = 25)
    @XmlElement(name = "name", required = true)
    @ApiModelProperty(example = "明石焼き")
    private String name;

    @NotEmpty
    @Size(min = 1, max = 4)
    @XmlElement(name = "price", required = true)
    @ApiModelProperty(example = "200")
    private int price;

    @NotEmpty
    @Length(min = 1, max = 64)
    @XmlElement(name = "note")
    @ApiModelProperty(example = "タコが入っています。")
    private String note;

}
