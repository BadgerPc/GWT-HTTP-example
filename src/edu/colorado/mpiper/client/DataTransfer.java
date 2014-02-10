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

  private static final String CXN_MSG = "Couldn't connect to server";

  /**
   * Performs a GET operation with the given URL.
   * 
   * @param tester
   * @param url
   */
  public static void get(final TestTemplate tester, final String url) {

    RequestBuilder builder =
        new RequestBuilder(RequestBuilder.GET, URL.encode(url));
    GWT.log(url);

    try {
      @SuppressWarnings("unused")
      Request request = builder.sendRequest(null, new RequestCallback() {

        @Override
        public void onResponseReceived(Request request, Response response) {
          if (Response.SC_OK == response.getStatusCode()) {
            String rtxt = response.getText();
            tester.setResponse(rtxt);
          } else {
            String msg =
                "The URL '" + url + "' cannot be accessed. "
                    + "Response code: " + response.getStatusCode();
            Window.alert(msg);
          }
        }

        @Override
        public void onError(Request request, Throwable exception) {
          Window.alert(CXN_MSG);
        }
      });

    } catch (RequestException e) {
      Window.alert("Failed to send the request: " + e.getMessage());
    }
  }

  /**
   * Performs a POST operation with the given URL.
   * 
   * @param tester
   * @param url
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
      //builder.setHeader("Content-Type", "application/json");
      Request request =
          builder.sendRequest(queryString, new RequestCallback() {

            @Override
            public void onResponseReceived(Request request, Response response) {

              if (Response.SC_OK == response.getStatusCode()) {
                String rtxt = response.getText();
                // tester.setResponse(rtxt);
                Window.alert(rtxt);
              } else {
                String msg =
                    "The URL '" + url + "' cannot be accessed. "
                        + "Response code: " + response.getStatusCode();
                Window.alert(msg);
              }
            }

            @Override
            public void onError(Request request, Throwable exception) {
              Window.alert(CXN_MSG);
            }
          });

    } catch (RequestException e) {
      Window.alert("Failed to send the request: " + e.getMessage());
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
   * A JSNI method for creating a String from a JavaScriptObject.
   * 
   * @see http://stackoverflow.com/questions/4872770/excluding-gwt-objectid-from-json-stringifyjso-in-devmode
   * @param jso a JavaScriptObject
   * @return a String representation of the JavaScriptObject
   */
  private final native static <T> String stringify(T jso) /*-{
		return JSON.stringify(jso, function(key, value) {
			if (key == '__gwt_ObjectId') {
				return;
			}
			return value;
		});
  }-*/;

  /**
   * A JSNI method for evaluating JSONs.
   * 
   * Note that this is a generic method. It returns a JavaScript object of the
   * type denoted by the type parameter T.
   * 
   * @see <a
   *      href="http://docs.oracle.com/javase/tutorial/extra/generics/methods.html">Generic
   *      Methods</a>
   * 
   * @param jsonStr a String that you trust
   * @return a JavaScriptObject that you can cast to an overlay type
   */
  private final native static <T> T parse(String jsonStr) /*-{
		return eval("(" + jsonStr + ")");
  }-*/;
}
