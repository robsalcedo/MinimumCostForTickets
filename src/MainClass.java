import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;

class Solution {
    public int mincostTickets(int[] days, int[] costs) {
        int n = days.length;
        int lastDay = days[n-1];
        int[] dp = new int[lastDay+1];

        Set<Integer> daysSet = new HashSet<>();
        for(int day : days){
            daysSet.add(day);
        }

        for(int i=1; i<dp.length; i++){
            if(!daysSet.contains(i)){
                dp[i] = dp[i-1];
            }else{
                dp[i] = Math.min(dp[i-1]+costs[0],
                        Math.min(dp[Math.max(i-7,0)] + costs[1],
                                dp[Math.max(i-30,0)] + costs[2]));
            }
        }
        return dp[lastDay];
    }
}

public class MainClass {
    public static int[] stringToIntegerArray(String input) {
        input = input.trim();
        input = input.substring(1, input.length() - 1);
        if (input.length() == 0) {
            return new int[0];
        }

        String[] parts = input.split(",");
        int[] output = new int[parts.length];
        for(int index = 0; index < parts.length; index++) {
            String part = parts[index].trim();
            output[index] = Integer.parseInt(part);
        }
        return output;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        String line;
        while ((line = in.readLine()) != null) {
            int[] days = stringToIntegerArray(line);
            line = in.readLine();
            int[] costs = stringToIntegerArray(line);

            int ret = new Solution().mincostTickets(days, costs);

            String out = String.valueOf(ret);

            System.out.print(out);
        }
    }
}
