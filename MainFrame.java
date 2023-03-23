import com.sun.tools.javac.Main;

import javax.swing.*;
import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.logging.FileHandler;

public class MainFrame extends JFrame{
    private JTextField nameInput;
    private JRadioButton anoRadioButton;
    private JRadioButton neRadioButton;
    private JButton saveButton;
    private JButton showButton;
    private JTextField nameOutput;
    private JPanel mainPanel;

    private JFileChooser chooser = new JFileChooser(".");
    File textFile;
    ArrayList<Names> listJmen = new ArrayList<>();

    public MainFrame(){
        setContentPane(mainPanel);
        setTitle("Test projekt");
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        nameOutput.setEnabled(false);
ButtonGroup btnGroup = new ButtonGroup();
btnGroup.add(anoRadioButton);
        btnGroup.add(neRadioButton);
        saveButton.addActionListener(e-> {
            try {
                write();
            } catch (Error ex) {
                throw new RuntimeException(ex);
            }
        });
        showButton.addActionListener(e->{
        try{
            read();
        } catch (Error ex) {
            throw new RuntimeException(ex);
        }
        });
    }

    public static void main(String[] args) {
        MainFrame mainframe= new MainFrame();
        mainframe.setSize(450,150);

        JMenuBar menuBar = new JMenuBar();
        JMenu menu = new JMenu("File");
        menuBar.add(menu);
        JMenuItem item=new JMenuItem("Open");
        menu.add(item);
        item.addActionListener(e->mainframe.setFile());
        mainframe.setJMenuBar(menuBar);
    }
    String line = "";
    boolean isIt;
    void read() throws Error{
        try(Scanner sc = new Scanner(new BufferedReader(new FileReader(textFile)));) {
        while (sc.hasNextLine()){
            line = sc.nextLine();
            String[] parametry = line.split(":");
            if(parametry[1].equals("ano")){
                isIt = true;
            }else {
                isIt = false;
            }
            listJmen.add(new Names(parametry[0],isIt));
        }

        }catch (FileNotFoundException e){
            throw new Error("Nenalezen soubor");
        }finally {
            nameInput.setText(listJmen.get(0).getName());
            if(listJmen.get(0).isYesOrNo()){
                anoRadioButton.setSelected(true);
            }else {
                neRadioButton.setSelected(true);
            }
        }
    }

    void write() throws Error{
        try(PrintWriter writer = new PrintWriter(new BufferedWriter(new FileWriter(textFile)))){
            for (Names namess: listJmen) {
                String isItActually;
                if(anoRadioButton.isSelected()){
                    isItActually = "ano";
                }else {
                    isItActually = "ne";
                }
                writer.print(nameInput.getText() + ":" + isItActually + "\n");
                writer.flush();
            }

        }catch (IOException ex){
            throw new Error("IO Error");
        }
    }


    void setFile() {
        int result = chooser.showOpenDialog(this);
        if(result == JFileChooser.APPROVE_OPTION){
            textFile = chooser.getSelectedFile();
            nameOutput.setText(textFile.getName());
        }
    }
}
