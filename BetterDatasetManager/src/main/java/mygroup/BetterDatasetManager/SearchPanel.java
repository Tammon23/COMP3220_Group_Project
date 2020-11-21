package mygroup.BetterDatasetManager;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JPanel;

/**
 * This Panel will be used in the search frame, it will have a drop down menu that lists all of the tags, and a button that will 
 * search for data sets with the selected tag and show them on the panel
 * @author massi
 *
 */
public class SearchPanel extends JPanel{
	private ArrayList<String> tags;	//To hold all the tags found in the datasets
	private String[] tagsArray;
	private JComboBox<String> comboBox;	//Drop down menu
	
	public SearchPanel(final dataSetManager manager) {
		//Set layout
		this.setLayout(new FlowLayout());
		
		//Get the list of tags
		tags = manager.getAvailableTags();
		//Save list as array for the combobox
		tagsArray = new String[tags.size()];
		for(int i = 0; i < tags.size(); i++) {
			tagsArray[i] = tags.get(i);
		}
		
		//Instantiate comboBox
		comboBox = new JComboBox<String>(tagsArray);
		
		//Add listener
		comboBox.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				JComboBox cb = (JComboBox) e.getSource();
				
				//Now get a list of all of the datasets that use this selected tag
				final ArrayList<dataSet> setsWithTag = manager.getDatasetByTag(cb.getSelectedItem().toString());
				
				//Array for the buttons that will be used for the data sets that have this tag
				JButton[] taggedSetsButtons = new JButton[setsWithTag.size()];
				
				//The panel that this combobox is on
				JPanel searchPanel = (JPanel) cb.getParent();
				//Remove all components on the panel
				searchPanel.removeAll();
				//Readd the combobox
				searchPanel.add(cb);
				
				//Now, add a button for all of these sets
				for(int i = 0; i < setsWithTag.size(); i++) {
					final int index = i;
					//Create a button for this dataset
					taggedSetsButtons[index] = new JButton(setsWithTag.get(index).getTitle());
					//Add listener to this button
					taggedSetsButtons[index].addActionListener(new ActionListener() {

						@Override
						public void actionPerformed(ActionEvent e) {
							// TODO Auto-generated method stub
							setsWithTag.get(index).openWebpage();
							
							//Need to also increment the value in "timesAccessed" tag of this datasets info in the json file
							try {
								//Update the timesAccessed variable in this dataset
								setsWithTag.get(index).incrementTimesAccessed();
								System.out.println(setsWithTag.get(index).getTimesAccessed());
								
								//Update json file now that a value was incremented
								manager.saveToFile("test.json");
								
							} catch (FileNotFoundException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}
						}
						
					});
					
					//Add the buttons for this tag
					searchPanel.add(taggedSetsButtons[index]);
					//Update visual
					searchPanel.revalidate();
					searchPanel.updateUI();
				}
			}
			
		});
		this.add(comboBox);
	}
}
