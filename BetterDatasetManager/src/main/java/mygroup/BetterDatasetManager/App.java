package mygroup.BetterDatasetManager;

import java.awt.Desktop;
import java.awt.FlowLayout;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.Iterator;
import java.util.List;

import javax.swing.JFrame;

import org.json.JSONArray;
import org.json.JSONObject;

//TODO add functionality to the populate the allTags and allFileTypes attributes

public class App 
{		
    public static void main( String[] args ) throws FileNotFoundException
    {
    	dataSetManager manager = new dataSetManager();
    	
    	manager.loadFromFile("test.json");
    	
    	//Swing components
    	JFrame portalFrame = new JFrame("Portal Frame");
    	PortalPanel portalPanel;
    	
    	getSortedJSONArray(manager.getDatasets().toJSONArray(manager.getDatasets().names()));
    	
    	portalFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    	portalFrame.setSize(900,600);
//    	portalFrame.setLayout(new FlowLayout());
    	portalFrame.setVisible(true);
    	
    	portalPanel = new PortalPanel(manager);
    	portalFrame.add(portalPanel);
    	
    	portalFrame.revalidate();
    	
//    	// list of saved datasets
//    	System.out.println("List of saved datasets:\n\n");
//    	for (String key: manager.getDatasets().keySet()) {
//    		System.out.println(key);
//    	}
  
    	
    	
    }
    
    /**
     * Given a string with HTTPS or HTTP, it will open it in the system's default browser
     * @param urlString
     */
    public static void openWebpage(String url) {
    	
    	try {	
			Desktop.getDesktop().browse(new URL(url).toURI());
		
    	} catch (IOException | URISyntaxException e) {
			;
		}
    }
    
    
    public static JSONArray getSortedJSONArray(JSONArray jsArr) {
    	JSONArray jsonArr = jsArr;
    	
    	//jsArr has an element for each JSONObject in the file, hence each dataset is its own element in this JSONArray
    	
    	
    	
    	
    	for(int i = 0; i < jsonArr.length(); i++) {
    		System.out.println("JSONObject " + i + ": " + jsonArr.get(i));
    	}
    	

    	
    	return jsonArr;
    }
}
/*
   "example title 1":{
      "link":"example link 1",
      "title":"example title 1",
      "desc":"example desc 1",
      "tags":[
         "tag v1 : 1",
         "tag v2 : 1"
      ],
      "fileTypes":[
         "ftype v1 : 1",
         "ftype v2 : 1"
      ]
   }
*/
