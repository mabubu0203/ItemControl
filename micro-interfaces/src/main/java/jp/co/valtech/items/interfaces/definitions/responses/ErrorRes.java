package jp.co.valtech.items.interfaces.definitions.responses;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;
import java.util.List;

/**
 * @author uratamanabu
 * @version 1.0
 * @since 1.0
 */
@Data
@XmlRootElement(name = "error")
@ApiModel(description = "エラー情報")
public class ErrorRes
        implements Serializable {

    private static final long serialVersionUID = -2337862079596568172L;

    @XmlElement(name = "errorDataList")
    @ApiModelProperty
    private List<ErrorData> errorDataList;

    /**
     * @author uratamanabu
     * @version 1.0
     * @since 1.0
     */
    @Data
    @XmlRootElement(name = "errorData")
    @ApiModel(description = "エラー")
    public class ErrorData
            implements Serializable {

        private static final long serialVersionUID = 4856984078768376657L;

        @XmlElement(name = "keyName")
        @ApiModelProperty(example = "エラーキー", value = "KEY_NAME")
        private String keyName;

        @XmlElement(name = "message")
        @ApiModelProperty(example = "排他エラーです。", value = "MESSAGE")
        private String message;
    }

}
