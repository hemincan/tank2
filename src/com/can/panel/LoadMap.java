package com.can.panel;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

public class LoadMap {

	ArrayList<int[][]> maplist;

	public LoadMap() {

		maplist = new ArrayList<int[][]>();
		int map1[][]= {{0,0,0,0,0,0,0,0,0,0},   
   			 {0,0,1,0,0,0,0,1,0,0},
   		     {0,1,0,1,0,0,1,0,1,0},
   		     {1,0,0,0,1,1,0,0,0,1},
   		     {0,0,0,0,0,0,0,0,0,0},
   		     {0,1,0,0,0,0,0,0,1,0},
   		     {0,0,1,0,0,0,0,1,0,0},
   		     {0,0,0,1,0,0,1,0,0,0},
   		     {0,0,0,0,1,1,0,0,0,0},
   		     {0,0,0,0,0,0,0,0,0,0}};
   	 
   	 int map2[][]= {{0,0,0,0,0,0,0,0,0,0},   
   			 {0,0,1,1,1,1,0,0,0,0},
   			 {0,1,0,0,0,0,1,0,0,0},
   			 {0,1,0,0,0,0,0,1,0,0},
   			 {0,1,0,0,0,0,0,0,0,0},
   			 {0,1,0,0,0,0,0,0,0,0},
   			 {0,1,0,0,0,0,0,1,0,0},
   			 {0,1,0,0,0,0,1,0,0,0},
   			 {0,0,1,1,1,1,0,0,0,0},
   		     {0,0,0,0,0,0,0,0,0,0}};
   	 
   	 int map3[][]= {{0,0,0,0,0,0,0,0,0,0},   
   			 {0,0,1,0,0,0,0,1,0,0}, 
   			 {0,1,0,1,0,0,0,1,0,0}, 
   			 {0,1,0,0,1,0,0,1,0,0}, 
   			 {0,1,0,0,1,0,0,1,0,0}, 
   			 {0,1,0,0,1,0,0,1,0,0}, 
   			 {0,1,0,0,0,1,0,1,0,0}, 
   			 {0,1,0,0,0,0,1,1,0,0}, 
   			 {1,0,0,0,0,0,0,1,1,0}, 
   			 {0,0,0,0,0,0,0,0,0,0}};
   	 maplist.add(map1);
   	 maplist.add(map2);
   	 maplist.add(map3);
		
		
		/*try {
			ObjectOutputStream obj = new ObjectOutputStream(
					new FileOutputStream("d:/map.dat"));
			obj.writeObject(maplist);

			ObjectInputStream in = new ObjectInputStream(new FileInputStream(
					"d:/map.dat"));
			maplist = (ArrayList<int[][]>) in.readObject();

		} catch (IOException e) {
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}*/

	}
}
