package edu.colorado.mpiper.client;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.ui.HTML;

/**
 * Test for accessing a JSON file in DevMode using HTTP GET.
 * 
 * @author Mark Piper (mark.piper@colorado.edu)
 */
public class TestGetModelDevMode extends TestTemplate {

  public TestGetModelDevMode() {

    String url = GWT.getHostPageBaseURL() + "data/waves.json";
    DataTransfer.get(this, url);

    header.setHTML(header.getHTML() + "DevMode");
    grid.setWidget(0, 1, new HTML(url));
    grid.setWidget(1, 1, new HTML());
  }
}
