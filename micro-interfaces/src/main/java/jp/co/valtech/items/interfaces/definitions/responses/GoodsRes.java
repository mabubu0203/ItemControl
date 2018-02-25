package jp.co.valtech.items.interfaces.definitions.responses;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.Range;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;

@Data
@XmlRootElement(name = "goods")
@ApiModel(description = "商品情報")
public class GoodsRes
        implements Serializable {

    private static final long serialVersionUID = -1385773670653213914L;

    @XmlElement(name = "id")
    @Range(min = 0, max = 999999999)
    @ApiModelProperty(example = "1", value = "GOODS_ID")
    private Long id;

    @XmlElement(name = "version")
    @Range(min = 0, max = 99999)
    @ApiModelProperty(example = "1", value = "VERSION")
    private Integer version;

    @XmlElement(name = "name")
    @Length(min = 1, max = 25)
    @ApiModelProperty(example = "明石焼き", value = "NAME")
    private String name;

    @XmlElement(name = "price")
    @Range(min = 0, max = 9999)
    @ApiModelProperty(example = "200", value = "PRICE")
    private Integer price;

}
