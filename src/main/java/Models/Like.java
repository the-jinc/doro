package Models;

public class Like {
    int user;
    int post;

    public Like(int user, int post) {
        this.user = user;
        this.post = post;
    }

    public int getUser() {
        return user;
    }

    public void setUser(int user) {
        this.user = user;
    }

    public int getPost() {
        return post;
    }

    public void setPost(int post) {
        this.post = post;
    }

    @Override
    public String toString() {
        return "Like [user=" + user + ", post=" + post + "]";
    }

}
