package Models;

public class Post {
    int id;
    String title;
    String content;
    Models.User user;

    public Post(int id, String title, String content, Models.User user) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.user = user;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Models.User getUser() {
        return user;
    }

    public void setUser(Models.User user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "Post [id=" + id + ", title=" + title + ", content=" + content + ", user=" + user + "]";
    }

}
