import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.Calendar;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

public class FileDeletionApp extends JFrame {
    private JTextField filePathField;
    private JButton selectFileButton,deleteButton;
    private JSpinner dateSpinner,timeSpinner;
    private JLabel l1,l2,l3,l4;

    private Timer fileDeletionTimer;

    public FileDeletionApp() {
        setTitle("File Deletion App");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(500, 400, 900, 600);
        setLocationRelativeTo(null);
        getContentPane().setLayout(null);
        getContentPane().setBackground(Color.gray);

        l1 = new JLabel("We are happy to save your Time");
        l1.setFont(new Font("Arial", Font.BOLD, 40));
        l1.setSize(700, 50);
        l1.setLocation(480, 50);

        l2 = new JLabel("File Path: ");
        l2.setFont(new Font("Arial", Font.BOLD, 20));
        l2.setLocation(540, 300);
        l2.setSize(250, 20);

        l3= new JLabel("  Deletion Date: ");
        l3.setFont(new Font("Arial", Font.BOLD, 20));
        l3.setLocation(510, 350);
        l3.setSize(250, 20);

        l4=new JLabel("  Deletion Time: ");
        l4.setFont(new Font("Arial", Font.BOLD, 20));
        l4.setLocation(510, 400);
        l4.setSize(250, 20);



        filePathField = new JTextField(20);
        filePathField.setFont(new Font("Arial", Font.BOLD, 20));
        filePathField.setLocation(680, 300);
        filePathField.setSize(300, 20);

        selectFileButton = new JButton("Select");
        selectFileButton.setFont(new Font("Arial", Font.BOLD, 20));
        selectFileButton.setLocation(1010, 300);
        selectFileButton.setSize(100, 20);

        dateSpinner = new JSpinner(new SpinnerDateModel());
        dateSpinner.setFont(new Font("Arial", Font.BOLD, 20));
        dateSpinner.setLocation(680, 350);
        dateSpinner.setSize(300, 20);

        timeSpinner = new JSpinner(new SpinnerDateModel());
        timeSpinner.setFont(new Font("Arial", Font.BOLD,20));
        timeSpinner.setLocation(680, 400);
        timeSpinner.setSize(300, 20);

        deleteButton = new JButton("Delete");
        deleteButton.setFont(new Font("Arial", Font.BOLD, 20));
        deleteButton.setLocation(680, 500);
        deleteButton.setSize(100, 20);

        JSpinner.DateEditor dateEditor = new JSpinner.DateEditor(dateSpinner, "MM/dd/yyyy");
        dateSpinner.setEditor(dateEditor);

        JSpinner.DateEditor timeEditor = new JSpinner.DateEditor(timeSpinner, "HH:mm:ss");
        timeSpinner.setEditor(timeEditor);
