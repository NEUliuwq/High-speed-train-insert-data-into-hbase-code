package ATPtest;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;

public class countcolume {

	public static int count(int l) throws Exception {
		String [] str=listsplitfiles.listfiles();
		int count = 0;
		BufferedReader br = null;
		br = new BufferedReader(new InputStreamReader(
				new FileInputStream(File.separator+"usr" +File.separator+"work"+File.separator + "datalcean" + File.separator  +str[l])));
		String line = null;
		while ((line = br.readLine()) != null) {

			count++;

		}
		return count;
	}
	
	public static int splitcount(int l) throws Exception {
		String [] str=listsplitfiles.listsplitfiles();
		int count = 0;
		BufferedReader br = null;
		br = new BufferedReader(new InputStreamReader(
				new FileInputStream(File.separator+"usr" +File.separator+"work"+File.separator + "split" + File.separator  +str[l])));
		String line = null;
		while ((line = br.readLine()) != null) {

			count++;

		}
		return count;
	}

}
