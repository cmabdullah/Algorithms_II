import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.FlowEdge;
import edu.princeton.cs.algs4.FlowNetwork;
import edu.princeton.cs.algs4.FordFulkerson;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class BaseballElimination {
    private final int n;
    private int[] wins;
    private int[] losses;
    private int[] remaining;
    private int[][] against;
    private Map<String, Integer> name2id;
    private Map<Integer, String> id2name;
    private FordFulkerson maxFlowSolver;
    private Set<String> teamSet;

    public BaseballElimination(String filename) {
        // create a baseball division from given filename in format specified below
        In input = new In(filename);

        n = Integer.parseInt(input.readLine());
        wins = new int[n];
        losses = new int[n];
        remaining = new int[n];
        against = new int[n][n];
        name2id = new HashMap<>();
        id2name = new HashMap<>();

        String[] lines = input.readAllLines();
        for (int i = 0; i < lines.length; i++) {
            String[] fields = lines[i].trim().split("\\s+", 5);

            name2id.put(fields[0], i);
            id2name.put(i, fields[0]);
            wins[i] = Integer.parseInt(fields[1]);
            losses[i] = Integer.parseInt(fields[2]);
            remaining[i] = Integer.parseInt(fields[3]);

            String[] competeNums = fields[4].split("\\s+");
            for (int j = 0; j < competeNums.length; j++)
                against[i][j] = Integer.parseInt(competeNums[j]);
        }
    }

    public int numberOfTeams() {
        return n;
    }

    public Iterable<String> teams() {
        return name2id.keySet();
    }

    public int wins(String team) {
        if (!name2id.containsKey(team)) throw new IllegalArgumentException();
        return wins[name2id.get(team)];
    }

    public int losses(String team) {
        if (!name2id.containsKey(team)) throw new IllegalArgumentException();
        return losses[name2id.get(team)];
    }

    public int remaining(String team) {
        if (!name2id.containsKey(team)) throw new IllegalArgumentException();
        return remaining[name2id.get(team)];
    }

    public int against(String team1, String team2) {
        // number of remaining games between team1 and team2
        if (!name2id.containsKey(team1)) throw new IllegalArgumentException();
        if (!name2id.containsKey(team2)) throw new IllegalArgumentException();
        return against[name2id.get(team1)][name2id.get(team2)];
    }

    public boolean isEliminated(String team) {
        // is given team eliminated?
        if (!name2id.containsKey(team)) throw new IllegalArgumentException();

        int x = name2id.get(team);
        teamSet = new HashSet<>();
        FlowNetwork network = constructFlowNetwork(x);

        int s = network.V() - 2;
        int t = network.V() - 1;
        maxFlowSolver = new FordFulkerson(network, s, t);

        for (FlowEdge e : network.adj(s))
            if (e.flow() < e.capacity())
                return true;
        return !teamSet.isEmpty();
    }

    private FlowNetwork constructFlowNetwork(int x) {
        int V = 2 + (n - 1) * (n - 2) / 2 + n;
        FlowNetwork network = new FlowNetwork(V);

        // let 0 to n - 1 be the team vertices(x will be isolated and unused),
        // V - 2 be s, and V - 1 be t, remaining be the game vertices
        int s = V - 2, t = V -1;
        for (int v = 0; v < n; v++) {
            if (v == x) continue;

            int capacity = wins[x] + remaining[x] - wins[v];
            if (capacity < 0)
                teamSet.add(id2name.get(v));
            FlowEdge e = new FlowEdge(v, t, Math.max(capacity, 0));
            network.addEdge(e);
        }

        int offset = 0;
        for (int i = 0; i < n; i++)
            for (int j = 0; j < n; j++) {
                if (i == x || j == x || i >= j) continue;

                int v = n + (offset++);
                network.addEdge(new FlowEdge(s, v, against[i][j]));
                network.addEdge(new FlowEdge(v, i, Double.POSITIVE_INFINITY));
                network.addEdge(new FlowEdge(v, j, Double.POSITIVE_INFINITY));
            }

        return network;
    }

    public Iterable<String> certificateOfElimination(String team) {
        // subset R of teams that eliminates given team; null if not eliminated
        if (!name2id.containsKey(team)) throw new IllegalArgumentException();
        int id = name2id.get(team);

        if (!isEliminated(team))
            return null;
        else {
            for (int v = 0; v < n; v++)
                if (v != id && maxFlowSolver.inCut(v))
                    teamSet.add(id2name.get(v));
            return teamSet;
        }
    }

    public static void main(String[] args) {
        BaseballElimination division = new BaseballElimination(args[0]);
        for (String team : division.teams()) {
            if (division.isEliminated(team)) {
                StdOut.print(team + " is eliminated by the subset R = { ");
                for (String t : division.certificateOfElimination(team)) {
                    StdOut.print(t + " ");
                }
                StdOut.println("}");
            }
            else {
                StdOut.println(team + " is not eliminated");
            }
        }
    }
}
