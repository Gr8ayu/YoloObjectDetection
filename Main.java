
import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.*;

import static java.lang.Thread.sleep;


public class Main {
    JButton open ,viewRaw , detect, viewFinal;
    String path = "/home/gr8ayu/Desktop/courses/rvce/4sem/OOPS/partB/";
    String rawImage =path + "dataset.png";
    String finalImage = path + "objectdetection.jpg";
    JLabel imageLabel;
    JLabel logs ;
    JFrame f;
    JPanel panel = new JPanel();

    Main() throws IOException {
        f= new JFrame("Panel Example");
        f = new JFrame();
        imageLabel = new JLabel();
        imageLabel.setBounds(20,0,500,400);
        f.add(imageLabel);
        JTextArea ta=new JTextArea(300,30);
        ta.setBounds(20,20,300,30);
        f.add(ta);
        open = new JButton("Open File");
        open.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {

                if(actionEvent.getSource()==open){
                    JFileChooser fc=new JFileChooser();
                    int i=fc.showOpenDialog(open);
                    if(i==JFileChooser.APPROVE_OPTION){
                        File f=fc.getSelectedFile();
                        String filepath=f.getPath();
                        rawImage = filepath;
                        try{
                            ta.setText(filepath);
                            displayImage(filepath);
                        }catch (Exception ex) {ex.printStackTrace();  }
                    }
                }
                viewFinal.setEnabled(false);
            }
        });

        open.setBounds(350,20,100,30);
        f.add(open);


        panel.setBounds(40,80,400,450);
        panel.setBackground(Color.gray);
        f.add(panel);
        logs = new JLabel("<html>");
        panel.add(logs);

        viewRaw = new JButton("View Image");
        viewRaw.setBounds(5,600,150,30);
        viewRaw.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                BufferedImage img = null;
                try {
                    img = ImageIO.read(new File(rawImage));
                } catch (IOException e) {
                    e.printStackTrace();
                }
                ImageIcon icon = new ImageIcon(img);
                JOptionPane.showMessageDialog(null, icon);
            }
        });
        f.add(viewRaw);

        detect = new JButton("DETECT");
        detect.setBounds(180,600,100,30);
        detect.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
               
                logs.setText("<html> Process Started, Please Wait..."+"<br>");

                try {
//                    logs.setText(logs.getText()+"python3 "+path + "yolo.py " + rawImage  +"<br>");
                    System.out.println(logs.getText()+"python3 "+path + "yolo.py " + rawImage  );
                    Process p = Runtime.getRuntime().exec("python3 "+path + "yolo.py " + rawImage  );
                    BufferedReader in = new BufferedReader(new InputStreamReader(p.getInputStream()));
                    String ret = new String();
                    while ((ret  = in.readLine()) != null ){
                        System.out.println(ret);
                        logs.setText(logs.getText()+ret+"<br>");
                    }



                } catch (IOException e) {
                    e.printStackTrace();
                }
//                for (int i = 0; i < 10; i++) {
//                        logs.setText(logs.getText()+i+"<br>");
//                }
                viewFinal.setEnabled(true);
            }
        });
        f.add(detect);

        viewFinal = new JButton("View Analysis");
        viewFinal.setBounds(300,600,150,30);
        viewFinal.setEnabled(false);
        viewFinal.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                BufferedImage img = null;
                try {
                    img = ImageIO.read(new File(finalImage));
                } catch (IOException e) {
                    e.printStackTrace();
                }
                ImageIcon icon = new ImageIcon(img);
                JOptionPane.showMessageDialog(null, icon);
            }
        });
        f.add(viewFinal);





        f.setSize(500,700);
        f.setLayout(null);
        f.setVisible(true);
        //displayImage(rawImage);
    }

    void displayImage(String imgUrl) throws IOException {
        BufferedImage img = ImageIO.read(new File(imgUrl));
        ImageIcon icon = new ImageIcon(img);
//        imageLabel = new JLabel(icon);
        //imageLabel.setIcon(icon);
        JOptionPane.showMessageDialog(null, icon);
        f.add(imageLabel);

    }



    public static void main(String args[]) throws IOException {
        new Main();
    }
}


