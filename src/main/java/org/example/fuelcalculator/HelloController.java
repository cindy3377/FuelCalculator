package org.example.fuelcalculator;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.text.MessageFormat;
import java.util.Locale;
import java.util.ResourceBundle;

public class HelloController {

    @FXML private Label lblDistance;
    @FXML private Label lblFuel;
    @FXML private Label lblResult;
    @FXML private TextField txtDistance;
    @FXML private TextField txtFuel;
    @FXML private Button btnCalculate;

    private Locale currentLocale = new Locale("en");

    public void initialize() {
        setLanguage(new Locale("en", "US"));
    }

    @FXML
    protected void onCalculateClick() {
        try {
            double distance = Double.parseDouble(txtDistance.getText());
            double fuel = Double.parseDouble(txtFuel.getText());

            if (distance <= 0 || fuel <= 0) {
                lblResult.setText(getMessage("invalid.input"));
                return;
            }

            double consumption = (fuel / distance) * 100;
            String resultMessage = MessageFormat.format(getMessage("result.label"), String.format("%.2f", consumption));
            lblResult.setText(resultMessage);

        } catch (NumberFormatException e) {
            lblResult.setText(getMessage("invalid.input"));
        }
    }

    @FXML
    protected void onENClick() { setLanguage(new Locale("en", "US")); }

    @FXML
    protected void onFRClick() { setLanguage(new Locale("fr", "FR")); }

    @FXML
    protected void onJPClick() { setLanguage(new Locale("ja", "JP")); }

    @FXML
    protected void onIRClick() { setLanguage(new Locale("fa", "IR")); }

    private void setLanguage(Locale locale) {
        this.currentLocale = locale;
        ResourceBundle bundle = ResourceBundle.getBundle("messages", locale);

        lblDistance.setText(bundle.getString("distance.label"));
        lblFuel.setText(bundle.getString("fuel.label"));
        btnCalculate.setText(bundle.getString("calculate.button"));
        lblResult.setText(bundle.getString("result.label.default"));
    }

    private String getMessage(String key) {
        ResourceBundle bundle = ResourceBundle.getBundle("messages", currentLocale);
        return bundle.getString(key);
    }
}