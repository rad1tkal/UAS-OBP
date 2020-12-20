import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MenuAdmin extends JFrame {
    JButton crud = new JButton("CRUD");
    JButton acc = new JButton("Request");
    JButton pengembalian = new JButton("Pengembalian");
    JButton logout = new JButton("Logout");
    public MenuAdmin(){
        setTitle("Peminjaman Buku");
        setSize(400, 400);
        setLayout(new GridBagLayout());
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        add(new MenuPane());


        setVisible(true);
    }

    public class MenuPane extends JPanel {

        public MenuPane() {
            setBorder(new EmptyBorder(10, 10, 10, 10));
            setLayout(new GridBagLayout());

            GridBagConstraints gbc = new GridBagConstraints();
            gbc.gridwidth = GridBagConstraints.REMAINDER;
            gbc.anchor = GridBagConstraints.NORTH;

            add(new JLabel("<html><h1><strong><i>Admin</i></strong></h1><hr></html>"), gbc);

            gbc.anchor = GridBagConstraints.CENTER;
            gbc.fill = GridBagConstraints.HORIZONTAL;

            JPanel buttons = new JPanel(new GridBagLayout());
            buttons.add(crud, gbc);
            buttons.add(acc, gbc);
            buttons.add(pengembalian, gbc);
            buttons.add(logout,gbc);
            crud.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    new Admin();
                    dispose();
                }
            });
            logout.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    new Login();
                    dispose();
                }
            });
            acc.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    new Request();
                    dispose();
                }
            });
            pengembalian.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    new Return();
                    dispose();
                }
            });
            gbc.weighty = 1;
            add(buttons, gbc);
        }

    }



}
