package model;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.List;

public class Export {
    public Export (){
    }   

    public void exportOrders(File file, List<Order> orders) throws FileNotFoundException{
        PrintWriter pw = new PrintWriter(file);
    
        for(int i=0;i<orders.size();i++){
          String board = orders.get(i).getId();
          pw.println(board);
          
        }
    
        pw.close();
      }
}
