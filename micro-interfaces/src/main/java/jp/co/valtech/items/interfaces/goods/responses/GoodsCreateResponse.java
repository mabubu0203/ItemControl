package jp.co.valtech.items.interfaces.goods.responses;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.validator.constraints.Range;

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
public class GoodsCreateResponse
        implements Serializable {

    private static final long serialVersionUID = -1554764837470653275L;

    @XmlElement(name = "goods")
    @ApiModelProperty
    private Goods goods;

    /**
     * @author uratamanabu
     * @version 1.0
     * @since 1.0
     */
    @Data
    @XmlRootElement(name = "goods")
    @ApiModel(description = "商品情報")
    public class Goods
            implements Serializable {

        private static final long serialVersionUID = 3967426942723130144L;

        @XmlElement(name = "id")
        @Range(min = 0, max = 999999999)
        @ApiModelProperty(example = "1", value = "GOODS_ID")
        private Long id;

    }

}
