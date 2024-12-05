package lipesc.com.yt_analyzer.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lipesc.com.yt_analyzer.model.VideoAnalysis;
import lipesc.com.yt_analyzer.service.TextAnalysisService;
import lipesc.com.yt_analyzer.service.YoutubeTranscriptionService;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/videos")
@RequiredArgsConstructor
public class VideoAnalysisController {
  private final YoutubeTranscriptionService transcriptionService;
  private final TextAnalysisService analysisService;

  public ResponseEntity<VideoAnalysis> analyzeVideo(@RequestBody String videoURL) {
    String transcription = transcriptionService.extractTranscription(videoURL);
    VideoAnalysis analysis = analysisService.analyzeTranscription(transcription);
    return ResponseEntity.ok(analysis);
  }
}
