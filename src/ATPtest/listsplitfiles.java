package ATPtest;

import java.io.File;

public class listsplitfiles {
	public static String[] listfiles() {
		String path = File.separator + "usr" + File.separator + "work" + File.separator + "dataclean" + File.separator;
		File f = new File(path);
		String[] flist = f.list();
		return flist;
	}
	
	public static String[] listsplitfiles() {
		String path = File.separator + "usr" + File.separator + "work" + File.separator + "split" + File.separator;
		File f = new File(path);
		String[] flist2 = f.list();
		return flist2;
	}
}
