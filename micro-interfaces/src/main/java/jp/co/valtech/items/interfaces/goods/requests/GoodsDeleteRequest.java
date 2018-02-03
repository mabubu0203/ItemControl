package jp.co.valtech.items.interfaces.goods.requests;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.time.LocalDateTime;

@ApiModel(description = "リクエスト情報")
@Data
@ToString
@XmlRootElement(name = "request")
public class GoodsDeleteRequest {

    @XmlElement(name = "version")
    @ApiModelProperty(example = "1")
    private int version;

}
