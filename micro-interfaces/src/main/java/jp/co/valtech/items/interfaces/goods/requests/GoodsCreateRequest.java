package jp.co.valtech.items.interfaces.goods.requests;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import jp.co.valtech.items.interfaces.definitions.requests.GoodsReq;
import lombok.Data;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
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
public class GoodsCreateRequest implements Serializable {

    private static final long serialVersionUID = 191785731994911194L;

    @Valid
    @XmlElement(name = "goods", required = true)
    @NotNull
    @ApiModelProperty
    private GoodsReq goods;

}
