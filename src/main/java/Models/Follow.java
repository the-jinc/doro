package Models;

public class Follow {
    int user;
    int follows;

    public Follow(int user, int follows) {
        this.user = user;
        this.follows = follows;
    }

    public int getUser() {
        return user;
    }

    public void setUser(int user) {
        this.user = user;
    }

    public int getFollows() {
        return follows;
    }

    public void setFollows(int follows) {
        this.follows = follows;
    }

    @Override
    public String toString() {
        return "Follow [user=" + user + ", follows=" + follows + "]";
    }

}
