package chapter14;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.stream.Stream;

public class QuizCardPlayer {

    private JTextArea question;
    private JTextArea answer;
    private JScrollPane questionScrollPane;
    private JScrollPane answerScrollPane;
    private JFrame frame;
    private JPanel mainPanel;
    private JButton nextButton;
    private JButton answerButton;
    private JMenuBar menuBar;
    private JMenu fileMenu;
    private JMenuItem openMenuItem;
    private ArrayList<QuizCard> quizCardList;
    private int quizCardListIndex;
    private QuizCard currentQuizCard;

    public static void main() {
        QuizCardPlayer gui = new QuizCardPlayer();
        gui.createGui();
    }


    private void createGui() {
        // instantiate instance variables
        frame = new JFrame("Quiz Card App");
        mainPanel = new JPanel();
        nextButton = new JButton("Next Card");
        answerButton = new JButton("Show Answer");
        question = new JTextArea(6, 20);
        answer = new JTextArea(6, 20);
        questionScrollPane = new JScrollPane(question);
        answerScrollPane = new JScrollPane(answer);
        menuBar = new JMenuBar();
        fileMenu = new JMenu("File");
        openMenuItem = new JMenuItem("Open File");
        quizCardList = new ArrayList<QuizCard>();
        quizCardListIndex = 0;

        // configure the text area
        Font largeFont = new Font("sanserif", Font.BOLD, 24);
        question.setFont(largeFont);
        question.setLineWrap(true);
        question.setWrapStyleWord(true);
        answer.setFont(largeFont);
        answer.setLineWrap(true);
        answer.setWrapStyleWord(true);
        // configure the scrollers
        answerScrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        answerScrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        answerScrollPane.setVisible(false); // set answer pane to be invisible
        questionScrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        questionScrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        // register listeners
        openMenuItem.addActionListener(new OpenMenuListener());
        nextButton.addActionListener(new NextCardListener());
        answerButton.addActionListener(new ShowAnswerListener());
        // Add widgets to the main panel
        mainPanel.add(new JLabel("Question"));
        mainPanel.add(questionScrollPane);
        mainPanel.add(new JLabel("Answer"));
        mainPanel.add(answerScrollPane);
        mainPanel.add(answerButton);
        mainPanel.add(nextButton);
        // Add menu items to the menu, add menu to the menu bar
        fileMenu.add(openMenuItem);
        menuBar.add(fileMenu);
        // Add menu and main panel to the frame
        frame.setJMenuBar(menuBar);
        frame.getContentPane().add(BorderLayout.CENTER, mainPanel);
        // configure frame
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(500, 600);
        frame.setVisible(true);
    }

    private class OpenMenuListener implements ActionListener {

        public void actionPerformed(ActionEvent e) {
          // bring up the dialog box
          // let the user navigate and choose the file they want to open
            File wkdir = new File(System.getProperty("user.dir"));
            JFileChooser fileOpen = new JFileChooser();
            fileOpen.setCurrentDirectory(wkdir);
            fileOpen.showOpenDialog(frame);
            if (fileOpen.getSelectedFile() != null) {
                openFile(fileOpen.getSelectedFile());
            }
        }
    }
    private class NextCardListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            displayNextCard();
        }
    }
    private class ShowAnswerListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            displayAnswer();
        }
    }

    private void openFile(File file) {
        // read text from file into an Array List of Cards
        // file is read in one line at a time
        // calls the make card method to create a QuizCard object
        // one line in a file holds the question + answer (seperated by a '/')
        // display the first card on the Ui
        try {
            BufferedReader reader = new BufferedReader(new FileReader(file));
            Stream<String> lines = reader.lines();
            lines.forEach(line->makeCard(line));
            lines.close();
           /* BufferedReader reader = new BufferedReader(new FileReader(file));
            String line = null;
            while (reader.readLine() != null) {
                makeCard(line);
            }*/
        } catch (IOException ex) {
            System.out.println("Could not open file: " + file);
            ex.printStackTrace();
        }
        displayNextCard();

    }


    private void makeCard(String line) {
        // takes a line from the text file
        // creates a reference to a QuizCard object
        // splits the line by the '/' character
        // instantiates a quiz card object
        // Adds quiz card object to the quiz card list
        String[] res = line.split("/");
        System.out.println("Question: " + res[0]);
        System.out.println("Answer: " + res[1]);
        QuizCard quizCard = new QuizCard(res[0], res[1]);
        quizCardList.add(quizCard);
    }

    private void displayNextCard() {
        // Get card from array
        // set question and answer text
        // increment array index
        if (quizCardListIndex == quizCardList.size()) {
            quizCardListIndex = 0;
        }
        QuizCard quizCard = quizCardList.get(quizCardListIndex);
        quizCardListIndex++;
        question.setText(quizCard.getQuestion());
        answer.setText(quizCard.getAnswer());
        answerScrollPane.setVisible(false);
        frame.getContentPane().validate();
        frame.getContentPane().repaint();
    }
    private void displayAnswer() {
        // If the answer scroller is invisible
        // then the answer scroller is set to visible
        if (answerScrollPane.isVisible() == false) {
            answerScrollPane.setVisible(true);
            frame.getContentPane().validate();
            frame.getContentPane().repaint();

        }
    }
}
