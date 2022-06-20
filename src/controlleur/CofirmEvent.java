package controlleur;

import javafx.event.EventHandler;
import javafx.stage.WindowEvent;

@Deprecated
public class CofirmEvent implements EventHandler<WindowEvent> {
    
    public void handle(WindowEvent event) {

        event.consume();
    }
}
