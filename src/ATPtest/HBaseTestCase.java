package ATPtest;

import java.io.IOException;
import java.io.RandomAccessFile;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.HBaseConfiguration;
import org.apache.hadoop.hbase.HColumnDescriptor;
import org.apache.hadoop.hbase.HTableDescriptor;
import org.apache.hadoop.hbase.client.Get;
import org.apache.hadoop.hbase.client.HBaseAdmin;
import org.apache.hadoop.hbase.client.HTable;
import org.apache.hadoop.hbase.client.Put;
import org.apache.hadoop.hbase.client.Result;
import org.apache.hadoop.hbase.client.ResultScanner;
import org.apache.hadoop.hbase.client.Scan;
import org.apache.hadoop.hbase.util.Bytes;

public class HBaseTestCase {
	
	public static int id = 1;
	public static String tableName ;
	public static String columnFamily ;

	/**
	 * @param args
	 */
	/*
	 * 
	 * public static void table(){ try { HBaseTestCase.create(tableName,
	 * columnFamily); } catch (Exception e) { // TODO Auto-generated catch block
	 * e.printStackTrace(); } }
	 */
	public HBaseTestCase(String tableName,String columnFamily){
		this.tableName=tableName;
		this.columnFamily=columnFamily;
	}
	
	
	
	public static void fun(String T, String V) {
		// TODO Auto-generated method stub

		/*
		 * try { System.out.println(id);
		 */
		/*
		 * if (true == HBaseTestCase.delete(tableName)) {
		 * System.out.println("Delete Table " + tableName + " success!");
		 * 
		 * }
		 */

		try {
			HBaseTestCase.put(tableName, String.valueOf(id), columnFamily, "T", T);
			HBaseTestCase.put(tableName, String.valueOf(id), columnFamily, "V", V);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		

		// System.out.println("成功插入数据库：" + count1 + "条记录！");

		/*
		 * } catch (Exception e) { e.printStackTrace(); }
		 */
	}

	

	static Configuration cfg = HBaseConfiguration.create();
	static {
		System.out.println(cfg.get("hbase.master"));
	}

	public static void create() throws Exception {
		HBaseAdmin admin = new HBaseAdmin(cfg);
		if (admin.tableExists(tableName)) {
			System.out.println(tableName + " exists!");
		} else {
			HTableDescriptor tableDesc = new HTableDescriptor(tableName);
			tableDesc.addFamily(new HColumnDescriptor(columnFamily));
			admin.createTable(tableDesc);
			System.out.println(tableName + " create successfully!");
		}
	}

	public static void put(String tablename, String row, String columnFamily, String column, String v)
			throws Exception {

		HTable table = new HTable(cfg, tablename);
		Put put = new Put(Bytes.toBytes(row));

		put.add(Bytes.toBytes(columnFamily), Bytes.toBytes(column), Bytes.toBytes(v));

		table.put(put);
        id++;
	//	System.out.println("put '" + row + "', '" + columnFamily + ":" + column + "', '" + v + "'");

	}

	public static void get(String tablename, String row) throws Exception {
		HTable table = new HTable(cfg, tablename);
		Get get = new Get(Bytes.toBytes(row));
		Result result = table.get(get);
		System.out.println("Get: " + result);
	}

	public static void scan(String tableName) throws Exception {

		HTable table = new HTable(cfg, tableName);
		Scan s = new Scan();
		ResultScanner rs = table.getScanner(s);

		for (Result r : rs) {
			System.out.println("Scan: " + r);

		}
	}

	public static boolean delete(String tableName) throws IOException {

		HBaseAdmin admin = new HBaseAdmin(cfg);
		if (admin.tableExists(tableName)) {
			try {
				admin.disableTable(tableName);
				admin.deleteTable(tableName);
			} catch (Exception e) {
				e.printStackTrace();
				return false;
			}
		}
		return true;
	}
}
