import java.util.*;

public class CliqueGraph {
    public long calcSum(int N, int[] V, int[] sizes) {
        Set<Integer> set = new HashSet<Integer>();
        for(int i = 0; i < V.length; i++)
            set.add(V[i]);
        Integer[] v = new Integer[set.size()];
        set.toArray(v);
        Arrays.sort(v);

        int[] S = new int[sizes.length+1];
        S[0]=0;
        for(int i = 0; i < sizes.length; i++)
            S[i+1]=S[i]+sizes[i];

        //System.out.println(Arrays.toString(V));
        //System.out.println(Arrays.toString(v));
        //System.out.println(Arrays.toString(S));

        Object[] cliq = new Object[sizes.length];
        for(int i = 0; i < sizes.length; i++){
            cliq[i] = new HashSet<Integer>();
            for(int j=S[i]; j<S[i+1]; j++)
                ((Set)cliq[i]).add(V[j]);
        }

        int[][] cliqMap = new int[sizes.length][sizes.length];
        Set temp;
        for(int i=0; i<sizes.length; i++){
            cliqMap[i][i]=0;
            for(int j=i+1; j<sizes.length; j++){
                temp = (Set)((HashSet)cliq[i]).clone();
                temp.retainAll((Set)cliq[j]);
                cliqMap[i][j]= temp.size()>0 ? 1 : 3000;
                cliqMap[j][i]=cliqMap[i][j];
            }
        }
        for(int k=0; k<sizes.length; k++)
            for(int i=0; i<sizes.length; i++)if(i!=k)
                for(int j=i+1; j<sizes.length; j++)if(j!=k && cliqMap[i][j]>1)
                    if(cliqMap[i][k]+cliqMap[k][j]<cliqMap[i][j]){
                        cliqMap[i][j] = cliqMap[i][k]+cliqMap[k][j];
                        cliqMap[j][i]=cliqMap[i][j];
                    }

        long ret = 0;
        int dist;
        Object[] record = new Object[N];
        for(int i=0; i< N; i++){
            record[i] = new HashSet<Integer>();
            for(int k=0; k<sizes.length; k++) if(((Set)cliq[k]).contains(i))
                ((Set)record[i]).add(k);
        }
        for(int i=0; i< N; i++)
            for(int j=i+1; j< N; j++){
                dist = Integer.MAX_VALUE;
                for(int k : (Set<Integer>)record[i])
                    for(int l : (Set<Integer>)record[j])
                        dist = Math.min(cliqMap[k][l],dist);
                ret+=dist+1;
            }

        return ret;

    }
// BEGIN CUT HERE
    void println(Object o) { System.out.println(o); }
    void print (Object o) {System.out.print(o); }
    void println() {System.out.println(); }
/** begin cut - don't modify this line*/
	public static void main(String[] a) {
		new CliqueGraph().runTestCase(0);
		new CliqueGraph().runTestCase(1);
		new CliqueGraph().runTestCase(2);
	}

	public void runTestCase(int nbr) {
		switch(nbr) {
			case 0 : {
				checkOutput(calcSum(4, new int[] {0,1,2,0,3}, new int[] {3,2}), 8, 0); break;
			}
			case 1 : {
				checkOutput(calcSum(5, new int[] {0,1,2,3,1,2,4}, new int[] {4,3}), 12, 1); break;
			}
			case 2 : {
				checkOutput(calcSum(15, new int[] {1,3,5,7,9,11,13,0 ,2,3,6,7,10,11,14,0 ,4,5,6,7,12,13,14,0 ,8,9,10,11,12,13,14,0}, new int[] {8,8,8,8}), 130, 2); break;
			}
		}
	}
	final void checkOutput(int mine, int them, int nbr) {
		boolean success = (mine==them);
		StringBuffer out = new StringBuffer();
		out.append("Example ");
		out.append((nbr+1));
		out.append(" - ");
		out.append(success ? "success" : "failure   ");
		if(!success) {
			out.append("Got: ");
			out.append(mine);
			out.append(", Expected: ");
			out.append(them);
		}
		System.out.println(out);
	}
	final void checkOutput(long mine, long them, int nbr) {
		boolean success = (mine==them);
		StringBuffer out = new StringBuffer();
		out.append("Example ");
		out.append((nbr+1));
		out.append(" - ");
		out.append(success ? "success" : "failure   ");
		if(!success) {
			out.append("Got: ");
			out.append(mine);
			out.append(", Expected: ");
			out.append(them);
		}
		System.out.println(out);
	}
	final void checkOutput(double mine, double them, int nbr) {
		boolean success = doubleCompare(mine, them);
		StringBuffer out = new StringBuffer();
		out.append("Example ");
		out.append((nbr+1));
		out.append(" - ");
		out.append(success ? "success" : "failure   ");
		if(!success) {
			out.append("Got: ");
			out.append(mine);
			out.append(", Expected: ");
			out.append(them);
		}
		System.out.println(out);
	}
	private static boolean doubleCompare(double expected, double result){
		double MAX_DOUBLE_ERROR = 1E-9;
		if(Double.isNaN(expected)){
			return Double.isNaN(result);
		}else if(Double.isInfinite(expected)){
			if(expected > 0){
				return result > 0 && Double.isInfinite(result);
			}else{
				return result < 0 && Double.isInfinite(result);
			}
		}else if(Double.isNaN(result) || Double.isInfinite(result)){
			return false;
		}else if(Math.abs(result - expected) < MAX_DOUBLE_ERROR){
			return true;
		}else{
			double min = Math.min(expected * (1.0 - MAX_DOUBLE_ERROR),
				expected * (1.0 + MAX_DOUBLE_ERROR));
			double max = Math.max(expected * (1.0 - MAX_DOUBLE_ERROR),
					expected * (1.0 + MAX_DOUBLE_ERROR));
			return result > min && result < max;
		}
	}
	final void checkOutput(char mine, char them, int nbr) {
		boolean success = (mine==them);
		StringBuffer out = new StringBuffer();
		out.append("Example ");
		out.append((nbr+1));
		out.append(" - ");
		out.append(success ? "success" : "failure   ");
		if(!success) {
			out.append("Got: ");
			out.append("'");
			out.append(mine);
			out.append("'");
			out.append(", Expected: ");
			out.append("'");
			out.append(them);
			out.append("'");
		}
		System.out.println(out);
	}
	final void checkOutput(String mine, String them, int nbr) {
		boolean success = (mine.equals(them));
		StringBuffer out = new StringBuffer();
		out.append("Example ");
		out.append((nbr+1));
		out.append(" - ");
		out.append(success ? "success" : "failure   ");
		if(!success) {
			out.append("Got: ");
			out.append("\"");
			out.append(mine);
			out.append("\"");
			out.append(", Expected: ");
			out.append("\"");
			out.append(them);
			out.append("\"");
		}
		System.out.println(out);
	}
	final void checkOutput(long[] mine, long[] them, int nbr) {
		boolean success = (Arrays.equals(mine, them));
		StringBuffer out = new StringBuffer();
		out.append("Example ");
		out.append((nbr+1));
		out.append(" - ");
		out.append(success ? "success" : "failure   ");
		if(!success) {
			out.append("Got: ");
			out.append("{");
			for(int x=0;x<mine.length;x++) {
				out.append(mine[x]);
				if(x<mine.length-1) out.append(", ");
			}
			out.append("}");
			out.append(", Expected: ");
			out.append("{");
			for(int x=0;x<them.length;x++) {
				out.append(them[x]);
				if(x<them.length-1) out.append(", ");
			}
			out.append("}");
		}
		System.out.println(out);
	}
	final void checkOutput(char[] mine, char[] them, int nbr) {
		boolean success = (Arrays.equals(mine, them));
		StringBuffer out = new StringBuffer();
		out.append("Example ");
		out.append((nbr+1));
		out.append(" - ");
		out.append(success ? "success" : "failure   ");
		if(!success) {
			out.append("Got: ");
			out.append("{");
			for(int x=0;x<mine.length;x++) {
				out.append(mine[x]);
				if(x<mine.length-1) out.append(", ");
			}
			out.append("}");
			out.append(", Expected: ");
			out.append("{");
			for(int x=0;x<them.length;x++) {
				out.append(them[x]);
				if(x<them.length-1) out.append(", ");
			}
			out.append("}");
		}
		System.out.println(out);
	}
	final void checkOutput(double[] mine, double[] them, int nbr) {
		boolean success = (Arrays.equals(mine, them));
		StringBuffer out = new StringBuffer();
		out.append("Example ");
		out.append((nbr+1));
		out.append(" - ");
		out.append(success ? "success" : "failure   ");
		if(!success) {
			out.append("Got: ");
			out.append("{");
			for(int x=0;x<mine.length;x++) {
				out.append(mine[x]);
				if(x<mine.length-1) out.append(", ");
			}
			out.append("}");
			out.append(", Expected: ");
			out.append("{");
			for(int x=0;x<them.length;x++) {
				out.append(them[x]);
				if(x<them.length-1) out.append(", ");
			}
			out.append("}");
		}
		System.out.println(out);
	}
	final void checkOutput(int[] mine, int[] them, int nbr) {
		boolean success = (Arrays.equals(mine, them));
		StringBuffer out = new StringBuffer();
		out.append("Example ");
		out.append((nbr+1));
		out.append(" - ");
		out.append(success ? "success" : "failure   ");
		if(!success) {
			out.append("Got: ");
			out.append("{");
			for(int x=0;x<mine.length;x++) {
				out.append(mine[x]);
				if(x<mine.length-1) out.append(", ");
			}
			out.append("}");
			out.append(", Expected: ");
			out.append("{");
			for(int x=0;x<them.length;x++) {
				out.append(them[x]);
				if(x<them.length-1) out.append(", ");
			}
			out.append("}");
		}
		System.out.println(out);
	}
	final void checkOutput(String[] mine, String[] them, int nbr) {
		boolean success = (Arrays.equals(mine, them));
		StringBuffer out = new StringBuffer();
		out.append("Example ");
		out.append((nbr+1));
		out.append(" - ");
		out.append(success ? "success" : "failure   ");
		if(!success) {
			out.append("Got: ");
			out.append("{");
			for(int x=0;x<mine.length;x++) {
				out.append(mine[x]);
				if(x<mine.length-1) out.append(", ");
			}
			out.append("}");
			out.append(", Expected: ");
			out.append("{");
			for(int x=0;x<them.length;x++) {
				out.append(them[x]);
				if(x<them.length-1) out.append(", ");
			}
			out.append("}");
		}
		System.out.println(out);
	}

/** end cut - don't modify this line*/
// END CUT HERE
}
