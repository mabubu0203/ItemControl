package jp.co.valtech.items.interfaces.goods.responses;

import io.swagger.annotations.ApiModel;
import jp.co.valtech.items.interfaces.definitions.responses.GoodsRes;
import lombok.Data;
import lombok.ToString;

import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@Data
@ToString
@XmlRootElement(name = "response")
@ApiModel(description = "0件取得時空の配列を返却する。")
public class GoodsGetResponse {

    private List<GoodsRes> goodsList;

}
