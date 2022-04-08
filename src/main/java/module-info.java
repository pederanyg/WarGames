module no.edu.ntnu.idatt2001.pederany.wargames {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.graphics;


    opens no.edu.ntnu.idatt2001.pederany.wargames to javafx.fxml;
    exports no.edu.ntnu.idatt2001.pederany.wargames;
}