package jp.co.valtech.items.interfaces.goods.responses;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import jp.co.valtech.items.interfaces.definitions.responses.GoodsRes;
import lombok.Data;
import lombok.ToString;

import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@ApiModel(description = "0件取得時空の配列を返却する。")
@Data
@ToString
@XmlRootElement(name = "response")
public class GoodsGetResponse {

    private List<GoodsRes> goodsList;

}
