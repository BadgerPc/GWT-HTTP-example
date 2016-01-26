package edu.colorado.mpiper.client;

import com.google.gwt.user.client.ui.HTML;

/**
 * Test for accessing a JSON file using HTTP GET from river.colorado.edu.
 *  
 * @author Mark Piper (mark.piper@colorado.edu)
 */
public class TestGetModelRiver extends TestTemplate {

  public TestGetModelRiver() {

    // Models are accessed by index -- here, 18. Use "show" instead of "open"
    // to access model information because formatting.
    //String url = "http://csdms.colorado.edu/wmt/models/open/18";
    String url = "https://csdms.colorado.edu/wmt/api-dev/models/show/18";
    DataTransfer.get(this, url);

    header.setHTML(header.getHTML() + "GET model from river");
    grid.setWidget(0, 1, new HTML(url));
    grid.setWidget(1, 1, new HTML());
  }
}
