package jp.co.valtech.items.interfaces.definitions.responses;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.Range;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.time.LocalDateTime;

@ApiModel(description = "商品情報")
@Data
@ToString
@XmlRootElement(name = "goods")
public class GoodsRes {

    @XmlElement(name = "id")
    @ApiModelProperty(example = "1", value = "ID")
    private long id;

    @XmlElement(name = "version")
    @ApiModelProperty(example = "1", value = "VERSION")
    private int version;

    @XmlElement(name = "code")
    @Length(min = 1, max = 10)
    @ApiModelProperty(example = "AA01", value = "CODE")
    private String code;

    @XmlElement(name = "name")
    @Length(min = 1, max = 25)
    @ApiModelProperty(example = "明石焼き", value = "NAME")
    private String name;

    @XmlElement(name = "price")
    @Range(min = 0, max = 9999)
    @ApiModelProperty(example = "200", value = "PRICE")
    private int price;

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
