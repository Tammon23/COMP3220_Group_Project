package mygroup.BetterDatasetManager;

import java.util.ArrayList;
import org.json.JSONArray;
import org.json.JSONObject;

public class dataSet {
	private String link;                         // the link associated with a dataset
	private String title;                        // the title associated with a dataset
	private String desc;                         // the description associated with a dataset
	private ArrayList<String> tags;              // A list of tags that we came up with that represents a dataset
	private ArrayList<String> fileTypes;         // The file types that the dataset can be downloaded as
	
	/**
	 * Default constructor
	 */
	public dataSet() {}
	
	/**
	 * Creates a data set object, and sets all the attributes
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
	
	/**
	* getter for link attribute
	* @return String the link
	*/
	public String getLink() {
		return link;
	}
	
	/**
	* setter for link attribute
	* @param link
	*/
	public void setLink(String link) {
		this.link = link;
	}
	
	/**
	* getter for title attribute
	* @return String the title
	*/
	public String getTitle() {
		return title;
	}
	
	/**
	* setter for title attribute
	* @param title
	*/
	public void setTitle(String title) {
		this.title = title;
	}
	
	/**
	* getter for description attribute
	* @return String the description
	*/
	public String getDesc() {
		return desc;
	}
	
	/**
	* setter for description attribute
	* @param desc
	*/
	public void setDesc(String desc) {
		this.desc = desc;
	}
	
	/**
	* getter for tags attribute
	* @return ArrayList<String> the list of tags
	*/
	public ArrayList<String>  getTags() {
		return tags;
	}
	
	/**
	* setter for tag attribute
	* @param ArrayList<String> the list of tags
	*/
	public void setTags(ArrayList<String>  tags) {
		this.tags = tags;
	}
	
	/**
	* getter for file types attribute
	* @return ArrayList<String> the list of file types
	*/
	public ArrayList<String>  getFileTypes() {
		return fileTypes;
	}
	
	/**
	* setter for file types attribute
	* @param ArrayList<String> the list of file types
	*/
	public void setFileTypes(ArrayList<String>  fileTypes) {
		this.fileTypes = fileTypes;
	}
}
