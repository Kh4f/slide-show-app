package io.github.kh4f;

import javax.swing.*;
import java.awt.*;
import java.util.Objects;

public class App extends JFrame {

    private JPanel panelMain;
    private JLabel currImgLabel;
    private JButton nextBtn;
    private JLabel nextImgLabel;
    private JLabel prevImgLabel;
    private JLabel imgName;
    CircularLinkedList<ImageIcon> picturesList = new CircularLinkedList<>();
    CircularLinkedList.Node<ImageIcon> currImg;

    public App() throws CircularLinkedList.CircularLinkedListException {
        this.setTitle("Slide show app");
        this.setContentPane(panelMain);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        this.setLocation((screenSize.width - 500) / 2, (screenSize.height - 500) / 2);
        this.pack();

        picturesList.addLast(new ImageIcon(Objects.requireNonNull(getClass().getResource("/1.png"))));
        picturesList.addLast(new ImageIcon(Objects.requireNonNull(getClass().getResource("/2.png"))));
        picturesList.addLast(new ImageIcon(Objects.requireNonNull(getClass().getResource("/3.png"))));
        picturesList.addLast(new ImageIcon(Objects.requireNonNull(getClass().getResource("/4.png"))));
        picturesList.addLast(new ImageIcon(Objects.requireNonNull(getClass().getResource("/5.png"))));

        currImg = picturesList.getHead();
        prevImgLabel.setIcon(getScaledImage(currImg.getValue()));
        currImgLabel.setIcon(currImg.getNext().getValue());
        nextImgLabel.setIcon(getScaledImage(currImg.getNext().getNext().getValue()));

        nextBtn.addActionListener(actionEvent -> {
            try {
                movePictures();
            } catch (Exception ex) {
                System.err.println(ex.getMessage());
            }
        });

    }

    public void movePictures() {
        currImg = currImg.getNext();
        prevImgLabel.setIcon(getScaledImage(currImg.getValue()));
        currImgLabel.setIcon(currImg.getNext().getValue());
        nextImgLabel.setIcon(getScaledImage(currImg.getNext().getNext().getValue()));

        String description = currImg.getValue().getDescription();
        String imageName = description.substring(description.lastIndexOf("/") + 1, description.lastIndexOf("."));
        imgName.setText("Image " + imageName);
    }

    public ImageIcon getScaledImage(ImageIcon icon) {
        Image image = icon.getImage();
        Image scaledImage = image.getScaledInstance(150, 150, Image.SCALE_SMOOTH);
        return new ImageIcon(scaledImage);
    }

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            try {
                new App().setVisible(true);
            } catch (Exception ex) {
                System.err.println(ex.getMessage());
            }
        });
    }
}
