package foo.study.url.domain;

public class Url {

    private Long id;
    private String originDomain;
    private String shortDomain;

    public Url() {

    }

    public Url(String originDomain, String shortDomain) {
        this.originDomain = originDomain;
        this.shortDomain = shortDomain;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getOriginDomain() {
        return originDomain;
    }

    public void setOriginDomain(String originDomain) {
        this.originDomain = originDomain;
    }

    public String getShortDomain() {
        return shortDomain;
    }

    public void setShortDomain(String shortDomain) {
        this.shortDomain = shortDomain;
    }
}
