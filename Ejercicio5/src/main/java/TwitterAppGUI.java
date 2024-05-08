import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.time.LocalDateTime;
import modelo.CuentaUsuario;
import modelo.Tweet;
import modelo.Twitter;




public class TwitterAppGUI extends JFrame {

    private List<CuentaUsuario> usuarios;

    private JTextField aliasField;
    private JTextField mensajeField;
    private JTextField aliasSeguirField;
    private DefaultListModel<CuentaUsuario> usuarioListModel;
    private JList<CuentaUsuario> usuarioList;

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

        JButton publicarButton = new JButton("Publicar");
        constraints.gridx = 0;
        constraints.gridy = 2;
        constraints.gridwidth = 2;
        constraints.anchor = GridBagConstraints.CENTER;
        panel.add(publicarButton, constraints);

        timelineArea = new JTextArea(10, 40);
        timelineArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(timelineArea);
        constraints.gridx = 0;
        constraints.gridy = 3;
        constraints.gridwidth = 2;
        panel.add(scrollPane, constraints);


        publicarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                publicarTweet();
            }
        });
        panel.add(scrollPane);

        add(panel);
        pack();
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    private void publicarTweet() {
        String alias = aliasField.getText();
        String mensaje = mensajeField.getText();
        CuentaUsuario usuario = twitter.buscarUsuarioPorAlias(alias);
        if (usuario != null) {
            LocalDateTime fechaHora = LocalDateTime.now(); // Obtenemos la fecha y hora actual
            Tweet tweet = new Tweet(mensaje, usuario); // Creamos el Tweet con la fecha y hora actuales
            twitter.publicarTweet(usuario, mensaje); // Publicamos el tweet
            usuario.agregarTweet(tweet);
            mostrarTimeline();
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
