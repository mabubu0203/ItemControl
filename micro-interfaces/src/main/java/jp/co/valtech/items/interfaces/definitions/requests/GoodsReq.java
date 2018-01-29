package jp.co.valtech.items.interfaces.definitions.requests;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlElement;

@Data
@ToString
public class GoodsReq {

    @NotEmpty
    @Size(max = 10)
    @XmlElement(name = "code", required = true)
    @ApiModelProperty(name = "code", required = true, allowEmptyValue = false, example = "AA01")
    private String code;

    @NotEmpty
    @Size(max = 25)
    @XmlElement(name = "name", required = true)
    @ApiModelProperty(name = "name", required = true, allowEmptyValue = false, example = "明石焼き")
    private String name;

    @NotEmpty
    @Size(max = 4)
    @XmlElement(name = "price", required = true)
    @ApiModelProperty(name = "price", required = true, allowEmptyValue = false, example = "200")
    private int price;

    @NotEmpty
    @Size(max = 64)
    @XmlElement(name = "note")
    @ApiModelProperty(name = "note", required = true, allowEmptyValue = false, example = "タコが入っています。")
    private String note;

}
