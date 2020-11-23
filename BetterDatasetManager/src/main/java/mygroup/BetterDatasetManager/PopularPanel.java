package mygroup.BetterDatasetManager;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;

import javax.swing.JButton;
import javax.swing.JPanel;

/**
 * This will host the 5 most accessed data sets by sorting the ArrayList
 * @author massi
 *
 */
public class PopularPanel extends JPanel{
	private dataSet[] popularSets = new dataSet[5];
	private JButton[] popularButtons = new JButton[5];
	private dataSetManager manager;
	private ArrayList<dataSet> dataSets;
	
	public PopularPanel(final dataSetManager manager) {
		this.setLayout(new FlowLayout());
		this.manager = manager;
		dataSets = (ArrayList<dataSet>) manager.getDatasets().clone();
		
		//Sort dataSets, natively sorted by timesAccessed as defined in dataSet.java in ascending order
		Collections.sort(dataSets);
		Collections.reverse(dataSets);
		
		for(int i = 0; i < 5; i++) {
			final int index = i;
			popularButtons[i] = new JButton(dataSets.get(i).getTitle());
			String tempDesc = dataSets.get(index).getDesc();
			if(tempDesc.length() >= 100) {
				tempDesc = tempDesc.substring(0, 100) + "...";
			}
			popularButtons[i].setToolTipText(tempDesc);			
			popularButtons[i].addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					// TODO Auto-generated method stub
					dataSets.get(index).openWebpage();
					
					//Need to also increment the value in "timesAccessed" tag of this datasets info in the JSON file
					try {
						//Update the timesAccessed variable in this dataset
						dataSets.get(index).incrementTimesAccessed();
						
						//Update JSON file now that a value was incremented
						manager.saveToFile(Constants.INPUT_FILE_NAME);
						
					} catch (FileNotFoundException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
				
			});
			
			//Add this button to the panel
			this.add(popularButtons[i]);
		}
	}
}
