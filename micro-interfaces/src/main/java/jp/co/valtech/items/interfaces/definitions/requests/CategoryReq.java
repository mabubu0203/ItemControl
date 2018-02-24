package jp.co.valtech.items.interfaces.definitions.requests;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@Data
@XmlRootElement(name = "category")
@ApiModel(description = "カテゴリー情報")
public class CategoryReq {

    @XmlElement(name = "code", required = true)
    @NotEmpty
    @Length(min = 1, max = 10)
    @Pattern(regexp = "[a-zA-Z0-9]+")
    @ApiModelProperty(example = "AA01", value = "CATEGORY_CODE")
    private String code;

    @XmlElement(name = "name", required = true)
    @NotEmpty
    @Length(min = 1, max = 25)
    @ApiModelProperty(example = "明石焼き", value = "NAME")
    private String name;

    @XmlElement(name = "note")
    @Length(min = 1, max = 64)
    @ApiModelProperty(example = "タコが入っています。", value = "NOTE")
    private String note;

}
