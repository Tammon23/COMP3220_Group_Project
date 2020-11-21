package mygroup.BetterDatasetManager;

import java.awt.Desktop;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONObject;

public class dataSet implements Comparable<dataSet>{
	private String link;
	private String title;
	private String desc;
	private ArrayList<String> tags;
	private ArrayList<String> fileTypes;
	
	private int timesAccessed;
	
	/**
	 * Default constructor
	 */
	public dataSet() {}
	
	/**
	 * Constructor for making a data set from raw inputs
	 * @param link the link to the data set
	 * @param title the title of the data set
	 * @param desc is a description of the data set
	 * @param tags tags associated to the data set
	 */
	public dataSet(String link, String title, String desc, ArrayList<String> tags, ArrayList<String> fileTypes) {
		this.link = link;
		this.title = title;
		this.desc = desc;
		this.tags = tags;
		this.fileTypes = fileTypes;
		this.timesAccessed = 0;
	}
	
	/**
	 * Constructor for making a data set from JSON object
	 * @param link the link to the data set
	 * @param title the title of the data set
	 * @param desc is a description of the data set
	 * @param tags tags associated to the data set
	 */
	public dataSet(JSONObject saved_dataset_json) {
		this.link = (String) saved_dataset_json.get("link");
		this.title = (String) saved_dataset_json.get("title");
		this.desc = (String) saved_dataset_json.get("desc");
		this.timesAccessed = (Integer)saved_dataset_json.get("timesAccessed");
		this.tags = new ArrayList<String>();
		this.fileTypes = new ArrayList<String>();
		
		JSONArray json_tags = (JSONArray) saved_dataset_json.get("tags");
		JSONArray json_fileTypes = (JSONArray) saved_dataset_json.get("fileTypes");
		
		
		for (int i = 0; i < json_tags.length(); i++) {
			this.tags.add(json_tags.getString(i));
		}
		
		for (int i = 0; i < json_fileTypes.length(); i++) {
			this.fileTypes.add(json_fileTypes.getString(i));
		}	

	}
	

	/**
	 * Creates a the JSON representation of a data sets
	 * @return JSONObject
	 */
	public JSONObject toJson() {
		JSONObject jo = new JSONObject();
		jo.put("timesAccessed", this.timesAccessed);
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

	public int getTimesAccessed() {
		return timesAccessed;
	}

	public void setTimesAccessed(int timesAccessed) {
		this.timesAccessed = timesAccessed;
	}
	
	public void incrementTimesAccessed() {
		timesAccessed++;
	}

	@Override
	public int compareTo(dataSet o) {
		// TODO Auto-generated method stub
		return Integer.compare(this.getTimesAccessed(), o.getTimesAccessed());
	}
	
    /**
     * This will open the link saved in this object
     * @param urlString
     */
    public void openWebpage() {
    	
    	try {	
			Desktop.getDesktop().browse(new URL(link).toURI());
		
    	} catch (IOException | URISyntaxException e) {
			;
		}
    }
}
