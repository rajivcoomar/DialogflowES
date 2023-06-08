package test;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import com.google.api.gax.rpc.ApiException;
import com.google.cloud.dialogflow.v2.DetectIntentResponse;
import com.google.cloud.dialogflow.v2.QueryInput;
import com.google.cloud.dialogflow.v2.QueryResult;
import com.google.cloud.dialogflow.v2.SessionName;
import com.google.cloud.dialogflow.v2.SessionsClient;
import com.google.cloud.dialogflow.v2.TextInput;
import com.google.common.collect.Maps;

public class DetectIntentTexts {
  public static Map<String, QueryResult> detectIntentTexts(String projectId, List<String> texts, int sessionId, String languageCode, int throttleLimit) throws IOException, ApiException {
    Map<String, QueryResult> queryResults = Maps.newHashMap();
    try {
      SessionsClient sessionsClient = SessionsClient.create();
      try {
        for (int i = 0; i < texts.size(); i++) {
          if ((i + 1) % throttleLimit == 0) {
            System.out.println("Waiting 60s to avoid request limit... Currently up to prompt: " + i + 1);
            TimeUnit.SECONDS.sleep(65L);
          } 
          String text = texts.get(i);
          SessionName session = SessionName.of(projectId, String.valueOf(i + sessionId));
          
          TextInput.Builder textInput = TextInput.newBuilder().setText(text).setLanguageCode(languageCode);
          QueryInput queryInput = QueryInput.newBuilder().setText(textInput).build();
          DetectIntentResponse response = sessionsClient.detectIntent(session, queryInput);
          QueryResult queryResult = response.getQueryResult();
          
          queryResults.put(text, queryResult);
        } 
        if (sessionsClient != null)
          sessionsClient.close(); 
      } catch (Throwable throwable) {
        if (sessionsClient != null)
          try {
            sessionsClient.close();
          } catch (Throwable throwable1) {
            throwable.addSuppressed(throwable1);
          }  
        throw throwable;
      } 
    } catch (InterruptedException e) {
      throw new RuntimeException(e);
    } 
    return queryResults;
  }
}
