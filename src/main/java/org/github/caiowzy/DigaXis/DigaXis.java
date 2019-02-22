package org.github.caiowzy.DigaXis;

import com.github.sarxos.webcam.Webcam;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import java.io.File;
import java.io.IOException;
import java.util.Timer;
import java.util.TimerTask;
import javafx.embed.swing.SwingFXUtils;
import javax.imageio.ImageIO;


public class DigaXis extends Application {

    private final Webcam webcam = Webcam.getDefault();
    private ImageView imageView = new ImageView();
    Timer timer = new Timer();

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage janelaPrincipal) {
        this.atualizaImagem();
        janelaPrincipal.setTitle("Diga Xis");
        janelaPrincipal.setResizable(false);

        janelaPrincipal.setOnCloseRequest(actionEvent -> {
            timer.cancel();
            webcam.close();
            Platform.exit();
            System.exit(0);
        });

        Scene scene = new Scene(new VBox(), 500, 560);

        imageView.setFitHeight(500);
        imageView.setFitWidth(500);

        Region spacer = new Region();
        spacer.setPrefHeight(5);

        Button fotografarBtn = new Button();
        fotografarBtn.setText("Fotografar");
        fotografarBtn.setPrefHeight(50);
        fotografarBtn.setOnAction(actionEvent -> {
            Image image = imageView.getImage();
            FileChooser fileChooser = new FileChooser();
            fileChooser.setTitle("Salvar imagem");
            File file = fileChooser.showSaveDialog(janelaPrincipal);
            if (file != null) {
                try {
                    ImageIO.write(SwingFXUtils.fromFXImage(image,
                            null), "png", file);
                } catch (IOException ex) {
                    System.out.println(ex.getMessage());
                }
            }
        });

        HBox hbox = new HBox();
        hbox.setAlignment(Pos.CENTER);
        hbox.getChildren().add(fotografarBtn);

        ((VBox) scene.getRoot()).getChildren().addAll(imageView, spacer, hbox);

        janelaPrincipal.setScene(scene);
        janelaPrincipal.show();

    }

    private void atualizaImagem() {
        webcam.open();
        TimerTask refresh = new TimerTask() {
            public void run() {
                Image image = SwingFXUtils.toFXImage(webcam.getImage(), null);
                imageView.setImage(image);
            }
        };
        timer.scheduleAtFixedRate(refresh, 0, 30L);
    }
}
