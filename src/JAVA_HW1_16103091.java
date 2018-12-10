import java.util.*;
import java.io.*;

/*
 * Class to store the information of student 
 * and calculate total marks and grade.
 */
class Student {
	String id;
	int ex;
	int as[] = new int[10];
	int mi;
	int fin;
	int cl;

	public int assignmentSum() {
		int s, i;
		for (s = 0, i = 0; i < 10; i++)
			s += as[i];
		return s;
	}

	public int totalSum() {
		int s = assignmentSum(), ts = 0;
		ts += s + ex + mi + fin + cl;
		return ts;
	}

	public char grade(int p) {
		if (p >= 90)
			return 'A';
		else if (p >= 78)
			return 'B';
		else if (p >= 62)
			return 'C';
		else if (p >= 46)
			return 'D';
		else
			return 'F';
	}
}

public class JAVA_HW1_16103091 {
	public static void main(String args[]) throws IOException, FileNotFoundException {

		File filei = new File("datai.txt");
		File fileo = new File("datao.txt");

		Scanner sc = new Scanner(filei);
		Writer wr = new FileWriter(fileo);

		wr.write("StdentID  EX  ----------Assignments--------  TOT  MI  Fin  CL  PTS  PCT  GR\n");
		wr.write("--------  50  20 20 20 20 20 20 20 20 20 20  200  70  100  10  420  ---  --\n\n");

		String s = "";
		int pmax = 0, st = 0, avgper = 0;
		int g[] = new int[5];

		/*
		 * Calculate percentage and grade of each student
		 * and write it into the output file.
		 */
		while (sc.hasNextLine()) {
			Student obj = new Student();
			s = sc.nextLine();
			String[] tokens = s.split("\\s+");
			int c = 0;

			obj.id = tokens[c++];
			obj.ex = Integer.parseInt(tokens[c++]);
			for (int i = 0; c < 12; c++, i++)
				obj.as[i] = Integer.parseInt(tokens[c]);
			obj.mi = Integer.parseInt(tokens[c++]);
			obj.fin = Integer.parseInt(tokens[c++]);
			obj.cl = Integer.parseInt(tokens[c++]);

			int asum = obj.assignmentSum();
			int tsum = obj.totalSum();
			if (pmax < tsum)
				pmax = tsum;
			float p = (float) tsum / (float) (4.2);
			int per = Math.round(p);
			avgper += per;
			char gr = obj.grade(per);
			g[gr - 'A']++;
			st++;

			wr.write(obj.id + "  " + String.format("%2d", obj.ex) + "  ");
			for (int i = 0; i < 10; i++)
				wr.write(String.format("%2d", obj.as[i]) + " ");
			wr.write(" " + String.format("%3d", asum) + "  ");
			wr.write(String.format("%2d", obj.mi) + "  " + String.format("%3d", obj.fin) + "  ");
			wr.write(String.format("%2d", obj.cl) + "  " + String.format("%3d", tsum) + "  ");
			wr.write(String.format("%3d", per) + "  " + String.format("%2c", gr));
			wr.write("\n");
		}
		
		/*
		 * Write the summary report file of all the students
		 * into the output file.
		 */
		wr.write("\n\n");
		wr.write("Total Number of Students: " + st + "\n");
		wr.write("Average Total Point Percentage: " + avgper / st + "\n\n");
		wr.write("Number of A's: " + g[0] + "\n");
		wr.write("Number of B's: " + g[1] + "\n");
		wr.write("Number of C's: " + g[2] + "\n");
		wr.write("Number of D's: " + g[3] + "\n");
		wr.write("Number of F's: " + g[4] + "\n\n");
		wr.write("Maximum Points: " + pmax + "\n");
		sc.close();
		wr.close();

		/*
		 * Read the output file and print it to the console screen. 
		 */
		Scanner scc = new Scanner(fileo);
		scc.useDelimiter("\\Z");
		System.out.println(scc.next());
		scc.close();
	}

}