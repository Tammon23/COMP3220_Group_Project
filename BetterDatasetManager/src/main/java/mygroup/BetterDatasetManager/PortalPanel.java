package mygroup.BetterDatasetManager;

import java.awt.Desktop;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;

import javax.swing.JButton;
import javax.swing.JPanel;

import org.json.JSONObject;

/**
 * @author Massimo Albano
 * This class is used to contain the buttons of all data sets, opening the connected webpage to download
 */
public class PortalPanel extends JPanel{
	//List of all the buttons. Array because we know the exact size
	private JButton linkButtons[];
	
	public PortalPanel(JSONObject dataSets, int numDataSets) {
		this.setLayout(new FlowLayout());	//Easy layout to fit everything nicely regardless of frame size
		linkButtons = new JButton[numDataSets];	//Create array with size = number of data sets in JSON file
		
		//Hold the names of all datasets in an array
		String[] dataNames = JSONObject.getNames(dataSets);
		System.out.println();	//Formatting
		
		//This is for testing purposes, can be deleted later
		for(int i = 0; i < dataNames.length; i++) {
			System.out.println(dataNames[i]);
		}
		
		//Instantiate all the buttons in the array with the name of the dataset and an action listener
		for(int i = 0; i < numDataSets; i++) {
			JSONObject tempJSON = (JSONObject) dataSets.get(dataNames[i]);	//This is a JSON object of the current dataset
			final String tempLink = (String) tempJSON.get("link");		//Get the link from the string stored in the JSON file. Saved as final to be used in the action listener
			linkButtons[i] = new JButton(dataNames[i]);			//Instantiate the button with the name of this dataset
			linkButtons[i].setToolTipText(tempJSON.getString("desc"));	//Add a tooltip with the sets description. This needs to be adjusted for formatting
			
			linkButtons[i].addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					openWebpage(tempLink);	//When button is pressed, open the webpage with the link found in the JSON file by calling the custom method
				}
				
			});
			this.add(linkButtons[i]);	//Add this button to the PortalPanel
		}
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
}
