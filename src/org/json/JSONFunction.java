package org.json;



/**
 *
 * @author fy
 *
 */


public class JSONFunction implements  JSONString{
private String s ;
public JSONFunction(String s){
	if(s.indexOf("function()")>-1){
		this.s = s;
	}else{
		this.s = "function(){"+s+"}";
	}
}

	public String toJSONString(){
		if(s!=null)
		return s;
		return "";
	}
}