package chapter14;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class QuizCardBuilder {
    private JTextArea question;
    private JTextArea answer;
    private ArrayList<QuizCard> quizCardList;
    private JFrame frame;
    private JPanel mainPanel;
    private JButton nextButton;
    private JMenuBar menuBar;
    private JMenu fileMenu;
    private JMenuItem newMenuItem;
    private JMenuItem saveMenuItem;
    private JLabel answerLabel;
    private JLabel questionLabel;

    public static void main() {
        QuizCardBuilder gui = new QuizCardBuilder();
        gui.createGui();
    }

    private void createGui() {
        // Create Instances
        frame = new JFrame("Quiz Card Creation Wizard");
        mainPanel = new JPanel();
        Font largeFont = new Font("sanserif", Font.BOLD, 24);
        question = new JTextArea(6, 20);
        answer = new JTextArea(6, 20);
        JScrollPane questionScrollPane = new JScrollPane(question);
        JScrollPane answerScrollPane = new JScrollPane(answer);
        nextButton = new JButton("Next Card");
        quizCardList = new ArrayList<QuizCard>();
        answerLabel = new JLabel("Answer");
        questionLabel = new JLabel("Question");

        menuBar = new JMenuBar();
        fileMenu = new JMenu("File");
        newMenuItem = new JMenuItem("New");
        saveMenuItem = new JMenuItem("Save");


        // Set up the question and answer text areas
        question.setLineWrap(true);
        question.setWrapStyleWord(true);
        question.setFont(largeFont);

        answer.setLineWrap(true);
        answer.setWrapStyleWord(true);
        answer.setFont(largeFont);
        // Set Scroller Defaults
        questionScrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        questionScrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        answerScrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        answerScrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        // Register Listeners
        nextButton.addActionListener(new NextCardListener());
        newMenuItem.addActionListener(new NewMenuListener());
        saveMenuItem.addActionListener(new SaveMenuListener());

        // Add Save and New Menu Items to the file menu and them to the menu bar
        fileMenu.add(newMenuItem);
        fileMenu.add(saveMenuItem);

        menuBar.add(fileMenu);


        // Add widgets to the main panel
        mainPanel.add(questionLabel);
        mainPanel.add(questionScrollPane);
        mainPanel.add(answerLabel);
        mainPanel.add(answerScrollPane);
        mainPanel.add(nextButton);

        // Add widgets to the frame
        frame.setJMenuBar(menuBar);
        frame.getContentPane().add(BorderLayout.CENTER, mainPanel);
        // Set frame defaults
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(500, 600);
        frame.setVisible(true);

    }

    private class NextCardListener implements ActionListener {

        public void actionPerformed(ActionEvent e) {
            // add the current card to the list and clear the text areas
            QuizCard quizCard = new QuizCard(question.getText(), answer.getText());
            if (!quizCard.getAnswer().equals("") && !quizCard.getQuestion().equals("")) {
                // add quiz card to list if is not blank
                quizCardList.add(quizCard);
            }
            clearCard();
        }
    }

    private class SaveMenuListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            // Add current question and answer box to the quiz card list
            // bring up a file dialog box
            // let the user name and save the file
            System.out.println("Question: " + question.getText());
            System.out.println("Answer: " + answer.getText());
            QuizCard quizCard = new QuizCard(question.getText(), answer.getText());
            if (!quizCard.getAnswer().equals("") && !quizCard.getQuestion().equals("")) {
                // add quiz card to list if is not blank
                quizCardList.add(quizCard);
            }
            File wkdir = new File(System.getProperty("user.dir"));
            System.out.println(wkdir);
            JFileChooser fileSave = new JFileChooser();
            fileSave.setCurrentDirectory(wkdir);
            fileSave.showSaveDialog(frame);
            saveFile(fileSave.getSelectedFile());
        }
    }



    private class NewMenuListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            // Clear out the card list, and clear out the text areas
            quizCardList.clear();
            clearCard();
        }
    }

    private void clearCard() {
        question.setText("");
        answer.setText("");
        question.requestFocus();
    }

    private void saveFile(File file) {
        // iterate through the list of cards, and write each one out to a text file
        // in a parseable way

        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(file, true));
            for (QuizCard quizCard : quizCardList) {
                /*System.out.println("Question: " + quizCard.getQuestion());
                System.out.println("Answer: " + quizCard.getAnswer());*/
                writer.write(quizCard.getQuestion() + "/");
                writer.write(quizCard.getAnswer() + "\n");
            }
            writer.close();
        } catch (IOException ex) {
            System.out.println("Couldn't write file to " + file);
            ex.printStackTrace();
        }
    }
}
