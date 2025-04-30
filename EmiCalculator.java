import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

class EmiCalculator extends JFrame implements ActionListener {
    JRadioButton rb1, rb2, rb3;
    JButton b;
    JLabel title, subtitle, p, r, t, ans, tp, inte, note, note1, note2, note3, note4;
    JTextField ptext, rtext, ttext, anstext, tptext, intetext;

    EmiCalculator() {
        setTitle("EMI Calculator");
        setSize(800, 1000);
        setLayout(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);  // Properly closes the app
        setVisible(true);

        Font myFont = new Font("Serif", Font.BOLD, 50);
        Font myFont1 = new Font("Serif", Font.BOLD, 20);
        Font myFont2 = new Font("Serif", Font.BOLD, 25);
        Font myFont3 = new Font("Serif", Font.BOLD, 15);

        title = new JLabel("EMI Calculator");
        title.setBounds(200, 15, 400, 100);
        title.setForeground(Color.RED);
        title.setFont(myFont);

        subtitle = new JLabel("What kind of Loan do you prefer ?");
        subtitle.setBounds(185, 50, 450, 150);
        subtitle.setFont(myFont2);

        rb1 = new JRadioButton("Personal Loan");
        rb1.setBounds(120, 170, 200, 50);
        rb1.setFont(myFont1);

        rb2 = new JRadioButton("Home Loan");
        rb2.setBounds(330, 170, 190, 50);
        rb2.setFont(myFont1);

        rb3 = new JRadioButton("Car Loan");
        rb3.setBounds(520, 170, 200, 50);
        rb3.setFont(myFont1);

        ButtonGroup bg = new ButtonGroup();
        bg.add(rb1); bg.add(rb2); bg.add(rb3);

        p = new JLabel("Loan Amount -");
        p.setBounds(220, 250, 150, 20);
        p.setFont(myFont3);
        ptext = new JTextField();
        ptext.setBounds(370, 250, 150, 20);
        ptext.setFont(myFont3);

        r = new JLabel("Rate of Interest -");
        r.setBounds(220, 300, 150, 20);
        r.setFont(myFont3);
        rtext = new JTextField();
        rtext.setBounds(370, 300, 150, 20);
        rtext.setFont(myFont3);

        t = new JLabel("Loan Term -");
        t.setBounds(220, 350, 100, 20);
        t.setFont(myFont3);
        ttext = new JTextField();
        ttext.setBounds(370, 350, 150, 20);
        ttext.setFont(myFont3);

        b = new JButton("Calculate");
        b.setBounds(325, 420, 100, 50);
        b.addActionListener(this);

        ans = new JLabel("Loan EMI");
        ans.setBounds(220, 520, 100, 20);
        ans.setFont(myFont3);
        anstext = new JTextField();
        anstext.setBounds(370, 520, 150, 20);
        anstext.setFont(myFont3);
        anstext.setEditable(false);

        inte = new JLabel("Total Interest");
        inte.setBounds(220, 570, 100, 20);
        inte.setFont(myFont3);
        intetext = new JTextField();
        intetext.setBounds(370, 570, 150, 20);
        intetext.setFont(myFont3);
        intetext.setEditable(false);

        tp = new JLabel("Total Payment");
        tp.setBounds(220, 620, 100, 20);
        tp.setFont(myFont3);
        tptext = new JTextField();
        tptext.setBounds(370, 620, 150, 20);
        tptext.setFont(myFont3);
        tptext.setEditable(false);

        note = new JLabel("Note :");
        note.setBounds(30, 670, 400, 50);
        note.setForeground(Color.GRAY);

        note1 = new JLabel("1 - Interest rate is monthly. Input in months.");
        note1.setBounds(30, 700, 600, 50);
        note1.setForeground(Color.GRAY);

        note2 = new JLabel("2 - Max Personal Loan term: 60 months.");
        note2.setBounds(30, 730, 600, 50);
        note2.setForeground(Color.GRAY);

        note3 = new JLabel("3 - Max Home Loan term: 360 months.");
        note3.setBounds(30, 760, 600, 50);
        note3.setForeground(Color.GRAY);

        note4 = new JLabel("4 - Max Car Loan term: 84 months.");
        note4.setBounds(30, 790, 600, 50);
        note4.setForeground(Color.GRAY);

        add(title); add(subtitle); add(rb1); add(rb2); add(rb3); add(b);
        add(p); add(ptext); add(r); add(rtext); add(t); add(ttext);
        add(ans); add(anstext); add(inte); add(intetext); add(tp); add(tptext);
        add(note); add(note1); add(note2); add(note3); add(note4);
    }

    public void actionPerformed(ActionEvent e) {
        try {
            float n1 = Float.parseFloat(ptext.getText());
            float n2 = Float.parseFloat(rtext.getText());
            float n3 = Float.parseFloat(ttext.getText());

            boolean valid = false;
            if (rb1.isSelected() && n1 <= 1500000 && n2 <= 25 && n3 <= 60) valid = true;
            else if (rb2.isSelected() && n1 <= 20000000 && n2 <= 20 && n3 <= 360) valid = true;
            else if (rb3.isSelected() && n1 <= 2000000 && n2 <= 20 && n3 <= 84) valid = true;

            if (valid) {
                n2 = n2 / (12 * 100);  // Monthly interest
                float emi = (n1 * n2 * (float) Math.pow(1 + n2, n3)) /
                            ((float) Math.pow(1 + n2, n3) - 1);
                float totalPayment = emi * n3;
                float interest = totalPayment - n1;

                anstext.setText(String.format("%.2f", emi));
                tptext.setText(String.format("%.2f", totalPayment));
                intetext.setText(String.format("%.2f", interest));
            } else {
                JOptionPane.showMessageDialog(this, "Invalid loan parameters.");
            }
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Please enter valid numbers.");
        }
    }

    public static void main(String[] args) {
        new EmiCalculator();
    }
}
