package Cryptology;

import Cryptology.cryptanalysis.BruteForce;
import Cryptology.cryptography.service.CipherService;
import Cryptology.cryptography.service.impl.CaesarCipher;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;

public class GUIMain extends Application {

    private TextField inputTextField;
    private TextField outputTextField;
    private ComboBox<String> dropdownList;
    private Stage primaryStage;

    @Override
    public void start(Stage primaryStage) {
        this.primaryStage = primaryStage;
        showWelcomeWindow();
    }

    private void showWelcomeWindow() {
        primaryStage.setTitle("Welcome");

        VBox root = new VBox(20);
        root.setPadding(new Insets(20));
        root.setAlignment(javafx.geometry.Pos.CENTER);

        Label label = new Label("Welcome to Caesar Cipher Cryptology");
        label.setStyle("-fx-font-size: 24px; -fx-font-weight: bold;");

        HBox buttons = new HBox(20);
        buttons.setAlignment(javafx.geometry.Pos.CENTER);
        buttons.setPadding(new Insets(20));

        Button encryptButton = new Button("Encrypt");
        encryptButton.setOnAction(e -> showEncryptWindow());

        Button decryptButton = new Button("Decrypt");
        decryptButton.setOnAction(e -> showDecryptWindow());

        Button bruteSearchButton = new Button("Brute Force");
        bruteSearchButton.setOnAction(e -> showBruteForceWindow());

        buttons.getChildren().addAll(encryptButton, decryptButton, bruteSearchButton);

        root.getChildren().addAll(label, buttons);

        primaryStage.setScene(new Scene(root, 600, 200));
        primaryStage.show();
    }

    private void showEncryptWindow() {
        Stage encryptStage = new Stage();
        encryptStage.setTitle("Encrypt");

        GridPane grid = createGridPane();

        addLabel(grid, "Enter Plain Text:", 0, 0);
        inputTextField = createTextField();
        grid.add(inputTextField, 1, 0);

        addLabel(grid, "Encrypted Text:", 0, 1);
        outputTextField = createTextField();
        outputTextField.setEditable(false);
        grid.add(outputTextField, 1, 1);

        addLabel(grid, "Key:", 0, 2);
        dropdownList = createComboBox();
        grid.add(dropdownList, 1, 2);

        Button encryptButton = createButton("Encrypt");
        encryptButton.setOnAction(e -> processCipher(true));
        grid.add(encryptButton, 1, 3);

        Scene scene = new Scene(grid, 400, 200);
        encryptStage.setScene(scene);
        encryptStage.show();
    }

    private void showDecryptWindow() {
        Stage decryptStage = new Stage();
        decryptStage.setTitle("Decrypt");

        GridPane grid = createGridPane();

        addLabel(grid, "Enter Encrypted Text:", 0, 0);
        inputTextField = createTextField();
        grid.add(inputTextField, 1, 0);

        addLabel(grid, "Decrypted Text:", 0, 1);
        outputTextField = createTextField();
        outputTextField.setEditable(false);
        grid.add(outputTextField, 1, 1);

        addLabel(grid, "Key:", 0, 2);
        dropdownList = createComboBox();
        grid.add(dropdownList, 1, 2);

        Button decryptButton = createButton("Decrypt");
        decryptButton.setOnAction(e -> processCipher(false));
        grid.add(decryptButton, 1, 3);

        Scene scene = new Scene(grid, 400, 200);
        decryptStage.setScene(scene);
        decryptStage.show();
    }

    private void showBruteForceWindow() {
        Stage bruteStage = new Stage();
        bruteStage.setTitle("Brute Force");

        GridPane grid = createGridPane();

        addLabel(grid, "Enter Encrypted Text:", 0, 0);
        inputTextField = createTextField();
        grid.add(inputTextField, 1, 0);

        addLabel(grid, "Decrypted Text:", 0, 1);
        outputTextField = createTextField();
        outputTextField.setEditable(false);
        grid.add(outputTextField, 1, 1);

        Button bruteSearchButton = createButton("Brute Search");
        bruteSearchButton.setOnAction(e -> bruteAttackSearch());
        grid.add(bruteSearchButton, 1, 3);

        Scene scene = new Scene(grid, 400, 200);
        bruteStage.setScene(scene);
        bruteStage.show();
    }

    private void processCipher(boolean isEncrypt) {
        String key = dropdownList.getValue();
        String text = inputTextField.getText();
        CipherService cipher = new CaesarCipher(Integer.parseInt(key));
        String result = isEncrypt ? cipher.encrypt(text) : cipher.decrypt(text);
        outputTextField.setText(result);
    }

    private void bruteAttackSearch() {
        String text = inputTextField.getText();
        String result = null;
        try {
            result = BruteForce.searchAttack(text);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        outputTextField.setText(result);
    }

    private GridPane createGridPane() {
        GridPane grid = new GridPane();
        grid.setPadding(new Insets(20));
        grid.setVgap(10);
        grid.setHgap(10);
        return grid;
    }

    private TextField createTextField() {
        TextField textField = new TextField();
        textField.setPrefWidth(200);
        return textField;
    }

    private ComboBox<String> createComboBox() {
        ComboBox<String> comboBox = new ComboBox<>();
        comboBox.getItems().addAll("1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25");
        comboBox.setValue("1");
        return comboBox;
    }

    private Button createButton(String text) {
        Button button = new Button(text);
        button.setPrefWidth(110);
        return button;
    }

    private void addLabel(GridPane gridPane, String text, int column, int row) {
        Label label = new Label(text);
        gridPane.add(label, column, row);
    }

    public static void main(String[] args) {
        launch(args);
    }
}
