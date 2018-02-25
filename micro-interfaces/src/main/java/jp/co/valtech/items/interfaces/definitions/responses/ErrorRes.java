package jp.co.valtech.items.interfaces.definitions.responses;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@Data
@XmlRootElement(name = "error")
@ApiModel(description = "エラー情報")
public class ErrorRes {

    @XmlElement(name = "errorDataList")
    @ApiModelProperty
    private List<ErrorData> errorDataList;

    @Data
    @XmlRootElement(name = "errorData")
    @ApiModel(description = "エラー")
    public class ErrorData {

        @XmlElement(name = "keyName")
        @ApiModelProperty(example = "エラーキー", value = "KEY_NAME")
        private String keyName;

        @XmlElement(name = "message")
        @ApiModelProperty(example = "排他エラーです。", value = "MESSAGE")
        private String message;
    }

}
