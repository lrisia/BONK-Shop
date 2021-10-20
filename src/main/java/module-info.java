module ku.cs {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;

    opens ku.cs to javafx.fxml;
    exports ku.cs;
    exports ku.cs.controllers;

    opens ku.cs.controllers to javafx.fxml;
    exports ku.cs.controllers.verify;
    opens ku.cs.controllers.verify to javafx.fxml;
    exports ku.cs.controllers.shop;
    opens ku.cs.controllers.shop to javafx.fxml;
    exports ku.cs.controllers.userdata;
    opens ku.cs.controllers.userdata to javafx.fxml;
    exports ku.cs.controllers.shopSetting;
    opens ku.cs.controllers.shopSetting to javafx.fxml;
}
