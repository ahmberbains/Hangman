import javax.swing.*;
import java.awt.*;

public class HangmanGraphics extends Canvas {

    JFrame frame;
    private int frame_width;
    private int frame_height = 600;

    int y_offset = 40;
    int head_size = 100;
    int body_length = 140;
    int arm_height = 50;
    int arm_width = 80;
    int leg_length = 20;
    int leg_height = 100;
    int leg_width = 80;
    int neck_length = 20;
    int blank_width = 65;
    int blank_height = 10;
    int blank_space_width = 30;
    int blank_y_offset = 125;
    int blank_left, blank_y, blank_word_width;
    Font stringFont;
    int fontSize = 48;

    public HangmanGraphics(){
        blank_word_width = Game.wordLength * blank_width +(Game.wordLength - 1 ) * blank_space_width;
        frame_width = (int) (blank_word_width*1.2);
        blank_left = (frame_width + blank_width + blank_space_width) /2 - (head_size + blank_word_width)/2;
        blank_y = y_offset + head_size + body_length + leg_height + blank_y_offset;
        frameInit();
        stringFont =  new Font ("SansSerif", Font.PLAIN, fontSize);
    }

    void frameInit(){
        frame = new JFrame();
        frame.add(this);
        frame.setSize(frame_width,frame_height);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }

    void update(){
        frame.remove(this);
        frame.add(this);
    }

    public void paint(Graphics g){
        drawPerson(g);
        drawHiddenWord(g);

    }

    public void drawPerson(Graphics g){
        int frame_middle = frame.getWidth()/2;
        int head_x = frame_middle - head_size/2;
        int head_y = y_offset;
        int body_y = head_y + head_size;
        int arm_y1 = body_y + neck_length;
        int leg_y1 = body_y + body_length;
        switch (Game.numGuesses){
            case 0:
                g.drawLine(frame_middle, leg_y1, frame_middle + leg_width, leg_y1 + leg_height);
            case 1:
                g.drawLine(frame_middle, leg_y1, frame_middle - leg_width, leg_y1 + leg_height);
            case 2:
                g.drawLine(frame_middle, arm_y1, frame_middle + arm_width, arm_y1 + arm_height);
            case 3:
                g.drawLine(frame_middle, arm_y1, frame_middle - arm_width, arm_y1 + arm_height);
            case 4:
                g.drawLine(frame_middle, body_y, frame_middle, body_y + body_length);
            case 5:
                g.drawOval(head_x, head_y, head_size, head_size);
            default:
                break;
        }

    }

    public void drawHiddenWord(Graphics g){
        g.setFont(stringFont);
        int x = blank_left;
        char c;
        for(int i=0; i < Game.wordLength; i++){
            g.fillRect(x,blank_y,blank_width,blank_height);
            c = Game.hiddenChars[i];
            if(c != '_'){
                g.drawString(Character.toString(c),x+blank_width/4,blank_y-10);

            }
            x += blank_width + blank_space_width;
        }
    }
}
