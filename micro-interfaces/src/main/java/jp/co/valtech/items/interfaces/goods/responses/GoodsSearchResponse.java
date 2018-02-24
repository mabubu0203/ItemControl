package jp.co.valtech.items.interfaces.goods.responses;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import jp.co.valtech.items.interfaces.definitions.responses.GoodsRes;
import lombok.Data;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@Data
@XmlRootElement(name = "response")
@ApiModel(description = "0件取得時空の配列を返却する。")
public class GoodsSearchResponse {

    @XmlElement(name = "goodsList")
    @ApiModelProperty
    private List<GoodsRes> goodsList;

}
