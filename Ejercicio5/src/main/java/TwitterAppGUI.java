import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDateTime;
import java.util.List;
import modelo.CuentaUsuario;
import modelo.Tweet;
import modelo.Twitter;

public class TwitterAppGUI extends JFrame {

    private JTextField aliasField;
    private JTextField mensajeField;
    private JTextField seguirAliasField; // Nuevo campo para ingresar el alias a seguir
    private JTextArea timelineArea;
    private Twitter twitter;

    public TwitterAppGUI() {
        super("Twitter App");
        twitter = new Twitter();

        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.anchor = GridBagConstraints.WEST;
        constraints.insets = new Insets(10, 10, 10, 10);

        JLabel aliasLabel = new JLabel("Alias:");
        constraints.gridx = 0;
        constraints.gridy = 0;
        panel.add(aliasLabel, constraints);

        aliasField = new JTextField(20);
        constraints.gridx = 1;
        panel.add(aliasField, constraints);

        JLabel mensajeLabel = new JLabel("Mensaje:");
        constraints.gridx = 0;
        constraints.gridy = 1;
        panel.add(mensajeLabel, constraints);

        mensajeField = new JTextField(20);
        constraints.gridx = 1;
        panel.add(mensajeField, constraints);

        JLabel seguirAliasLabel = new JLabel("Seguir a:");
        constraints.gridx = 0;
        constraints.gridy = 2;
        panel.add(seguirAliasLabel, constraints);

        seguirAliasField = new JTextField(20); // Nuevo campo para ingresar el alias a seguir
        constraints.gridx = 1;
        panel.add(seguirAliasField, constraints);

        JButton publicarButton = new JButton("Publicar");
        constraints.gridx = 0;
        constraints.gridy = 3;
        constraints.gridwidth = 2;
        constraints.anchor = GridBagConstraints.CENTER;
        panel.add(publicarButton, constraints);

        JButton seguirButton = new JButton("Seguir");
        constraints.gridx = 0;
        constraints.gridy = 4;
        constraints.gridwidth = 2;
        constraints.anchor = GridBagConstraints.CENTER;
        panel.add(seguirButton, constraints);

        timelineArea = new JTextArea(10, 40);
        timelineArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(timelineArea);
        constraints.gridx = 0;
        constraints.gridy = 5;
        constraints.gridwidth = 2;
        panel.add(scrollPane, constraints);

        publicarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                publicarTweet();
            }
        });

        seguirButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                seguirUsuario();
            }
        });

        add(panel);
        pack();
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Crear algunos usuarios
        CuentaUsuario usuario1 = new CuentaUsuario("user1", "user1@example.com");
        CuentaUsuario usuario2 = new CuentaUsuario("user2", "user2@example.com");
        CuentaUsuario usuario3 = new CuentaUsuario("user3", "user3@example.com");

        // Agregar usuarios a Twitter
        twitter.agregarUsuario(usuario1);
        twitter.agregarUsuario(usuario2);
        twitter.agregarUsuario(usuario3);
    }

    private void publicarTweet() {
        String alias = aliasField.getText();
        String mensaje = mensajeField.getText();
        CuentaUsuario usuario = twitter.buscarUsuarioPorAlias(alias);
        if (usuario != null) {
            LocalDateTime fechaHora = LocalDateTime.now();
            Tweet tweet = new Tweet(mensaje, fechaHora, usuario);

            twitter.publicarTweet(usuario, mensaje); // Publicamos el tweet
            usuario.agregarTweet(tweet);
            mostrarTimeline(); // Mostrar el nuevo tweet en la línea de tiempo
            JOptionPane.showMessageDialog(this, "Tweet publicado correctamente.");
        } else {
            JOptionPane.showMessageDialog(this, "No se encontró ningún usuario con ese alias.");
        }
    }

    private void seguirUsuario() {
        String aliasSeguir = aliasField.getText();
        String aliasSeguido = seguirAliasField.getText(); // Obtener el alias del nuevo campo
        CuentaUsuario usuarioSeguir = twitter.buscarUsuarioPorAlias(aliasSeguir);
        CuentaUsuario usuarioSeguido = twitter.buscarUsuarioPorAlias(aliasSeguido);
        if (usuarioSeguir != null && usuarioSeguido != null) {
            if (usuarioSeguir != usuarioSeguido) {
                usuarioSeguir.seguir(usuarioSeguido);
                JOptionPane.showMessageDialog(this, "Ahora estás siguiendo a " + aliasSeguido + ".");
            } else {
                JOptionPane.showMessageDialog(this, "No puedes seguirte a ti mismo.");
            }
        } else {
            JOptionPane.showMessageDialog(this, "No se encontró algún usuario.");
        }
    }

    private void mostrarTimeline() {
        String alias = aliasField.getText();
        CuentaUsuario usuario = twitter.buscarUsuarioPorAlias(alias);
        if (usuario != null) {
            StringBuilder timeline = new StringBuilder();
            for (Tweet tweet : twitter.obtenerTimeline(usuario)) {
                timeline.append(tweet).append("\n");
            }
            timelineArea.setText(timeline.toString());
        } else {
            JOptionPane.showMessageDialog(this, "No se encontró ningún usuario con ese alias.");
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new TwitterAppGUI().setVisible(true);
            }
        });
    }
}









