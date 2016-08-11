
package sample;

import javax.annotation.Generated;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Generated("org.jsonschema2pojo")
@JsonPropertyOrder({
    "msg",
    "sub",
    "demail"
})
public class Object {

    @JsonProperty("msg")
    private String msg;
    @JsonProperty("sub")
    private String sub;
    @JsonProperty("demail")
    private String demail;

    /**
     * 
     * @return
     *     The msg
     */
    @JsonProperty("msg")
    public String getMsg() {
        return msg;
    }

    /**
     * 
     * @param msg
     *     The msg
     */
    @JsonProperty("msg")
    public void setMsg(String msg) {
        this.msg = msg;
    }

    /**
     * 
     * @return
     *     The sub
     */
    @JsonProperty("sub")
    public String getSub() {
        return sub;
    }

    /**
     * 
     * @param sub
     *     The sub
     */
    @JsonProperty("sub")
    public void setSub(String sub) {
        this.sub = sub;
    }

    /**
     * 
     * @return
     *     The demail
     */
    @JsonProperty("demail")
    public String getDemail() {
        return demail;
    }

    /**
     * 
     * @param demail
     *     The demail
     */
    @JsonProperty("demail")
    public void setDemail(String demail) {
        this.demail = demail;
    }

}
