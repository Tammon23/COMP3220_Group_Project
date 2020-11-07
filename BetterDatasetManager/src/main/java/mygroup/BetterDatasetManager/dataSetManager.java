package mygroup.BetterDatasetManager;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import org.json.JSONObject;

public class dataSetManager {
	private int numDatasets;
	private JSONObject datasets;
	private List<String> allFileTypes;
	private List<String> allTags;
	
	public dataSetManager() {
		this.numDatasets = 0;
		this.datasets = new JSONObject();
		this.allFileTypes = new ArrayList<String>();
		this.allTags = new ArrayList<String>();
		
	}
	
	public List<String> getAvailableFileTypes(){
		return this.allFileTypes;
	}
	
	public List<String> getAvailableTags(){
		return this.allTags;
	}
	
	public int getnumDatasets() {
		return this.numDatasets;
	}
	
	public JSONObject getDatasets() {
		return this.datasets;
	}
	
	
    /**
     * Adds a dataset to the list of dataset
     * @param d a dataset object
     */
    public void addDataset(dataSet d) {
    	// alternative option when saving datasets, save it buy an id
    	//  this.datasets.put(Integer.toString(this.numDatasets), d);
    	
    	// adds the dataset into our Json array, 
    	this.datasets.put(d.getTitle(), d.toJson());                                   
    	this.numDatasets++;
    }
    
    /**
     * Loads dataset information from an input JSON file
     * @param filename
     * @throws FileNotFoundException 
     */
    public void loadFromFile(String filename) throws FileNotFoundException {
    	// reading the entire contents of the file as a single string
    	Scanner f = new Scanner(new File(filename)).useDelimiter("\\Z");
    	String jsonString = f.next();
    	
    	// parsing the string into our json object
    	this.datasets = new JSONObject(jsonString);
    	this.numDatasets = this.datasets.length();
    	
    }
    
    /**
     * saves datasets into output JSON file
     * @param filename
     * @throws FileNotFoundException
     */
    
    public void saveToFile(String filename) throws FileNotFoundException {
    	
    	PrintWriter f = new PrintWriter(filename);
    	
    	f.println(this.datasets.toString());
    	
    	f.close();
    	
    }
}