import java.util.*;

public class MiningGoldEasy {
    public int GetMaximumGold(int N, int M, int[] ei, int[] ej) {
        N++; M++;
        int D = ei.length;
        int[][] dp = new int[N+M][D];
        int temp;

        int d=0;
        System.out.format("day %d\n",d);
        for(int i=0; i<N; i++){
            dp[i][d]= Math.abs(i-ei[d]);
            System.out.format("%3d",dp[i][d]);
        }
        System.out.format("  |");
        for(int i=0; i<M; i++){
            dp[i+N][d] = Math.abs(i-ej[d]);
            System.out.format("%3d",dp[i+N][d]);
        }
        System.out.format("\n");
        for(d=1; d<D; d++){
            System.out.format("day %d\n",d);
            for(int i=0; i<N; i++){
                temp = Math.abs(i-ei[d]);
                dp[i][d]=(M+N)*d;
                if(i==ei[d-1]){
                    for(int k=0; k<M; k++){
                        if(dp[k+N][d-1]+temp<dp[i][d])
                            dp[i][d]=dp[k+N][d-1]+temp;
                        System.out.format("\n dp1[%d][%d]= %d\n",i,d,dp[i][d]);
                    }
                }else{
                    if(dp[ej[d]+N][d-1]+temp<dp[i][d])
                        dp[i][d]=dp[ej[d]+N][d-1]+temp;
                    System.out.format("\n dp2[%d][%d]= %d\n",i,d,dp[i][d]);
                }

                if(ej[d]==ej[d-1]){
                    for(int k=0; k<N; k++){
                        if(dp[k][d-1]+temp<dp[i][d])
                            dp[i][d]=dp[k][d-1]+temp;
                        System.out.format("\n dp3[%d][%d]= %d\n",i,d,dp[i][d]);
                    }
                }else{
                    if(dp[i][d-1]+temp<dp[i][d])
                        dp[i][d]=dp[i][d-1]+temp;
                    System.out.format("\n dp4[%d][%d]= %d\n",i,d,dp[i][d]);
                }

                System.out.format("%3d",dp[i][d]);
            }
            System.out.format("  |");
            for(int i=0; i<M; i++){
                temp = Math.abs(i-ej[d]);
                dp[i+N][d]=(N+M)*d;
                if(ei[d]==ei[d-1]){
                    for(int k=0; k<M; k++)
                        if(dp[k+N][d-1]+temp<dp[i+N][d])
                            dp[i+N][d]=dp[k+N][d-1]+temp;
                }else
                    if(dp[i+N][d-1]+temp<dp[i+N][d])
                        dp[i+N][d]=dp[i+N][d-1]+temp;

                if(i==ej[d-1]){
                    for(int k=0; k<N; k++)
                        if(dp[k][d-1]+temp<dp[i+N][d])
                            dp[i+N][d]=dp[k][d-1]+temp;
                }else
                    if(dp[ei[d]][d-1]+temp<dp[i+N][d])
                        dp[i+N][d]=dp[ei[d]][d-1]+temp;

                System.out.format("%3d",dp[i+N][d]);
            }
            System.out.format("\n");
        }
        temp = Integer.MAX_VALUE;
        for(int i=0; i<dp.length; i++)
            if(dp[i][D-1]<temp)
                temp=dp[i][D-1];
        return (N+M-2)*ei.length-temp;
    }

// BEGIN CUT HERE
/** begin cut - don't modify this line*/
	public static void main(String[] a) {
		new MiningGoldEasy().runTestCase(3);
		new MiningGoldEasy().runTestCase(0);
		new MiningGoldEasy().runTestCase(1);
		new MiningGoldEasy().runTestCase(2);
		new MiningGoldEasy().runTestCase(4);
	new MiningGoldEasy().runTestCase(5);
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
