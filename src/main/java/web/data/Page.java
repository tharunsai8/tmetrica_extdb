package web.data;

/**
 * The type Page.
 */
public class Page {
    private String url;
    private boolean redirect;

    /**
     * Instantiates a new Page.
     *
     * @param url      the url
     * @param redirect the redirect
     */
    public Page(String url, boolean redirect) {
        this.url = url;
        this.redirect = redirect;
    }

    /**
     * Instantiates a new Page.
     *
     * @param url the url
     */
    public Page(String url) {
        this.url = url;
    }

    /**
     * Instantiates a new Page.
     */
    public Page() {
    }

    /**
     * Gets url.
     *
     * @return the url
     */
    public String getUrl() {
        return url;
    }

    /**
     * Sets url.
     *
     * @param url the url
     */
    public void setUrl(String url) {
        this.url = url;
    }

    /**
     * Is redirect boolean.
     *
     * @return the boolean
     */
    public boolean isRedirect() {
        return redirect;
    }

    /**
     * Sets redirect.
     *
     * @param redirect the redirect
     */
    public void setRedirect(boolean redirect) {
        this.redirect = redirect;
    }
}