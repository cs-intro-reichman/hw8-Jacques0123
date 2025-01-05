public class User {
    static int maxfCount = 10;

    private String name;
    private String[] follows;
    private int fCount;

    public User(String name) {
        this.name = name;
        follows = new String[maxfCount];
        fCount = 0;
    }

    public User(String name, boolean gettingStarted) {
        this(name);
        if (gettingStarted) {
            follows[0] = "Foo";
            follows[1] = "Bar";
            follows[2] = "Baz";
            fCount = 3;
        }
    }

    public String getName() {
        return name;
    }

    public String[] getFollows() {
        return follows;
    }

    public int getfCount() {
        return fCount;
    }

    public boolean follows(String name) {
        for (int i = 0; i < fCount; i++) {
            if (follows[i].equalsIgnoreCase(name)) {
                return true;
            }
        }
        return false;
    }

    public boolean addFollowee(String name) {
        if (follows(name) || fCount >= maxfCount) {
            return false;
        }
        follows[fCount++] = name;
        return true;
    }

    public boolean removeFollowee(String name) {
        for (int i = 0; i < fCount; i++) {
            if (follows[i].equalsIgnoreCase(name)) {
                for (int j = i; j < fCount - 1; j++) {
                    follows[j] = follows[j + 1];
                }
                follows[--fCount] = null;
                return true;
            }
        }
        return false;
    }

    public int countMutual(User other) {
        int count = 0;
        for (int i = 0; i < fCount; i++) {
            if (other.follows(follows[i])) {
                count++;
            }
        }
        return count;
    }

    public boolean isFriendOf(User other) {
        return this.follows(other.getName()) && other.follows(this.getName());
    }

    public String toString() {
        StringBuilder result = new StringBuilder(name + " -> ");
        for (int i = 0; i < fCount; i++) {
            result.append(follows[i]).append(" ");
        }
        return result.toString().trim();
    }
}
