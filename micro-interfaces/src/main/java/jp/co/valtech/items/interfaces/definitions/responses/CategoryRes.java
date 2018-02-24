package jp.co.valtech.items.interfaces.definitions.responses;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.Range;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.time.LocalDateTime;

@Data
@XmlRootElement(name = "category")
@ApiModel(description = "カテゴリー情報")
public class CategoryRes {

    @XmlElement(name = "id")
    @Range(min = 0, max = 999999999)
    @ApiModelProperty(example = "1", value = "ID")
    private long id;

    @XmlElement(name = "version")
    @Range(min = 0, max = 99999)
    @ApiModelProperty(example = "1", value = "VERSION")
    private int version;

    @XmlElement(name = "categoryCode")
    @Length(min = 1, max = 10)
    @ApiModelProperty(example = "AA01", value = "CATEGORY_CODE")
    private String categoryCode;

    @XmlElement(name = "name")
    @Length(min = 1, max = 25)
    @ApiModelProperty(example = "明石焼き", value = "NAME")
    private String name;

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
