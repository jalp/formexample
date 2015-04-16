package es.javi.formexample.domain;

import java.util.List;

public class Container {
    private String subject;
    private String body;
    private String description;
    private Integer price;
    private List<String> file;

    @Override
    public String toString() {
        return "Container{" +
                "subject='" + subject + '\'' +
                ", body='" + body + '\'' +
                ", description='" + description + '\'' +
                ", price=" + price +
                ", file=" + file +
                '}';
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public List<String> getFile() {
        return file;
    }

    public void setFile(List<String> file) {
        this.file = file;
    }

}
