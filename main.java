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

        selectFileButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                selectFile();
            }
        });

        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                scheduleFileDeletion();
            }
        });
        add(l1);
        add(l2);
        add(l3);
        add(l4);
        add(filePathField);
        add(selectFileButton);
        add(dateSpinner);
        add(timeSpinner);
        add(deleteButton);
        setVisible(true);
    }

    private void selectFile() {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES); // Allow selection of files and folders
        int result = fileChooser.showOpenDialog(this);
        if (result == JFileChooser.APPROVE_OPTION) {
            File selectedFile = fileChooser.getSelectedFile();
            filePathField.setText(selectedFile.getAbsolutePath());
        }
    }

    private void scheduleFileDeletion() {
        String filePath = filePathField.getText();
        Date deletionDateTime = getSelectedDeletionDateTime();

        if (filePath.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please select a file or folder.");
            return;
        }

        if (deletionDateTime == null) {
            JOptionPane.showMessageDialog(this, "Please select a valid deletion date and time.");
            return;
        }

        Date currentDateTime = new Date();
        if (deletionDateTime.before(currentDateTime)) {
            JOptionPane.showMessageDialog(this, "Selected deletion date and time should be in the future.");
            return;
        }

        if (fileDeletionTimer != null) {
            fileDeletionTimer.cancel();
        }

        fileDeletionTimer = new Timer();
        fileDeletionTimer.schedule(new TimerTask() {
            @Override
            public void run() {
                deleteFile(filePath);
                fileDeletionTimer.cancel();
            }
        }, deletionDateTime);
        JOptionPane.showMessageDialog(this, "File or folder deletion scheduled successfully.");
    }

    private Date getSelectedDeletionDateTime() {
        Date selectedDate = (Date) dateSpinner.getValue();
        Date selectedTime = (Date) timeSpinner.getValue();

        if (selectedDate == null || selectedTime == null) {
            return null;
        }

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(selectedDate);

        Calendar timeCalendar = Calendar.getInstance();
        timeCalendar.setTime(selectedTime);

        calendar.set(Calendar.HOUR_OF_DAY, timeCalendar.get(Calendar.HOUR_OF_DAY));
        calendar.set(Calendar.MINUTE, timeCalendar.get(Calendar.MINUTE));
        calendar.set(Calendar.SECOND, timeCalendar.get(Calendar.SECOND));

        return calendar.getTime();
    }

    private void deleteFile(String filePath) {
        File file = new File(filePath);
        if (file.exists()) {
            if (file.isDirectory()) {
                deleteDirectory(file);
            } else {
                if (file.delete()) {
                    JOptionPane.showMessageDialog(this, "File deleted successfully: " + filePath);
                } else {
                    JOptionPane.showMessageDialog(this, "Failed to delete file: " + filePath);
                }
            }
        } else {
            JOptionPane.showMessageDialog(this, "File or directory does not exist: " + filePath);
        }
    }

    private void deleteDirectory(File directory) {
        File[] files = directory.listFiles();
        if (files != null) {
            for (File file : files) {
                if (file.isDirectory()) {
                    deleteDirectory(file);
                } else {
                    if (file.delete()) {
                        System.out.println("Deleted file: " + file.getAbsolutePath());
                    } else {
                        System.out.println("Failed to delete file: " + file.getAbsolutePath());
                    }
                }
            }
        }

        if (directory.delete()) {
            System.out.println("Deleted directory: " + directory.getAbsolutePath());
        } else {
            System.out.println("Failed to delete directory: " + directory.getAbsolutePath());
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new FileDeletionApp();
            }
        });
    }
}
