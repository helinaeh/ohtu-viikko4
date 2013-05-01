package statistics.matcher;

public class QueryBuilder  {
    
    private Matcher query;
    
    public QueryBuilder() {
    }
    
    public QueryBuilder(Matcher matcher) {
        query = matcher;
    }
    
    public Matcher playsIn(String team) {
        return new PlaysIn(team);
    }
    
    public Matcher hasAtLeast(int value, String category) {
        return new HasAtLeast(value, category);
    }
    
    public Matcher hasFewerThan(int value, String category) {
        return new HasFewerThan(value, category);
    }
    
    public Matcher all(Matcher... matchers) {
        return new And(matchers);
    }
    
    public Matcher oneOf(Matcher... matchers) {
        return new Or(matchers);
    }
    
    public Matcher not(Matcher... matchers) {
        return new Not(matchers);
    }
}
