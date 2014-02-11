package edu.colorado.mpiper.client;

import java.util.HashMap;
import java.util.Map.Entry;

import com.google.gwt.core.client.GWT;
import com.google.gwt.http.client.Request;
import com.google.gwt.http.client.RequestBuilder;
import com.google.gwt.http.client.RequestCallback;
import com.google.gwt.http.client.RequestException;
import com.google.gwt.http.client.Response;
import com.google.gwt.http.client.URL;
import com.google.gwt.user.client.Window;

public class DataTransfer {

  private static final String ERR_MSG = "An error occurred: ";
  private static TestTemplate tester;
  private static String url;

  /**
   * Performs a GET operation with the given URL.
   * 
   * @param tester an instance of TestTemplate representing the current test
   * @param url the URL to GET data from
   */
  @SuppressWarnings("unused")
  public static void get(final TestTemplate tester, final String url) {

    DataTransfer.tester = tester;
    DataTransfer.url = url;

    RequestBuilder builder =
        new RequestBuilder(RequestBuilder.GET, URL.encode(url));
    GWT.log(url);

    try {
      Request request =
          builder.sendRequest(null, new GetModelRequestCallback());
    } catch (RequestException e) {
      Window.alert(ERR_MSG + e.getMessage());
    }
  }

  /**
   * Performs a POST operation with the given URL.
   * 
   * @param tester an instance of TestTemplate representing the current test
   * @param url the URL to POST data to
   */
  @SuppressWarnings("unused")
  public static void post(final TestTemplate tester, final String url) {

    RequestBuilder builder =
        new RequestBuilder(RequestBuilder.POST, URL.encode(url));
    GWT.log(url);

    String queryString = buildQueryString(tester.makeEntries());
    GWT.log(queryString);

    try {
      builder.setHeader("Content-Type", "application/x-www-form-urlencoded");
      // builder.setHeader("Content-Type", "application/json");
      Request request =
          builder.sendRequest(queryString, new PostModelRequestCallback());
    } catch (RequestException e) {
      Window.alert(ERR_MSG + e.getMessage());
    }
  }

  /**
   * Builds a HTTP query string from a HashMap of entries.
   * 
   * @param entries a HashMap of key-value pairs
   * @return the query, as a String
   */
  public static String buildQueryString(HashMap<String, String> entries) {

    StringBuilder sb = new StringBuilder();

    Integer entryCount = 0;
    for (Entry<String, String> entry : entries.entrySet()) {

      if (entryCount > 0) {
        sb.append("&");
      }

      String encodedName = URL.encodeQueryString(entry.getKey());
      sb.append(encodedName);
      sb.append("=");
      String encodedValue = URL.encodeQueryString(entry.getValue());
      sb.append(encodedValue);

      entryCount++;
    }

    return sb.toString();
  }

  /**
   * Provides the callback for a GET request of a model.
   */
  public static class GetModelRequestCallback implements RequestCallback {

    @Override
    public void onResponseReceived(Request request, Response response) {
      if (Response.SC_OK == response.getStatusCode()) {
        String rtxt = response.getText();
        tester.setResponse(rtxt);
      } else {
        String msg =
            "The URL '" + url + "' did not give an 'OK' response. "
                + "Response code: " + response.getStatusCode();
        Window.alert(msg);
      }
    }

    @Override
    public void onError(Request request, Throwable exception) {
      Window.alert(ERR_MSG + exception.getMessage());
    }
  }

  /**
   * Provides the callback for a POST request of a model.
   */
  public static class PostModelRequestCallback implements RequestCallback {

    @Override
    public void onResponseReceived(Request request, Response response) {
      if (Response.SC_OK == response.getStatusCode()) {
        String rtxt = response.getText();
        // tester.setResponse(rtxt);
        Window.alert(rtxt);
      } else {
        String msg =
            "The URL '" + url + "' did not give an 'OK' response. "
                + "Response code: " + response.getStatusCode();
        Window.alert(msg);
      }
    }

    @Override
    public void onError(Request request, Throwable exception) {
      Window.alert(ERR_MSG + exception.getMessage());
    }
  }
}
