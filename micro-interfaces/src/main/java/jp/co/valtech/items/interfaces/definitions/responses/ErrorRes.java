package jp.co.valtech.items.interfaces.definitions.responses;

import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@ApiModel(description = "エラー情報")
@Data
@ToString
@XmlRootElement(name = "error")
@NoArgsConstructor
@AllArgsConstructor
public class ErrorRes {

    private List<Error_data> errorDataList;

    @ApiModel(description = "エラー")
    @Data
    @ToString
    @XmlRootElement(name = "error_data")
    @NoArgsConstructor
    @AllArgsConstructor
    public class Error_data {
        private String KeyName;
        private String Message;
    }

}
