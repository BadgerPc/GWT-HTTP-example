package edu.colorado.mpiper.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.VerticalPanel;

/**
 * Practice using GWT RequestBuilder to perform HTTP GET and PUT tasks.
 * 
 * @author Mark Piper (mark.piper@colorado.edu)
 */
public class WebGetAndPut implements EntryPoint {

  public void onModuleLoad() {

    VerticalPanel contents = new VerticalPanel();
    RootPanel.get().add(contents);
    
    HTML title = new HTML("<h1>HTTP GET and PUT practice</h1>");
    contents.add(title);
    
    // The tests.
    //contents.add(new TestLocalhost());
    contents.add(new TestGetModelRiver());  
    contents.add(new TestPostModelRiver());
  }
}
