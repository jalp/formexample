package es.javi.formexample.domain;

import java.util.List;

public class Container {
    private String subject;
    private String body;
    private List<String> file;

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

    @Override
    public String toString() {
        return "Container{" +
                "subject='" + subject + '\'' +
                ", body='" + body + '\'' +
                ", file=" + file +
                '}';
    }
}
