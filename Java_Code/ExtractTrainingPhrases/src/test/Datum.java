package test;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Datum{
	@JsonProperty("text") 
    public String getText() { 
		 return this.text; } 
    public void setText(String text) { 
		 this.text = text; } 
    String text;
    @JsonProperty("userDefined") 
    public boolean getUserDefined() { 
		 return this.userDefined; } 
    public void setUserDefined(boolean userDefined) { 
		 this.userDefined = userDefined; } 
    boolean userDefined;
    @JsonProperty("meta") 
    public String getMeta() { 
		 return this.meta; } 
    public void setMeta(String meta) { 
		 this.meta = meta; } 
    String meta;
    @JsonProperty("alias") 
    public String getAlias() { 
		 return this.alias; } 
    public void setAlias(String alias) { 
		 this.alias = alias; } 
    String alias;
    
}
