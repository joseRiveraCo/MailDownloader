package mailDownloader.beta;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;

@PropertySource("classpath:application.properties")
public class Propiedades {

    @Value("${pathdownload}")
    private String pathdownload;

    @Value("${Host}")
    private String Host;

    @Value("${user}")
    private String user;

    @Value("${pass}")
    private String pass;

    @Value("${folder}")
    private String folder;

    public String getPathdownload() {
        return this.pathdownload;
    }

    public String getHost() {
        return this.Host;
    }

    public String getUser() {
        return this.user;
    }

    public String getPassword() {
        return pass;
    }

    public String getFolder() {
        return this.folder;
    }
}
