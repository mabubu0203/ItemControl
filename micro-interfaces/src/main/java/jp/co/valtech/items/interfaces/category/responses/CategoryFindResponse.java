package jp.co.valtech.items.interfaces.category.responses;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import jp.co.valtech.items.interfaces.definitions.responses.CategoryRes;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.validator.constraints.Length;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;
import java.time.LocalDateTime;

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
    private CategoryDetail category;

    /**
     * @author uratamanabu
     * @version 1.0
     * @since 1.0
     */
    @Data
    @EqualsAndHashCode(callSuper = false)
    @XmlRootElement(name = "category")
    @ApiModel(description = "商品情報")
    public class CategoryDetail
            extends CategoryRes
            implements Serializable {

        private static final long serialVersionUID = 4596780254818182943L;

        @XmlElement(name = "categoryCode")
        @Length(min = 1, max = 10)
        @ApiModelProperty(example = "AA01", value = "CATEGORY_CODE")
        private String categoryCode;

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

}
