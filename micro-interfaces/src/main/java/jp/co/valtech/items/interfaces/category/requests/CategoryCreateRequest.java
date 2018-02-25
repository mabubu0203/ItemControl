package jp.co.valtech.items.interfaces.category.requests;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import jp.co.valtech.items.interfaces.definitions.requests.CategoryReq;
import lombok.Data;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;

@Data
@XmlRootElement(name = "request")
@ApiModel(description = "リクエスト情報")
public class CategoryCreateRequest
        implements Serializable {

    private static final long serialVersionUID = 3760203085133033280L;

    @Valid
    @XmlElement(name = "category", required = true)
    @NotNull
    @ApiModelProperty
    private CategoryReq category;

}
