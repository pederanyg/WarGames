module no.edu.ntnu.idatt2001.pederany.wargames {

    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.graphics;

    exports no.edu.ntnu.idatt2001.pederany.wargames.Controllers;
    opens no.edu.ntnu.idatt2001.pederany.wargames.Controllers to javafx.fxml;
    exports no.edu.ntnu.idatt2001.pederany.wargames.Units;
    opens no.edu.ntnu.idatt2001.pederany.wargames.Units to javafx.fxml;
    exports no.edu.ntnu.idatt2001.pederany.wargames.Battle;
    opens no.edu.ntnu.idatt2001.pederany.wargames.Battle to javafx.fxml;
    exports no.edu.ntnu.idatt2001.pederany.wargames;
    opens no.edu.ntnu.idatt2001.pederany.wargames to javafx.fxml;
}