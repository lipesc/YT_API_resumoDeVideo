package lipesc.com.yt_analyzer.config;

import java.io.IOException;
import java.security.GeneralSecurityException;

import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.json.gson.GsonFactory;
import com.google.api.client.util.Value;
import com.google.api.services.youtube.YouTube;

public class YoutubeConfig {

  @Value("${youtube.api.key}")
  private String apiKey;

  public YouTube youtubeService() throws GeneralSecurityException, IOException {
    return new YouTube.Builder(GoogleNetHttpTransport.newTrustedTransport(), GsonFactory.getDefaultInstance(),
        request -> {
        }).setApplicationName("lipesc_ytan").build();
  }
}
