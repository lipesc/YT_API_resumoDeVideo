package lipesc.com.yt_analyzer.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Chapter {
  private String title;
  private String timestamp;
  private String summary;
}
