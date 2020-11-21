package mygroup.BetterDatasetManager;

import java.awt.Desktop;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JPanel;

import org.json.JSONArray;
import org.json.JSONObject;

/**
 * This class is used to show all of the data found in the JSON file
 * @author massi
 *
 */
public class PortalPanel extends JPanel{
	private JButton linkButtons[];
	private dataSetManager manager;
	private ArrayList<dataSet> dataSets;
	int numDataSets;
	
	public PortalPanel(final dataSetManager manager) {
		this.setLayout(new FlowLayout());
		this.manager = manager;
		dataSets = manager.getDatasets();
		numDataSets = manager.getnumDatasets();
		linkButtons = new JButton[numDataSets];
		
		
		for(int i = 0; i < dataSets.size(); i++) {
			final int index = i;
			final dataSet tempDataSet = dataSets.get(index);
			linkButtons[i] = new JButton(tempDataSet.getTitle());
			String tempDesc = tempDataSet.getDesc();
			if(tempDesc.length() >= 100) {
				tempDesc = tempDesc.substring(0, 100) + "...";
			}
			linkButtons[i].setToolTipText(tempDesc);
			
			linkButtons[i].addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					tempDataSet.openWebpage();

					//Need to also increment the value in "timesAccessed" tag of this datasets info in the json file
					try {
						//Update the timesAccessed variable in this dataset
						dataSets.get(index).incrementTimesAccessed();
						System.out.println(tempDataSet.getTitle() + " has been accessed " + (dataSets.get(index).getTimesAccessed()) + " times.");
						
						//Update json file now that a value was incremented
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
}