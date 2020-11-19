package mygroup.BetterDatasetManager;

import java.awt.Desktop;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;

import javax.swing.JButton;
import javax.swing.JPanel;

import org.json.JSONObject;

/**
 * This class is used to 
 * @author massi
 *
 */
public class PortalPanel extends JPanel{
	private JButton linkButtons[];
	dataSetManager manager;
	JSONObject dataSets;
	int numDataSets;
	
	public PortalPanel(final dataSetManager manager) {
		this.setLayout(new FlowLayout());
		this.manager = manager;
		dataSets = manager.getDatasets();
		numDataSets = manager.getnumDatasets();
		linkButtons = new JButton[numDataSets];
		
		String[] dataNames = JSONObject.getNames(dataSets);
		System.out.println();
		for(int i = 0; i < dataNames.length; i++) {
			System.out.println(dataNames[i]);
		}
		
		for(int i = 0; i < numDataSets; i++) {
			final JSONObject tempJSON = (JSONObject) dataSets.get(dataNames[i]);
			final String tempLink = (String) tempJSON.get("link");
			linkButtons[i] = new JButton(dataNames[i]);
			linkButtons[i].setToolTipText(tempJSON.getString("desc"));
			
			linkButtons[i].addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					openWebpage(tempLink);
					System.out.println("You clicked: " + tempJSON.getString("title"));

					//Need to also increment the value in "timesAccessed" tag of this datasets info in the json file
					try {
						//Update the timesAccessed variable in this dataset
						manager.incrementSetTimesAccessed(tempJSON.getString("title"));
						
						manager.saveToFile("test.json");
					} catch (FileNotFoundException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
				
			});
			this.add(linkButtons[i]);
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
