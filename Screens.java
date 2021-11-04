import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.util.List;

import static javax.swing.JOptionPane.showMessageDialog;

public class Screens implements ActionListener {

    // login frame
    private JFrame LoginFrame = new JFrame();
    private JButton ButtonLogin;
    private JTextField TextFieldLogin;
    private JPasswordField TextFieldPass;

    // index frame
    private JFrame IndexFrame = new JFrame();
    private JButton ButtonIMC;
    private JButton ButtonCrud;
    private JButton ButtonReport;
    private JTextField TextFieldAltura;
    private JTextField TextFieldPeso;
    private JTextField textFieldIMC;
    private JTextField textFieldClassification;
    private JTextField textFieldName;

    // report frame
    private JFrame ReportFrame = new JFrame();
    private JList ListPacientes;
    private JList ListPercentual;
    private JButton ButtonBack;
    private JScrollPane ScrollPacientes;
    private JScrollPane ScrollPercentual;

    // crud frame
    private JFrame crudFrame = new JFrame();
    private JComboBox ComboPacientes;
    private JComboBox ComboClasses;
    private JButton ButtonExlude;
    private JButton ButtonUpdate;
    private JButton ButtonRead;
    private JButton ButtonBack1;

    private JTextField TextFieldcdrAltura;
    private JTextField TextFieldcdrPeso;
    private JTextField textFieldcdrIMC;
    private JTextField textFieldcdrClassification;

    public void LoginScreen() {

        LoginFrame.setLocationRelativeTo(null);
        JLabel LabelLogin = new JLabel("Usuario:");
        JLabel LabelPass = new JLabel("Senha:");

        TextFieldLogin = new JTextField();
        TextFieldPass = new JPasswordField(10);
        ButtonLogin = new JButton("Enviar");

        ButtonLogin.addActionListener(this);

        LabelLogin.setBounds(25, 80, 60, 30);
        LabelPass.setBounds(25, 120, 60, 30);
        TextFieldLogin.setBounds(85, 85, 280, 30);
        TextFieldPass.setBounds(85, 125, 280, 30);
        ButtonLogin.setBounds(85, 160, 80, 30);

        LoginFrame.add(ButtonLogin);
        LoginFrame.add(LabelLogin);
        LoginFrame.add(LabelPass);
        LoginFrame.add(TextFieldLogin);
        LoginFrame.add(TextFieldPass);

        LoginFrame.setSize(400, 300);
        LoginFrame.setTitle("Autenticacao de Usuario");
        LoginFrame.setLayout(null);
        LoginFrame.setVisible(true);

    }

    public void IndexScreen() {

        IndexFrame.setLocationRelativeTo(null);
        JLabel LabelPeso = new JLabel("Peso:");
        JLabel LabelAltura = new JLabel("Altura:");
        JLabel LabelIMC = new JLabel("IMC:");
        JLabel LabelClassification = new JLabel("Class:");
        JLabel LabelName = new JLabel("Nome:");

        TextFieldPeso = new JTextField(1);
        TextFieldAltura = new JTextField(1);
        textFieldName = new JTextField(1);

        textFieldIMC = new JTextField(2);
        textFieldClassification = new JTextField(2);
        ButtonIMC = new JButton("Calcular");
        ButtonCrud = new JButton("Crud");
        ButtonReport = new JButton("Relatórios");

        ButtonCrud.addActionListener(this);
        ButtonIMC.addActionListener(this);
        ButtonReport.addActionListener(this);

        LabelName.setBounds(25, 10, 60, 30);
        textFieldName.setBounds(65, 15, 90, 20);
        LabelPeso.setBounds(25, 40, 60, 30);
        LabelAltura.setBounds(25, 70, 60, 30);
        TextFieldPeso.setBounds(65, 45, 40, 20);
        TextFieldAltura.setBounds(65, 75, 40, 20);

        LabelIMC.setBounds(125, 40, 60, 30);
        LabelClassification.setBounds(125, 70, 90, 30);
        textFieldIMC.setBounds(165, 45, 80, 20);
        textFieldClassification.setBounds(165, 75, 110, 20);
        textFieldIMC.setEditable(false);
        textFieldClassification.setEditable(false);

        ButtonReport.setBounds(65, 180, 100, 30);
        ButtonCrud.setBounds(230, 180, 100, 30);

        ButtonIMC.setBounds(65, 105, 100, 30);

        IndexFrame.add(LabelName);
        IndexFrame.add(textFieldName);
        IndexFrame.add(ButtonIMC);
        IndexFrame.add(LabelPeso);
        IndexFrame.add(TextFieldPeso);
        IndexFrame.add(LabelAltura);
        IndexFrame.add(TextFieldAltura);
        IndexFrame.add(ButtonReport);
        IndexFrame.add(ButtonCrud);

        IndexFrame.add(LabelIMC);
        IndexFrame.add(textFieldIMC);
        IndexFrame.add(LabelClassification);
        IndexFrame.add(textFieldClassification);

        IndexFrame.setSize(400, 300);
        IndexFrame.setTitle("Calculo de IMC");
        IndexFrame.setLayout(null);
        IndexFrame.setVisible(true);

    }

    public void ReportScreen() {
        ProjectController Controller = new ProjectController();
        ButtonBack = new JButton("Voltar");

        String[] users = new String[Controller.getUserList(1).length];
        users = Controller.getUserList(1);
        ListPacientes = new JList(users);

        String[] percentual = new String[Controller.getpercentual().length];
        percentual = Controller.getpercentual();
        ListPercentual = new JList(percentual);

        ScrollPacientes = new JScrollPane(ListPacientes);
        ScrollPercentual = new JScrollPane(ListPercentual);

        ReportFrame.setLocationRelativeTo(null);

        ListPacientes.setVisibleRowCount(4);

        ButtonBack.addActionListener(this);

        ButtonBack.setBounds(5, 10, 80, 30);

        ScrollPercentual.setBounds(110, 110, 170, 90);
        ScrollPacientes.setBounds(110, 10, 170, 90);

        ReportFrame.add(ScrollPacientes);
        ReportFrame.add(ScrollPercentual);
        ReportFrame.add(ButtonBack);

        ReportFrame.setSize(400, 300);
        ReportFrame.setTitle("Relatórios");
        ReportFrame.setLayout(null);
        ReportFrame.setVisible(false);

    }

    public void crudScreen() {
        ProjectController Controller = new ProjectController();
        crudFrame.setLocationRelativeTo(null);

        JLabel LabelPeso = new JLabel("Peso:");
        JLabel LabelAltura = new JLabel("Altura:");
        JLabel LabelIMC = new JLabel("IMC:");
        JLabel LabelClassification = new JLabel("Class:");
        JLabel LabelName = new JLabel("Nome:");

        ButtonBack1 = new JButton("Voltar");

        String[] classes = new String[6];
        classes[0] = "Magreza";
        classes[1] = "Saudável";
        classes[2] = "Sobrepeso";
        classes[3] = "Obesidade Grau I";
        classes[4] = "Obesidade Grau II";
        classes[5] = "Obesidade Grau III";

        ComboClasses = new JComboBox(classes);
        ComboClasses.setSelectedIndex(-1);
        String[] pacientes = new String[Controller.getUserList(0).length];
        pacientes = Controller.getUserList(0);
        ComboPacientes = new JComboBox(pacientes);

        TextFieldcdrPeso = new JTextField(1);
        TextFieldcdrAltura = new JTextField(1);

        textFieldcdrIMC = new JTextField(2);
        textFieldcdrClassification = new JTextField(2);

        ButtonRead = new JButton("Consultar");
        ButtonUpdate = new JButton("Atualizar");
        ButtonExlude = new JButton("Excluir");

        ButtonRead.addActionListener(this);
        ButtonUpdate.addActionListener(this);
        ButtonExlude.addActionListener(this);
        ButtonBack1.addActionListener(this);

        LabelName.setBounds(25, 90, 60, 30);
        LabelPeso.setBounds(25, 130, 60, 30);
        LabelAltura.setBounds(25, 160, 60, 30);
        TextFieldcdrPeso.setBounds(65, 135, 40, 20);
        TextFieldcdrAltura.setBounds(65, 165, 40, 20);

        ButtonRead.setBounds(25, 200, 90, 30);
        ButtonUpdate.setBounds(125, 200, 90, 30);
        ButtonExlude.setBounds(225, 200, 90, 30);

        LabelIMC.setBounds(125, 130, 60, 30);
        LabelClassification.setBounds(125, 160, 90, 30);
        textFieldcdrIMC.setBounds(165, 135, 80, 20);
        ComboClasses.setBounds(165, 165, 110, 20);

        ComboPacientes.setBounds(65, 95, 90, 20);
        ButtonBack1.setBounds(5, 10, 80, 30);

        crudFrame.add(ComboPacientes);
        crudFrame.add(ButtonBack1);

        crudFrame.add(LabelName);
        crudFrame.add(LabelPeso);
        crudFrame.add(TextFieldcdrPeso);
        crudFrame.add(LabelAltura);
        crudFrame.add(TextFieldcdrAltura);

        crudFrame.add(ButtonRead);
        crudFrame.add(ButtonExlude);
        crudFrame.add(ButtonUpdate);

        crudFrame.add(LabelIMC);
        crudFrame.add(textFieldcdrIMC);
        crudFrame.add(ComboClasses);
        crudFrame.add(textFieldcdrClassification);

        crudFrame.setSize(400, 300);
        crudFrame.setTitle("CRUD Pacientes");
        crudFrame.setLayout(null);
        crudFrame.setVisible(false);
    }

    public void actionPerformed(ActionEvent evento) {
        ProjectController Controller = new ProjectController();

        // LoginFrame
        if (LoginFrame.isFocused()) {
            String strUser = TextFieldLogin.getText().trim();
            String strPass = new String(TextFieldPass.getPassword()).trim();
            if (Controller.getUserAccount(strUser, strPass) == "true") {
                LoginFrame.setVisible(false);
                IndexScreen();
                ReportScreen();
                crudScreen();

            } else {
                JOptionPane.showMessageDialog(ButtonLogin, "Usuário ou Senha incorreta.", "Alerta",
                        JOptionPane.ERROR_MESSAGE);
                TextFieldLogin.setText("");
                TextFieldPass.setText("");
            }
        }

        // IndexFrame
        if (IndexFrame.isFocused()) {
            DefaultListModel ModelPacientes = new DefaultListModel();
            DefaultListModel ModelPercentual = new DefaultListModel();

            String strPeso = TextFieldPeso.getText();
            String strAltura = TextFieldAltura.getText().trim();
            String strName = textFieldName.getText().trim();

            if (evento.getActionCommand().equals("Calcular")) {
                if (!strName.equals("") && !strPeso.equals("") && !strAltura.equals("")) {
                    String[] IMCResult = new String[2];
                    IMCResult = Controller.getIMC(strPeso, strAltura.replace(",", "."), strName);
                    textFieldIMC.setText(IMCResult[0].toString());
                    textFieldClassification.setText(IMCResult[1].toString());
                    Controller.setLogs("Calculo IMC");
                    JOptionPane.showMessageDialog(ButtonLogin, "Paciente cadastrado com sucesso.", "Alerta",
                            JOptionPane.INFORMATION_MESSAGE);

                    TextFieldAltura.setText("");
                    TextFieldPeso.setText("");
                    textFieldName.setText("");
                    textFieldIMC.setText("");
                    textFieldClassification.setText("");

                    // ATUALIZA LISTBOX E COMBOBOX =))

                    String[] pacientes = new String[Controller.getUserList(0).length];
                    pacientes = Controller.getUserList(0);
                    ComboPacientes.removeAllItems();

                    for (int i = 0; i < pacientes.length; i++) {
                        ComboPacientes.addItem(pacientes[i]);
                    }

                    String[] users = new String[Controller.getUserList(1).length];
                    users = Controller.getUserList(1);
                    for (int i = 0; i < users.length; i++) {
                        ModelPacientes.add(i, users[i]);

                    }
                    ListPacientes.setModel(ModelPacientes);

                    String[] percentual = new String[Controller.getpercentual().length];
                    percentual = Controller.getpercentual();
                    for (int i = 0; i < percentual.length; i++) {
                        ModelPercentual.add(i, percentual[i]);

                    }
                    ListPercentual.setModel(ModelPercentual);

                } else {
                    JOptionPane.showMessageDialog(ButtonLogin, "Um ou mais campos estão vazios.", "Alerta",
                            JOptionPane.ERROR_MESSAGE);

                }
            }

            if (evento.getActionCommand().equals("Relatórios")) {
                ReportFrame.setVisible(true);
                IndexFrame.setVisible(false);

            }

            if (evento.getActionCommand().equals("Crud")) {
                crudFrame.setVisible(true);
                IndexFrame.setVisible(false);

            }
        }

        // ações de voltar
        if (evento.getActionCommand().equals("Voltar")) {
            crudFrame.setVisible(false);
            ReportFrame.setVisible(false);
            IndexFrame.setVisible(true);

        }

        // CrudFrame
        if (crudFrame.isFocused()) {
            DefaultListModel ModelPacientes = new DefaultListModel();
            DefaultListModel ModelPercentual = new DefaultListModel();
            String strPeso = TextFieldcdrPeso.getText().trim().replace(",", ".");
            String strAltura = TextFieldcdrAltura.getText().trim().replace(",", ".");
            String strIMC = textFieldcdrIMC.getText().trim().replace(",", ".");
            ;
            String strClassification = "0";
            if (ComboClasses.getSelectedIndex() != -1) {
                strClassification = ComboClasses.getSelectedItem().toString();
            }

            String ComboPk = ComboPacientes.getSelectedItem().toString();
            ComboPk = ComboPk.replace("[", "");
            String[] Pk = ComboPk.split("]");
            if (evento.getActionCommand().equals("Consultar")) {

                String[] Query = new String[12];
                Query = Controller.setQuery(1, Integer.parseInt(Pk[0]), "0", "0", "0", "0");

                TextFieldcdrAltura.setText(Query[0]);
                TextFieldcdrPeso.setText(Query[1]);
                textFieldcdrIMC.setText(Query[2]);
                ComboClasses.setSelectedItem(Query[3]);
                Controller.setLogs("Consulta Paciente");
            } else if (evento.getActionCommand().equals("Atualizar")) {
                if (!strIMC.equals("") && !strPeso.equals("") && !strAltura.equals("")
                        && ComboClasses.getSelectedIndex() != -1) {

                    Controller.setQuery(2, Integer.parseInt(Pk[0]), strPeso, strAltura, strIMC, strClassification);
                    TextFieldcdrAltura.setText("");
                    TextFieldcdrPeso.setText("");
                    textFieldcdrIMC.setText("");
                    textFieldcdrClassification.setText("");
                    ComboClasses.setSelectedIndex(-1);
                    Controller.setLogs("Update Paciente");
                    JOptionPane.showMessageDialog(ButtonLogin, "Paciente atualizado com sucesso.", "Alerta",
                            JOptionPane.INFORMATION_MESSAGE);
                            
                    //ATUALIZAR COMBO E LIST
                    String[] users = new String[Controller.getUserList(1).length];
                    users = Controller.getUserList(1);
                    for (int i = 0; i < users.length; i++) {
                        ModelPacientes.add(i, users[i]);

                    }
                    ListPacientes.setModel(ModelPacientes);

                    String[] percentual = new String[Controller.getpercentual().length];
                    percentual = Controller.getpercentual();
                    for (int i = 0; i < percentual.length; i++) {
                        ModelPercentual.add(i, percentual[i]);

                    }
                    ListPercentual.setModel(ModelPercentual);

                } else {
                    JOptionPane.showMessageDialog(ButtonLogin, "Um ou mais campos estão vazios.", "Alerta",
                            JOptionPane.ERROR_MESSAGE);
                }

            } else if (evento.getActionCommand().equals("Excluir")) {
                Controller.setQuery(3, Integer.parseInt(Pk[0]), "0", "0", "0", "0");
                TextFieldcdrAltura.setText("");
                TextFieldcdrPeso.setText("");
                textFieldcdrIMC.setText("");
                ComboClasses.setSelectedIndex(-1);
                Controller.setLogs("Delete Paciente");
                JOptionPane.showMessageDialog(ButtonLogin, "Paciente excluido com sucesso.", "Alerta",
                        JOptionPane.INFORMATION_MESSAGE);

                String[] pacientes = new String[Controller.getUserList(0).length];
                pacientes = Controller.getUserList(0);
                ComboPacientes.removeAllItems();


                //ATUALIZAR COMBO E LIST
                for (int i = 0; i < pacientes.length; i++) {
                    ComboPacientes.addItem(pacientes[i]);
                }

                String[] users = new String[Controller.getUserList(1).length];
                    users = Controller.getUserList(1);
                    for (int i = 0; i < users.length; i++) {
                        ModelPacientes.add(i, users[i]);

                    }
                    ListPacientes.setModel(ModelPacientes);

                    String[] percentual = new String[Controller.getpercentual().length];
                    percentual = Controller.getpercentual();
                    for (int i = 0; i < percentual.length; i++) {
                        ModelPercentual.add(i, percentual[i]);

                    }
                    ListPercentual.setModel(ModelPercentual);
            }
        }

    }
}
