package jp.co.valtech.items.interfaces.definitions.responses;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;


@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
@XmlRootElement(name = "error")
@ApiModel(description = "エラー情報")
public class ErrorRes {

    @XmlElement(name = "error_data_list")
    @ApiModelProperty
    private List<Error_data> errorDataList;

    @Data
    @ToString
    @NoArgsConstructor
    @AllArgsConstructor
    @XmlRootElement(name = "error_data")
    @ApiModel(description = "エラー")
    public class Error_data {

        @XmlElement(name = "key_name")
        @ApiModelProperty(example = "エラーキー", value = "KEYNAME")
        private String keyName;

        @XmlElement(name = "message")
        @ApiModelProperty(example = "排他エラーです。", value = "MESSAGE")
        private String message;
    }

}
