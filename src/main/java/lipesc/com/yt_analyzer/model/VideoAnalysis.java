package lipesc.com.yt_analyzer.model;

import java.util.List;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class VideoAnalysis {
  private String videoURL;
  private String fullTranscription;
  private List<String> mainTopics;
  private List<String> chapters;
}
