package mygroup.BetterDatasetManager;

import java.awt.GridLayout;
import java.io.FileNotFoundException;

import javax.swing.JFrame;
import javax.swing.JLabel;

public class App 
{		
    public static void main( String[] args ) throws FileNotFoundException
    {
    	dataSetManager manager = new dataSetManager();
    	
    	manager.loadFromFile(Constants.INPUT_FILE_NAME);
    	
    	//Swing components
    	JFrame portalFrame = new JFrame("Portal Frame");
    	PortalPanel portalPanel;
    	PopularPanel popularPanel;
    	
    	
    	//Set the program to terminate on frame close
    	portalFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    	//Set size of frame, arbitrarily
    	portalFrame.setSize(900,600);
    	//Set the layout to rows and columns to fit all the panels on it
    	portalFrame.setLayout(new GridLayout(4,1));
    	//Allow the frame to actually be seen
    	portalFrame.setVisible(true);
    	
    	//Add the title of the section
    	JLabel popDataLabel = new JLabel("Popular Data");
    	popDataLabel.setHorizontalAlignment(JLabel.CENTER);
    	portalFrame.add(popDataLabel);
    	
    	//Add the newly instantiated popularPanel to the frame
    	popularPanel = new PopularPanel(manager);
    	portalFrame.add(popularPanel);
    	

    	//Add the title of the section
    	JLabel allDataLabel = new JLabel("All Data");
    	allDataLabel.setHorizontalAlignment(JLabel.CENTER);
    	portalFrame.add(allDataLabel);

    	//Add the newly instantiated main panel to the frame
    	portalPanel = new PortalPanel(manager);
    	portalFrame.add(portalPanel);

    	//Revalidated to make sure all the buttons are visible
    	portalFrame.revalidate();
    	
    	
    	//Create the search frame
    	JFrame searchFrame = new JFrame("Search Frame");
    	SearchPanel searchPanel = new SearchPanel(manager);
    	
    	//Formatting
    	searchFrame.add(searchPanel);
    	searchFrame.setVisible(true);
    	searchFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    	searchFrame.setSize(300,300);
    	
    }
}