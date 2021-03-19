package ui;

import model.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import javax.swing.Timer;
import java.awt.event.*;


public class AppMainGui {
    public AppMainGui(RestaurantSystem restaurant){      
    }

    // ---------------- Current time 
    
	public void currentTime() throws InterruptedException {
		final DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		int interval = 1000; // 1000 ms

	
		new Timer(interval,  new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Calendar now = Calendar.getInstance();
				 dateFormat.format(now.getTime());  // set the label with this line
			}
			
		}).start();
	
		Thread.currentThread().join();
	}
}
