module hi.reiknirit.straetoutlit {
    requires javafx.controls;
    requires javafx.fxml;
            
        requires org.controlsfx.controls;
                        requires org.kordamp.bootstrapfx.core;
            
    opens hi.reiknirit.straetoutlit to javafx.fxml;
    exports hi.reiknirit.straetoutlit;
}