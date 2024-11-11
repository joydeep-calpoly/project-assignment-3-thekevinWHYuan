package main.Project3;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.JsonParseException;

import java.util.Date;

public class Article {
    private Source source;

    /**
     * This getter returns the source of the article.
     * @return the content source of the article.
     */
    public Source getSource() {
        return source;
    }

    /**
     * This getter returns the author of the article.
     * @return the content author of the article.
     */
    public String getAuthor() {
        return author;
    }

    /**
     * This getter returns the article title
     * @return the content article title
     */
    public String getTitle() {
        return title;
    }

    /**
     * This getter returns the article description
     * @return the description field of the article.
     */
    public String getDescription() {
        return description;
    }

    /**
     * This getter returns the url of the article.
     * @return the content url of the article.
     */
    public String getUrl() {
        return url;
    }
    /**
     * This getter returns the urltoImage of the article.
     * @return the urltoImage field of the article.
     */
    public String getUrlToImage() {
        return urlToImage;
    }

    /**
     * This getter returns the content of the article.
     * @return the content field of the article.
     */
    public String getContent() {
        return content;
    }

    /**
     * This getter returns the date published for this article.
     * This getter first checks to see if the date even exists. If it does, it returns the date otherwise
     * it returns a null
     * @return the date published for this article
     */
    public Date getPublishedAt() {
        if (publishedAt != null) {
            return (Date)publishedAt.clone();
        }
        return null;
    }

    private String author, title, description, url, urlToImage, content;
    private Date publishedAt;

    @JsonCreator
    private Article(@JsonProperty("source") Source source, @JsonProperty("author") String author, @JsonProperty("title") String title, @JsonProperty("description") String description,
                    @JsonProperty("url") String url, @JsonProperty("urlToImage") String urlToImage, @JsonProperty("content") String content, @JsonProperty("publishedAt") Date publishedAt){
            this.source = source;
            this.author = author;
            this.title = title;
            this.description = description;
            this.url = url;
            this.urlToImage = urlToImage;
            this.content = content;
            if (publishedAt != null){
                this.publishedAt = (Date) publishedAt.clone();
            }
    }

    private static class Source{
        String getId() {
            return id;
        }

        String getName() {
            return name;
        }

        private String id, name;

        @JsonCreator
        Source(@JsonProperty("id") String id, @JsonProperty("name") String name){
            this.id = id;
            this.name = name;
        }
    }

    /**
     * Returns whether two articles are the same.
     * The equal method compares the article's title, description, url, and what date it was published to another article.
     * @param obj   The other article that is to be compared to the caller article object.
     * @return      true to indicate that the two articles are the same or false
     */
    @Override
    public boolean equals(Object obj) {
        Article a = (Article) obj;
        return a.getTitle().equals(this.title) && a.description.equals(this.description) && a.getUrl().equals(this.url) && a.getPublishedAt().equals(this.publishedAt);
    }
}
