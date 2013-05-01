package statistics;

import statistics.matcher.*;

public class Main {
    public static void main(String[] args) {
        Statistics stats = new Statistics(new PlayerReaderImpl("http://nhlstatistics.herokuapp.com/players.txt"));
        
        QueryBuilder query = new QueryBuilder();
        
        Matcher m = query.all(
                    query.all(query.hasAtLeast(5, "goals"),
                    query.hasAtLeast(10, "assists"),
                    query.playsIn("PHI")),
                    
                    query.not(query.playsIn("EDM"),
                    query.hasFewerThan(20, "points")));
        
        for (Player player : stats.matches(m)) {
            System.out.println( player );
        }
        
    }
}
