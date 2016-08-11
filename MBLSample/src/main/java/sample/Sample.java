
package sample;

import javax.annotation.Generated;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Generated("org.jsonschema2pojo")
@JsonPropertyOrder({
    "semail",
    "number",
    "object",
    "string"
})
public class Sample {

    @JsonProperty("semail")
    private String semail;
    @JsonProperty("number")
    private int number;
    @JsonProperty("object")
    private Object object;
    @JsonProperty("string")
    private String string;

    /**
     * 
     * @return
     *     The semail
     */
    @JsonProperty("semail")
    public String getSemail() {
        return semail;
    }

    /**
     * 
     * @param semail
     *     The semail
     */
    @JsonProperty("semail")
    public void setSemail(String semail) {
        this.semail = semail;
    }

    /**
     * 
     * @return
     *     The number
     */
    @JsonProperty("number")
    public int getNumber() {
        return number;
    }

    /**
     * 
     * @param number
     *     The number
     */
    @JsonProperty("number")
    public void setNumber(int number) {
        this.number = number;
    }

    /**
     * 
     * @return
     *     The object
     */
    @JsonProperty("object")
    public Object getObject() {
        return object;
    }

    /**
     * 
     * @param object
     *     The object
     */
    @JsonProperty("object")
    public void setObject(Object object) {
        this.object = object;
    }

    /**
     * 
     * @return
     *     The string
     */
    @JsonProperty("string")
    public String getString() {
        return string;
    }

    /**
     * 
     * @param string
     *     The string
     */
    @JsonProperty("string")
    public void setString(String string) {
        this.string = string;
    }

}
