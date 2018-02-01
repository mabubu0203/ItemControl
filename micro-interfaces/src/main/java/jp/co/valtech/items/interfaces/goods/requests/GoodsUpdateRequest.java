package jp.co.valtech.items.interfaces.goods.requests;

import io.swagger.annotations.ApiModelProperty;
import jp.co.valtech.items.interfaces.definitions.requests.GoodsReq;
import lombok.Data;
import lombok.ToString;

import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlElement;

@Data
@ToString
public class GoodsUpdateRequest {

    @NotNull
    @XmlElement(name = "goods", required = true)
    @ApiModelProperty
    private GoodsReq goods;
}
