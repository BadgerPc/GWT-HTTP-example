package edu.colorado.mpiper.client;

import com.google.gwt.user.client.ui.HTML;

/**
 * Test for POSTing a JSON file to river.colorado.edu.
 *  
 * @author Mark Piper (mark.piper@colorado.edu)
 */
public class TestPostModelRiver extends TestTemplate {

  public TestPostModelRiver() {

    Integer r = (int) (Math.random() * 100);
    
    modelName = "GWT POST Test";
    modelJSON = "{\"model\":" + r.toString() + "}";
    
    String url = "https://csdms.colorado.edu/wmt/models/new";
    DataTransfer.post(this, url);

    header.setHTML(header.getHTML() + "POST model to river");
    grid.setWidget(0, 1, new HTML(url));
    grid.setWidget(1, 1, new HTML());
  }
}
