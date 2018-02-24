package jp.co.valtech.items.interfaces.category.responses;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import jp.co.valtech.items.interfaces.definitions.responses.CategoryRes;
import lombok.Data;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@Data
@XmlRootElement(name = "response")
@ApiModel(description = "0件取得時空の配列を返却する。")
public class CategorySearchResponse {

    @XmlElement(name = "categoryList")
    @ApiModelProperty
    private List<CategoryRes> categoryList;

}
