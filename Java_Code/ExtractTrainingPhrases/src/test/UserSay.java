package test;

import java.util.ArrayList;

import com.fasterxml.jackson.annotation.JsonProperty;

public class UserSay {
	
	  @JsonProperty("id") 
	    public String getId() { 
			 return this.id; } 
	    public void setId(String id) { 
			 this.id = id; } 
	    String id;
	    @JsonProperty("data") 
	    public ArrayList<Datum> getData() { 
			 return this.data; } 
	    public void setData(ArrayList<Datum> data) { 
			 this.data = data; } 
	    ArrayList<Datum> data;
	    @JsonProperty("isTemplate") 
	    public boolean getIsTemplate() { 
			 return this.isTemplate; } 
	    public void setIsTemplate(boolean isTemplate) { 
			 this.isTemplate = isTemplate; } 
	    boolean isTemplate;
	    @JsonProperty("count") 
	    public int getCount() { 
			 return this.count; } 
	    public void setCount(int count) { 
			 this.count = count; } 
	    int count;
	    @JsonProperty("lang") 
	    public String getLang() { 
			 return this.lang; } 
	    public void setLang(String lang) { 
			 this.lang = lang; } 
	    String lang;
	    @JsonProperty("updated") 
	    public int getUpdated() { 
			 return this.updated; } 
	    public void setUpdated(int updated) { 
			 this.updated = updated; } 
	    int updated;
    
    

}
