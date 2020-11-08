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

public class PortalPanel extends JPanel{
	private JButton linkButtons[];
	
	public PortalPanel(JSONObject dataSets, int numDataSets) {
		this.setLayout(new FlowLayout());
		linkButtons = new JButton[numDataSets];
		
		String[] dataNames = JSONObject.getNames(dataSets);
		System.out.println();
		for(int i = 0; i < dataNames.length; i++) {
			System.out.println(dataNames[i]);
		}
		
		for(int i = 0; i < numDataSets; i++) {
			JSONObject tempJSON = (JSONObject) dataSets.get(dataNames[i]);
			final String tempLink = (String) tempJSON.get("link");
			linkButtons[i] = new JButton(dataNames[i]);
			linkButtons[i].setToolTipText(tempJSON.getString("desc"));
			
			linkButtons[i].addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					openWebpage(tempLink);
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
