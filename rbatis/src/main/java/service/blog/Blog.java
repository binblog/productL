package service.blog;

/**
 * Created by bin.liang on 2016/12/1.
 */
public class Blog {
    private String title;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public String toString() {
        return "Blog{" +
                "title='" + title + '\'' +
                '}';
    }
}
