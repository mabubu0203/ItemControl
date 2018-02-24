package jp.co.valtech.items.interfaces.goods.requests;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import jp.co.valtech.items.interfaces.definitions.requests.GoodsReq;
import lombok.Data;
import org.hibernate.validator.constraints.Range;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@Data
@XmlRootElement(name = "request")
@ApiModel(description = "リクエスト情報")
public class GoodsUpdateRequest {

    @Valid
    @XmlElement(name = "goods", required = true)
    @NotNull
    @ApiModelProperty
    private GoodsReq goods;

    @XmlElement(name = "version", required = true)
    @NotNull
    @Range(min = 0, max = 99999)
    @ApiModelProperty(example = "1")
    private int version;

}
