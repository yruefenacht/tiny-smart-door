package ch.bfh.my.tinysmartdoorapp.service;

import android.content.Context;
import android.content.res.Resources;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import ch.bfh.my.tinysmartdoorapp.R;
import ch.bfh.my.tinysmartdoorapp.model.Credentials;

public class PropertiesService {

    private Context context;
    private Properties properties;

    public PropertiesService(Context context) {
        this.context = context;
        this.loadProperties();
    }

    public Credentials getCredentials() {
        return new Credentials(this.properties.getProperty("username"), this.properties.getProperty("password"));
    }

    public String getURL() {
        return this.properties.getProperty("mqtt_url") + ":" + this.properties.getProperty("mqtt_port");
    }

    private void loadProperties() {
        try {
            Resources resources = this.context.getResources();
            InputStream rawProperties = resources.openRawResource(R.raw.credentials);
            this.properties = new Properties();
            this.properties.load(rawProperties);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
