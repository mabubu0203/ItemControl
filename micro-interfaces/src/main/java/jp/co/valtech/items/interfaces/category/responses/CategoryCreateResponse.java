package jp.co.valtech.items.interfaces.category.responses;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.validator.constraints.Range;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@Data
@XmlRootElement(name = "response")
@ApiModel(description = "レスポンス情報")
public class CategoryCreateResponse {

    @XmlElement(name = "category")
    @ApiModelProperty
    private CategoryRes category;

    @Data
    @XmlRootElement(name = "category")
    @ApiModel(description = "商品情報")
    public class CategoryRes {

        @XmlElement(name = "id")
        @Range(min = 0, max = 999999999)
        @ApiModelProperty(example = "1", value = "CATEGORY_ID")
        private Long id;

    }
}
