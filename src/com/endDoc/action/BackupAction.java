package com.endDoc.action;

import java.io.*;
import com.opensymphony.xwork2.ActionSupport;

public class BackupAction extends ActionSupport{
	
	public String backupData() throws IOException{
		// 要用来做导入用的sql目标文件：
	    String fileAddress = "backup.sql";
	    fileAddress = this.getClass().getClassLoader().getResource("/").getPath() + fileAddress;
	    String localhost = "localhost";
	    String username = "root";
	    String password = "123456";
	    String database = "enddocdb";
		try{
			Runtime rt = Runtime.getRuntime();
			String command = "\"D:\\Program Files\\MySQL\\MySQL Server 5.5\\bin\\mysqldump\" -h" + localhost + " -u" + username + " -p" + password + " " + database;
			Process child = rt.exec(command);// 调用 调用mysql的安装目录的命令
			// 设置导出编码为utf-8。这里必须是utf-8
			// 把进程执行中的控制台输出信息写入.sql文件，即生成了备份文件。注：如果不对控制台信息进行读出，则会导致进程堵塞无法运行
		    InputStream in = child.getInputStream();// 控制台的输出信息作为输入流
		    InputStreamReader inputStreamReader = new InputStreamReader(in, "utf-8");
		    // 设置输出流编码为utf-8。这里必须是utf-8，否则从流中读入的是乱码
		    String inStr;
		    StringBuffer sb = new StringBuffer("");
		    String outStr;
		    // 组合控制台输出信息字符串
		    BufferedReader br = new BufferedReader(inputStreamReader);
		    while ((inStr = br.readLine()) != null) {
		    	sb.append(inStr + "\r\n");
		    }
		    outStr = sb.toString();
		    FileOutputStream fout = new FileOutputStream(fileAddress);
		    OutputStreamWriter writer = new OutputStreamWriter(fout, "utf-8");
		    writer.write(outStr);
		    writer.flush();
		    in.close();
		    inputStreamReader.close();
		    br.close();
		    writer.close();
		    fout.close();
		    System.out.println("");
		} catch (IOException e) {
			e.printStackTrace();
		}
		return fileAddress;
	}
 
	public void recoveryData() throws IOException { 
		String fileAddress = "backup.sql";
	    fileAddress = this.getClass().getClassLoader().getResource("/").getPath() + fileAddress;
	    String localhost = "localhost";
	    String username = "root";
	    String password = "123456";
	    String database = "test2";
	    
	    try {        
	        Runtime rt = Runtime.getRuntime();
	        // 调用 mysql 的 cmd:
	        String command = "\"D:\\Program Files\\MySQL\\MySQL Server 5.5\\bin\\mysql\" -h" + localhost + " -u" + username + " -p" + password + " " + database;
	        Process child = rt.exec(command);      
	        OutputStream out = child.getOutputStream();//控制台的输入信息作为输出流      
	        String inStr;      
	        StringBuffer sb = new StringBuffer("");      
	        String outStr;      
	        BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(fileAddress), "utf8"));      
	        while ((inStr = br.readLine()) != null) {      
	            sb.append(inStr + "\r\n");      
	        }      
	        outStr = sb.toString();      
	 
	        OutputStreamWriter writer = new OutputStreamWriter(out, "utf8");      
	        writer.write(outStr);      
	        // 注：这里如果用缓冲方式写入文件的话，会导致中文乱码，用flush()方法则可以避免      
	        writer.flush();      
	        // 别忘记关闭输入输出流      
	        out.close();
	        writer.close();
	        br.close();
	    } catch (IOException e) {      
	        e.printStackTrace();      
	    }
	} 
	
}
