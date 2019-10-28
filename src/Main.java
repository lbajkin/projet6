import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.effect.ColorAdjust;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Main extends Application {

    public void start(Stage primaryStage) {
        primaryStage.setTitle("Laboratoire 6");
        primaryStage.setScene(scene());
        primaryStage.setMaximized(true);
        primaryStage.setResizable(true);
        primaryStage.show();
    }

    private Scene scene() {
        // Image
        ImageView imageView;
        Image image0 = new Image("file:default.jpg");
        Image image1 = new Image("file:image1.jpg");
        Image image2 = new Image("file:image2.jpg");
        Image image3 = new Image("file:image3.jpg");
        imageView = new ImageView(image0);
        imageView.setFitWidth(450);
        imageView.setFitHeight(325);

        // Bottom Label
        Label label = new Label("Bienvenue dans le modificateur d'images!");

        // MenuBar
        BorderPane root = new BorderPane();

        Menu fichiers = new Menu("Fichiers");
        Menu charger = new Menu("Charger une image");
        Menu actions = new Menu("Actions");

        MenuItem i1 = new MenuItem("Image #1");
        MenuItem i2 = new MenuItem("Image #2");
        MenuItem i3 = new MenuItem("Image #3");
        MenuItem reinitialiser = new MenuItem("Réinitialiser");

        fichiers.getItems().addAll(charger);
        charger.getItems().addAll(i1, i2, i3);
        actions.getItems().addAll(reinitialiser);

        MenuBar menuBar = new MenuBar(fichiers, actions);

        i1.setOnAction(event -> {
            imageView.setImage(image1);
            label.setText("Image 1 chargée");
        });
        i2.setOnAction(event -> {
            imageView.setImage(image2);
            label.setText("Image 2 chargée");
        });
        i3.setOnAction(event -> {
            imageView.setImage(image3);
            label.setText("Image 3 chargée");
        });

        // Sliders
        Label label1 = new Label("Luminosité");
        Label label2 = new Label("Contraste");
        Label label3 = new Label("Teinte");
        Label label4 = new Label("Saturation");

        Tooltip tool1 = new Tooltip("Rend l'image plus claire ou plus sombre");
        Tooltip tool2 = new Tooltip("Diminue ou augmente la différence entre les couleurs");
        Tooltip tool3 = new Tooltip("Change la teinte (couleur) de l'image");
        Tooltip tool4 = new Tooltip("Diminue ou augmente l'intensité des couleurs");

        Slider lum = new Slider(-1, 1, 0);
        lum.setTooltip(tool1);
        Slider con = new Slider(-1, 1, 0);
        con.setTooltip(tool2);
        Slider tei = new Slider(-1, 1, 0);
        tei.setTooltip(tool3);
        Slider sat = new Slider(-1, 1, 0);
        sat.setTooltip(tool4);

        VBox vBox = new VBox(label1, lum, label2, con, label3, tei, label4, sat);
        vBox.setAlignment(Pos.CENTER);
        vBox.setSpacing(10);

        ColorAdjust colorAdjust = new ColorAdjust();

        HBox hBox = new HBox(imageView, vBox);
        hBox.setAlignment(Pos.CENTER);
        hBox.setSpacing(35);

        lum.setOnMouseDragged(event -> colorAdjust.setBrightness(lum.getValue()));
        con.setOnMouseDragged(event -> colorAdjust.setContrast(con.getValue()));
        tei.setOnMouseDragged(event -> colorAdjust.setHue(tei.getValue()));
        sat.setOnMouseDragged(event -> colorAdjust.setSaturation(sat.getValue()));

        imageView.setEffect(colorAdjust);

        reinitialiser.setOnAction(event -> {
            colorAdjust.setBrightness(0);
            colorAdjust.setContrast(0);
            colorAdjust.setHue(0);
            colorAdjust.setSaturation(0);
            lum.setValue(0);
            con.setValue(0);
            tei.setValue(0);
            sat.setValue(0);
            label.setText("Réinitialisation des ajustements de couleurs");
        });

        root.setCenter(hBox);
        root.setTop(menuBar);
        root.setBottom(label);

        return new Scene(root);
    }

}
