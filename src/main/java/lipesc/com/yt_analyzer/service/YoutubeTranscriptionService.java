package lipesc.com.yt_analyzer.service;

import java.io.IOException;
import java.security.GeneralSecurityException;

import org.springframework.stereotype.Service;

import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.json.gson.GsonFactory;
import com.google.api.services.youtube.YouTube;
import com.google.api.services.youtube.model.Caption;
import com.google.api.services.youtube.model.CaptionListResponse;

import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class YoutubeTranscriptionService {

  private YouTube youtubeService;

  @PostConstruct
  public void init() throws GeneralSecurityException, IOException {

    // servico do yt google api
    youtubeService = new YouTube.Builder(
        GoogleNetHttpTransport.newTrustedTransport(),
        GsonFactory.getDefaultInstance(),
        null).build();
  }

  public String extractTranscription(String videoUrl) {

    // pegar o id da url
    String videoId = extractVideoId(videoUrl);

    try {

      // legendas
      CaptionListResponse captions = youtubeService.captions()
          .list("snippet", videoId)
          .execute();

      // baixar transcrição
      if (!captions.getItems().isEmpty()) {
        String captionId = captions.getItems().get(0).getId();
        Caption caption = youtubeService.captions()
            .download(captionId)
            .execute();

        return caption.getSnippet().getContent();
      }

    } catch (Exception e) {
      log.error("*** Erro ao extrair transcrição", e);
    }
    return "";
  }

  private String extractVideoId(String videoUrls) {
    return videoUrls.split("v=")[1].split("&")[0];
  }

}
