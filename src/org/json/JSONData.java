package org.json;

/**
 *
 * @author fy
 *
 */
public class JSONData implements JSONString {

    private Object o;

    public JSONData(Object o) {
        this.o = o;
    }

    public String toJSONString() {
        if (o != null) {
            return o.toString();
        }
        return "";
    }
}