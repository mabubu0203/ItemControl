package jp.co.valtech.items.interfaces.goods.requests;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import jp.co.valtech.items.interfaces.definitions.requests.GoodsReq;
import lombok.Data;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@Data
@XmlRootElement(name = "request")
@ApiModel(description = "リクエスト情報")
public class GoodsCreateRequest {

    @Valid
    @XmlElement(name = "goods", required = true)
    @NotNull
    @ApiModelProperty
    private GoodsReq goods;

}
