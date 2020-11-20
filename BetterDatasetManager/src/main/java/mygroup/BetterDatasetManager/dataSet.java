package mygroup.BetterDatasetManager;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONObject;

public class dataSet implements Comparable<dataSet>{
	private String link;
	private String title;
	private String desc;
	private ArrayList<String> tags;
	private ArrayList<String> fileTypes;
	
	private Integer timesAccessed;
	
	/**
	 * Default constructor
	 */
	public dataSet() {}
	
	/**
	 * 
	 * @param link the link to the dataset
	 * @param title the title of the dataset
	 * @param desc is a description of the dataset
	 * @param tags tags assositated to the dataset
	 */
	public dataSet(String link, String title, String desc, ArrayList<String> tags, ArrayList<String> fileTypes) {
		this.link = link;
		this.title = title;
		this.desc = desc;
		this.tags = tags;
		this.fileTypes = fileTypes;
	}
	
	

	/**
	 * Creates a the json representation of a datasets
	 * @return JSONObject
	 */
	public JSONObject toJson() {
		JSONObject jo = new JSONObject();
		jo.put("link", this.link);
		jo.put("title", this.title);
		jo.put("desc", this.desc);
		jo.put("tags", new JSONArray(this.tags));
		jo.put("fileTypes", new JSONArray(this.fileTypes));
		
		return jo;
	}
	
	
	public String getLink() {
		return link;
	}
	
	public void setLink(String link) {
		this.link = link;
	}
	
	public String getTitle() {
		return title;
	}
	
	public void setTitle(String title) {
		this.title = title;
	}
	
	public String getDesc() {
		return desc;
	}
	
	public void setDesc(String desc) {
		this.desc = desc;
	}
	
	public ArrayList<String>  getTags() {
		return tags;
	}
	
	public void setTags(ArrayList<String>  tags) {
		this.tags = tags;
	}
	
	public ArrayList<String>  getFileTypes() {
		return fileTypes;
	}

	public void setFileTypes(ArrayList<String>  fileTypes) {
		this.fileTypes = fileTypes;
	}

	public Integer getTimesAccessed() {
		return timesAccessed;
	}

	public void setTimesAccessed(Integer timesAccessed) {
		this.timesAccessed = timesAccessed;
	}

	@Override
	public int compareTo(dataSet o) {
		// TODO Auto-generated method stub
		return this.getTimesAccessed().compareTo(o.getTimesAccessed());
	}

}
