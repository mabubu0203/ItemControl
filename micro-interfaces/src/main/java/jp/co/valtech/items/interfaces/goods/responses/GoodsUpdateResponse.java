package jp.co.valtech.items.interfaces.goods.responses;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiResponse;
import jp.co.valtech.items.interfaces.definitions.responses.GoodsRes;
import lombok.Data;
import lombok.ToString;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@ApiModel(description = "レスポンス情報")
@Data
@ToString
@XmlRootElement(name = "response")
public class GoodsUpdateResponse {

    @XmlElement(name = "goods")
    @ApiModelProperty
    private GoodsRes goods;

}
