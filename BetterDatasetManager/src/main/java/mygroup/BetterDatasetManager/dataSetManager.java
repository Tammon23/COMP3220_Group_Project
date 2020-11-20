package mygroup.BetterDatasetManager;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

import org.json.JSONObject;
import org.json.JSONArray;

public class dataSetManager {
	private ArrayList<dataSet> datasets;
	private ArrayList<String> allFileTypes;
	private ArrayList<String> allTags;
	
	public dataSetManager() {
		this.datasets = new ArrayList<dataSet>();
		this.allFileTypes = new ArrayList<String>();
		this.allTags = new ArrayList<String>();
		
	}
	
	public ArrayList<String> getAvailableFileTypes(){
		return this.allFileTypes;
	}
	
	public ArrayList<String> getAvailableTags(){
		return this.allTags;
	}
	
	public int getnumDatasets() {
		return this.datasets.size();
	}
	
	public ArrayList<dataSet> getDatasets() {
		return this.datasets;
	}
	
	/**
	 * Given a tag it will return an ArrayList<dataSet> with matching tags
	 * @param tag The string you want to filter by
	 * @return ArrayList<dataSet>
	 */
	public ArrayList<dataSet> getDatasetByTag(String tag){
		ArrayList<dataSet> filteredDatasets = new ArrayList<dataSet>();
		
		for (int i = 0; i < this.datasets.size(); i++) {
			if (this.datasets.get(i).getTags().contains(tag)) {
				filteredDatasets.add(this.datasets.get(i));
			}
		}
		
		return filteredDatasets;
	}
	
	
	
    /**
     * Adds a data set to the list of data set
     * @param d a data set object
     */
    public void addDataset(dataSet d) {
    	// adds the data set into our JSON array
    	this.datasets.add(d);
    }
    
  
    
    /**
     * Loads data set information from an input JSON file
     * @param filename
     * @throws FileNotFoundException 
     */
    public void loadFromFile(String filename) throws FileNotFoundException {
    	ArrayList<dataSet> dataset_list = new ArrayList<dataSet>();
    	
    	// reading the entire contents of the file as a single string
    	Scanner f = new Scanner(new File(filename)).useDelimiter("\\Z");
    	JSONArray JSONArray_of_datasets = new JSONArray(f.next());
    	
    	
    	// iterating over each data set in our JSON, converting to a data set, then adding it to the list
    	for (int i = 0; i < JSONArray_of_datasets.length(); i++) {
    		dataSet dataset_from_json = new dataSet((JSONObject) JSONArray_of_datasets.getJSONObject(i));
    		dataset_list.add(dataset_from_json);
    		
    		// updating out list of all available tags if we see a new one
    		ArrayList<String> dataset_tags = dataset_from_json.getTags();
    		for (int j = 0; j < dataset_tags.size(); j++) {
    			if (!this.allTags.contains(dataset_tags.get(j))){
    				this.allTags.add(dataset_tags.get(j));
    			}
    		}
    	}
    	
    	
    	// parsing the string into our JSON object
    	this.datasets = dataset_list;

//    	System.out.println(this.datasets.get("Alley Maintenance Service Requests"));
    }
    
    /**
     * saves data sets into output JSON file
     * @param filename
     * @throws FileNotFoundException
     */
    public void saveToFile(String filename) throws FileNotFoundException {
    	
    	PrintWriter file = new PrintWriter(filename);
    	JSONArray array = new JSONArray();
    	
    	// iterating through each data set and converting it to a JSON string
    	// then adding that string to our JSON array
    	for (dataSet d : this.datasets) {
    		array.put(d.toJson());
    	}
    	
    	// writing the JSON array to the file
    	file.println(array.toString());
    	
    	file.close();
    	
    }
    
    
}