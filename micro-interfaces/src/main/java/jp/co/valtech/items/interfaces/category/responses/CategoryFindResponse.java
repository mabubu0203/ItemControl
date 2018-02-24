package jp.co.valtech.items.interfaces.category.responses;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import jp.co.valtech.items.interfaces.definitions.responses.CategoryRes;
import lombok.Data;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@Data
@XmlRootElement(name = "response")
@ApiModel(description = "レスポンス情報")
public class CategoryFindResponse {

    @XmlElement(name = "category")
    @ApiModelProperty
    private CategoryRes category;

}
