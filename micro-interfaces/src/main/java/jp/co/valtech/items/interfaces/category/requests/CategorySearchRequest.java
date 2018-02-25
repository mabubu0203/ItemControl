package jp.co.valtech.items.interfaces.category.requests;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;

/**
 * @author uratamanabu
 * @version 1.0
 * @since 1.0
 */
@Data
@XmlRootElement(name = "request")
@ApiModel(description = "リクエスト情報")
public class CategorySearchRequest
        implements Serializable {

    private static final long serialVersionUID = 2833754991875538664L;

    @Valid
    @XmlElement(name = "condition", required = true)
    @NotNull
    @ApiModelProperty
    private Category condition;

    /**
     * @author uratamanabu
     * @version 1.0
     * @since 1.0
     */
    @Data
    @XmlRootElement(name = "condition")
    @ApiModel(description = "カテゴリー情報検索")
    public class Category
            implements Serializable {

        private static final long serialVersionUID = -2939362585819577676L;

        @XmlElement(name = "categoryCode")
        @Length(min = 1, max = 10)
        @Pattern(regexp = "[a-zA-Z0-9]+")
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

    }
}
