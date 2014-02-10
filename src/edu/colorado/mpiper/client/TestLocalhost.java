package edu.colorado.mpiper.client;

import com.google.gwt.user.client.ui.HTML;

/**
 * Test for accessing a JSON file from localhost using HTTP GET.
 * 
 * @author Mark Piper (mark.piper@colorado.edu)
 */
public class TestLocalhost extends TestTemplate {

  public TestLocalhost() {

    String url = "http://localhost/data/waves.json";
    DataTransfer.get(this, url);

    header.setHTML(header.getHTML() + "Localhost");
    grid.setWidget(0, 1, new HTML(url));
    grid.setWidget(1, 1, new HTML());
  }
}
