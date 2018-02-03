package jp.co.valtech.items.interfaces.definitions.responses;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@ApiModel(description = "商品情報")
@Data
@ToString
@XmlRootElement(name = "goods")
public class GoodsRes {

    @XmlElement(name = "id")
    @ApiModelProperty(example = "1")
    private long id;

    @Length(min = 1, max = 10)
    @XmlElement(name = "code")
    @ApiModelProperty(example = "AA01")
    private String code;

    @Length(min = 1, max = 25)
    @XmlElement(name = "name")
    @ApiModelProperty(example = "明石焼き")
    private String name;

    @Size(min = 1, max = 4)
    @XmlElement(name = "price")
    @ApiModelProperty(example = "200")
    private int price;

    @Length(min = 1, max = 64)
    @XmlElement(name = "note")
    @ApiModelProperty(example = "タコが入っています。")
    private String note;

}
