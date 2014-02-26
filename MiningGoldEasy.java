import java.util.*;

public class MiningGoldEasy {
    public int GetMaximumGold(int N, int M, int[] ei, int[] ej) {
        ArrayList<int[]> q = new ArrayList<int[]>();
        ArrayList<Integer> v = new ArrayList<Integer>();
        int[] t=new int[]{-1,0,0},temp;
        int res=0,idx;
        int iday,value;
        int ret=Integer.MAX_VALUE;
        q.add(new int[]{0,ei[0],ej[0]});
        v.add(0);
        while(!q.isEmpty()){
            if(q.get(0)[0]!=t[0]){
                for(int i=0; i<q.size(); i++){
                    System.out.print(Arrays.toString(q.get(i)));
                    System.out.print(v.get(i));
                }
                System.out.println();
                //System.out.println(Arrays.toString(t));
            }
            t = q.remove(0);
            res = v.remove(0);
            iday = t[0];
            if(iday==(ei.length-1)){
                if(res<ret)
                    ret=res;
                continue;
            }

            temp =new int [] {iday+1,t[1],ej[iday+1]};
            value = res+Math.abs(t[1]-ei[iday+1]);
            if(q.contains(temp)){
                idx = q.indexOf(temp);
                if(v.get(idx)>value) v.set(idx,value);
            }else{
                q.add(temp);
                v.add(value);
            }
            temp =new int[]{iday+1,ei[iday+1],t[2]};
            value = res+Math.abs(t[2]-ej[iday+1]);
            if(q.contains(temp)){
                idx = q.indexOf(temp);
                if(v.get(idx)>value) v.set(idx,value);
            }else{
                q.add(temp);
                v.add(value);
            }
        }
        return (N+M)*ei.length-res;
    }
// BEGIN CUT HERE
/** begin cut - don't modify this line*/
	public static void main(String[] a) {
		new MiningGoldEasy().runTestCase(0);
		new MiningGoldEasy().runTestCase(1);
		new MiningGoldEasy().runTestCase(2);
		new MiningGoldEasy().runTestCase(3);
		new MiningGoldEasy().runTestCase(4);
		//new MiningGoldEasy().runTestCase(5);
	}

	public void runTestCase(int nbr) {
		switch(nbr) {
			case 0 : {
				checkOutput(GetMaximumGold(2, 2, new int[] {0}, new int[] {0}), 4, 0); break;
			}
			case 1 : {
				checkOutput(GetMaximumGold(2, 2, new int[] {0, 2}, new int[] {0, 1}), 7, 1); break;
			}
			case 2 : {
				checkOutput(GetMaximumGold(3, 3, new int[] {0, 3, 3}, new int[] {0, 3, 0}), 15, 2); break;
			}
			case 3 : {
				checkOutput(GetMaximumGold(3, 4, new int[] {0, 3}, new int[] {4, 1}), 11, 3); break;
			}
			case 4 : {
				checkOutput(GetMaximumGold(5, 6, new int[] {0, 4, 2, 5, 0}, new int[] {3, 4, 5, 6, 0}), 48, 4); break;
			}
			case 5 : {
				checkOutput(GetMaximumGold(557, 919, new int[] {81, 509, 428, 6, 448, 149, 77, 142, 40, 405, 109, 247}, new int[] {653, 887, 770, 477, 53, 637, 201, 863, 576, 393, 512, 243}), 16255, 5); break;
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
