package reduction;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class MainClass {
	
	public static void main(String[] args) {
		BufferedReader reader = null;
		int m = 0, n = 0, k = 0;
		ArrayList<Edge> E = new ArrayList<>();
		
		try {
		    File file = new File("test.in");
		    reader = new BufferedReader(new FileReader(file));
		    
		    String line = reader.readLine();
		    String[] strings = line.trim().split("\\s+");
			n = Integer.parseInt(strings[0]);
			m = Integer.parseInt(strings[1]);
			k = Integer.parseInt(strings[2]);
			
			int u, v;
			
			for (int i = 0; i < m; i++) {
				line = reader.readLine();
			    strings = line.trim().split("\\s+");
				u = Integer.parseInt(strings[0]);
				v = Integer.parseInt(strings[1]);
				E.add(new Edge(u, v));
			}

		} catch (IOException e) {
		    e.printStackTrace();
		} finally {
		    try {
		        reader.close();
		    } catch (IOException e) {
		        e.printStackTrace();
		    }
		}
		
		
		String cond1 = "", cond2 = "", cond3 = "";
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < k; j++) {
				if (k != 1 && j == 0)
					cond1 += "(x" + (i * k + j);
				else if (k != 1 && j == k - 1)
					cond1 += "x" + (i * k + j) + ")";
				else
					cond1 += "x" + (i * k + j);
				if (j != k - 1)
					cond1 += "V";
			}
			if (i != n - 1)
				cond1 += "^";
		}
		for (int i = 0; i < n; i++) {
			for (int j1 = 0; j1 < k; j1++) {
				for (int j2 = j1 + 1; j2 < k; j2++) {
					cond2 += "(~x" + (i * k + j1) + "V~x" + (i * k + j2) + ")";
					if (i != n - 1 || j1 != k - 2 || j2 != k - 1)
						cond2 += "^";
				}
			}
		}
		for (Edge e: E) {
			for (int j = 0; j < k; j++) {
				cond3 += "(~x" + (e.u * k + j) + "V~x" + (e.v * k + j) + ")";
				if (e != E.get(m - 1) || j != k - 1)
					cond3 += "^";
			}
		}
		
		
		BufferedWriter writer = null;

		try {
			writer = new BufferedWriter(new FileWriter(new File("test.out")));
			
			writer.write(cond1);
			if (!cond2.equals(""))
				writer.write("^" + cond2);
			if (!cond3.equals(""))
				writer.write("^" + cond3);
			
		} catch(IOException e) {
			e.printStackTrace();
		} finally {
			try {
				writer.close();
			} catch(IOException e) {
				e.printStackTrace();
			}
		}
	}
	
}
