package jp.co.valtech.items.interfaces.category.responses;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import jp.co.valtech.items.interfaces.definitions.responses.CategoryRes;
import lombok.Data;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;
import java.util.List;

/**
 * @author uratamanabu
 * @version 1.0
 * @since 1.0
 */
@Data
@XmlRootElement(name = "response")
@ApiModel(description = "0件取得時空の配列を返却する。")
public class CategorySearchResponse
        implements Serializable {

    private static final long serialVersionUID = 6903306826800621861L;

    @XmlElement(name = "categoryList")
    @ApiModelProperty
    private List<CategoryRes> categoryList;

}
