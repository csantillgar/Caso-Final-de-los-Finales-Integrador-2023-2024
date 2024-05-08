import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.time.LocalDateTime;


import modelo.CuentaUsuario;
import modelo.Tweet;


class TwitterAppGUI extends JFrame {

    private List<CuentaUsuario> usuarios;

    private JTextField aliasField;
    private JTextField mensajeField;
    private JTextField aliasSeguirField;
    private DefaultListModel<CuentaUsuario> usuarioListModel;
    private JList<CuentaUsuario> usuarioList;

    public TwitterAppGUI() {
        setTitle("Twitter App");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(600, 400);
        setLocationRelativeTo(null);

        usuarios = new ArrayList<>(); // Inicializamos la lista de usuarios

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(6, 2, 10, 10));

        JLabel aliasLabel = new JLabel("Alias:");
        aliasField = new JTextField();
        panel.add(aliasLabel);
        panel.add(aliasField);

        JLabel mensajeLabel = new JLabel("Mensaje:");
        mensajeField = new JTextField();
        panel.add(mensajeLabel);
        panel.add(mensajeField);

        JButton publicarButton = new JButton("Publicar Tweet");
        publicarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                publicarTweet();
            }
        });
        panel.add(publicarButton);

        JLabel seguirLabel = new JLabel("Seguir a:");
        aliasSeguirField = new JTextField();
        panel.add(seguirLabel);
        panel.add(aliasSeguirField);

        JButton seguirButton = new JButton("Seguir Usuario");
        seguirButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                seguirUsuario();
            }
        });
        panel.add(seguirButton);

        usuarioListModel = new DefaultListModel<>();
        usuarioList = new JList<>(usuarioListModel);
        JScrollPane scrollPane = new JScrollPane(usuarioList);
        panel.add(scrollPane);

        add(panel);
    }

    private void publicarTweet() {
        String alias = aliasField.getText();
        String mensaje = mensajeField.getText();
        CuentaUsuario usuario = buscarUsuarioPorAlias(alias);
        if (usuario != null) {
            LocalDateTime fechaHora = LocalDateTime.now(); // Obtenemos la fecha y hora actual
            Tweet tweet = new Tweet(mensaje, fechaHora, usuario); // Creamos el Tweet con la fecha y hora actuales
            usuario.agregarTweet(tweet);
            JOptionPane.showMessageDialog(this, "Tweet publicado correctamente.");
        } else {
            JOptionPane.showMessageDialog(this, "No se encontró ningún usuario con ese alias.");
        }
    }

    private void seguirUsuario() {
        String alias = aliasSeguirField.getText();
        CuentaUsuario usuario = buscarUsuarioPorAlias(alias);
        if (usuario != null) {
            // Obtener usuario actual
            String aliasUsuarioActual = usuarioListModel.getElementAt(usuarioList.getSelectedIndex()).getAlias();
            CuentaUsuario usuarioActual = buscarUsuarioPorAlias(aliasUsuarioActual);
            if (usuarioActual != null) {
                if (!usuarioActual.getSiguiendo().contains(usuario)) {
                    usuarioActual.seguir(usuario);
                    JOptionPane.showMessageDialog(this, "Ahora estás siguiendo a " + alias + ".");
                } else {
                    JOptionPane.showMessageDialog(this, "Ya estás siguiendo a este usuario.");
                }
            }
        } else {
            JOptionPane.showMessageDialog(this, "No se encontró ningún usuario con ese alias.");
        }
    }

    private CuentaUsuario buscarUsuarioPorAlias(String alias) {
        for (CuentaUsuario usuario : usuarios) {
            if (usuario.getAlias().equals(alias)) {
                return usuario;
            }
        }
        return null;
    }



    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                TwitterAppGUI app = new TwitterAppGUI();
                app.setVisible(true);
            }
        });
    }
}
