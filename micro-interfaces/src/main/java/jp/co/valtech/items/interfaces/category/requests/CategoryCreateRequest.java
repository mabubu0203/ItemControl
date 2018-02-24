package jp.co.valtech.items.interfaces.category.requests;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import jp.co.valtech.items.interfaces.definitions.requests.CategoryReq;
import lombok.Data;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@Data
@XmlRootElement(name = "request")
@ApiModel(description = "リクエスト情報")
public class CategoryCreateRequest {

    @Valid
    @XmlElement(name = "category", required = true)
    @NotNull
    @ApiModelProperty
    private CategoryReq category;

}
