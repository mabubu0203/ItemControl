package jp.co.valtech.items.interfaces.category.responses;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import jp.co.valtech.items.interfaces.definitions.responses.CategoryRes;
import lombok.Data;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;

/**
 * @author uratamanabu
 * @version 1.0
 * @since 1.0
 */
@Data
@XmlRootElement(name = "response")
@ApiModel(description = "レスポンス情報")
public class CategoryFindResponse
        implements Serializable {

    private static final long serialVersionUID = -3005643378167781938L;

    @XmlElement(name = "category")
    @ApiModelProperty
    private CategoryRes category;

}
