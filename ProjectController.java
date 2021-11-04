import java.sql.*;

public class ProjectController {
    private String name; // private = restricted access
    private String c_user = "root";
    private String c_senha = "";
    private String c_fonte = "jdbc:mysql://localhost/projeto_wagner";
    private Connection con;

    // connect db
    public void ConnectSQL() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection(c_fonte, c_user, c_senha);
            System.out.println("Conexao MS-ACCESS O.K.");
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery("SELECT * FROM login");

            while (rs.next()) {
                String w_nome = rs.getString("username");
                String w_pass = rs.getString("password");
                System.out.println("Nome: " + w_nome.trim() + "\nSenha: " + w_pass.trim() + "\n");
            }
            con.close();
        } catch (Exception e) {
            System.out.println("Erro na Abertura do banco de Dados\nErro: " + e);
        }
    }

    // check login
    public String getUserAccount(String user, String pass) {
        String CheckAccount = "false";
        try {
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection(c_fonte, c_user, c_senha);
            Statement st = con.createStatement();
            ResultSet rs = st
                    .executeQuery("SELECT * FROM login WHERE username = '" + user + "' AND password = '" + pass + "'");

            if (rs.next()) {
                CheckAccount = "true";
            }
            con.close();
        } catch (Exception e) {
            System.out.println("Usuário Inexistente" + e);
        }

        return CheckAccount;
    }

    // calculate
    public String[] getIMC(String Peso, String Altura, String Nome) {
        String[] IMCvalues = new String[2];
        Double IMC = 0.0;
        String Classification = "";

        try {
            Double peso = Double.parseDouble(Peso);
            Double altura = Double.parseDouble(Altura);

            IMC = (peso / (altura * altura));

            if (IMC < 18.5) {
                Classification = "Magreza";
            } else if (IMC >= 18.5 && IMC <= 24.9) {
                Classification = "Saudável";
            } else if (IMC >= 25 && IMC <= 29.9) {
                Classification = "Sobrepeso";
            } else if (IMC >= 30 && IMC <= 34.9) {
                Classification = "Obesidade Grau I";
            } else if (IMC >= 35 && IMC <= 39.9) {
                Classification = "Obesidade Grau II";
            } else {
                Classification = "Obesidade Grau III";
            }

        } catch (Exception e) {
            System.out.println("Erro no calculo.\n Erro:" + e);
        }

        try {
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection(c_fonte, c_user, c_senha);
            Statement st = con.createStatement();
            st.executeUpdate("INSERT INTO paciente (name, height, width) VALUES ('" + Nome + "', '" + Altura + "', '"
                    + Peso + "')");
            ResultSet rs1 = st.executeQuery("SELECT * FROM paciente WHERE (name, height, width) = ('" + Nome + "', '"
                    + Altura + "', '" + Peso + "') ORDER BY id DESC LIMIT 1");

            if (rs1.next()) {
                String w_pk = rs1.getString("id");
                st.executeUpdate("INSERT INTO banco (patient, imc, classification) VALUES ('" + w_pk + "', '" + IMC
                        + "', '" + Classification + "')");
            }
            con.close();
        } catch (Exception e) {
            System.out.println("Erro ao salvar.\n Erro:" + e);
        }

        IMCvalues[0] = String.format("%.2f", IMC).toString();
        IMCvalues[1] = Classification;
        return IMCvalues;
    }

    // retrieve percentual
    public String[] getpercentual() {
        String[] percentual = new String[6];
        int count = 0;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection(c_fonte, c_user, c_senha);
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery("SELECT COUNT(*) FROM banco");

            while (rs.next()) {
                count = (rs.getInt(1));
            }
            con.close();
        } catch (Exception e) {
            System.out.println("Erro ao recuperar quantidade de classificações.\n Erro:" + e);
        }

        String[] classe = new String[count];

        try {
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection(c_fonte, c_user, c_senha);
            Statement st = con.createStatement();
            ResultSet rs1 = st.executeQuery("SELECT * FROM banco");

            int counter = 0;

            while (rs1.next()) {

                String w_classification = rs1.getString("classification");

                classe[counter] = w_classification;
                counter++;

            }

            rs1.close();

            float magreza = 0;
            float saudavel = 0;
            float sobrepeso = 0;
            float obI = 0;
            float obII = 0;
            float obIII = 0;

            for (int i = 0; i < classe.length; i++) {
                if ("Magreza".equals(classe[i])) {
                    magreza++;
                } else if ("Saudável".equals(classe[i])) {
                    saudavel++;
                } else if ("Sobrepeso".equals(classe[i])) {
                    sobrepeso++;
                } else if ("Obesidade Grau I".equals(classe[i])) {
                    obI++;
                } else if ("Obesidade Grau II".equals(classe[i])) {
                    obII++;
                } else if ("Obesidade Grau III".equals(classe[i])) {
                    obIII++;
                }
            }

            int total = classe.length;

            magreza = ((magreza * 100) / total);
            saudavel = ((saudavel * 100) / total);
            sobrepeso = ((sobrepeso * 100) / total);
            obI = ((obI * 100) / total);
            obII = ((obII * 100) / total);
            obIII = ((obIII * 100) / total);

            percentual[0] = "Magreza - " + String.format("%.02f", magreza) + "%";
            percentual[1] = "Saudável  - " + String.format("%.02f", saudavel) + "%";
            percentual[2] = "Sobrepeso  - " + String.format("%.02f", sobrepeso) + "%";
            percentual[3] = "Obesidade Grau I  - " + String.format("%.02f", obI) + "%";
            percentual[4] = "Obesidade Grau II  - " + String.format("%.02f", obII) + "%";
            percentual[5] = "Obesidade Grau III  - " + String.format("%.02f", obIII) + "%";

            con.close();
        } catch (Exception e) {
            System.out.println("Erro ao recuperar classificacoes.\n Erro:" + e);
        }

        return percentual;
    }

    // retrieve userlist in array
    public String[] getUserList(int type) {
        int count = 0;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection(c_fonte, c_user, c_senha);
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery("SELECT COUNT(*) AS count FROM paciente");

            while (rs.next()) {
                count = (rs.getInt(1));
            }
        } catch (Exception e) {
            System.out.println("Erro ao recuperar lista de pacientes.\n Erro:" + e);
        }

        String[] userList = new String[count];

        try {
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection(c_fonte, c_user, c_senha);
            Statement st = con.createStatement();
            ResultSet rs1 = st.executeQuery("SELECT * FROM paciente");
            int counter = 0;
            while (rs1.next()) {
                String w_id = rs1.getString("id");
                String w_name = rs1.getString("name");
                userList[counter] = "[" + w_id + "]" + " " + w_name;
                counter++;
            }

            counter = 0;
            if (type == 1) {
                ResultSet rs2 = st.executeQuery("SELECT * FROM banco");
                while (rs2.next()) {
                    String w_classification = rs2.getString("classification");
                    userList[counter] += " - " + w_classification;
                    counter++;
                }
            }

            con.close();
        } catch (Exception e) {
            System.out.println("Erro ao recuperar lista de classificacao.\n Erro:" + e);
        }

        return userList;

    }

    public String[] setQuery(int type, int pk, String Peso, String Altura, String IMC, String Classification){
        String[] Query = new String[12];
        //type 1 == read
        // type 2 == update
        // type 3 == delete

        try{
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection(c_fonte, c_user, c_senha);
            Statement st = con.createStatement();

            if (type == 1){
                ResultSet rs = st.executeQuery("SELECT * FROM paciente  WHERE (id) = ('"+ pk +"')");
                while (rs.next()) {
                    String w_height = rs.getString("height");
                    String w_width = rs.getString("width");
                    Query[0] = w_height.replace(".", ",");
                    Query[1] = w_width.replace(".", ",");
                }


                ResultSet rs1 = st.executeQuery("SELECT * FROM banco WHERE (patient) = ('"+ pk +"')");
                while (rs1.next()) {
                    String w_imc = rs1.getString("imc");
                    String w_classification = rs1.getString("classification");
                    Query[2] = w_imc.replace(".", ",");
                    Query[3] = w_classification;
                }

                con.close();

            }else if (type == 2){
                st.executeUpdate("UPDATE paciente SET height = '"+ Altura +"', width = '"+Peso+"' WHERE paciente.id = '"+pk+"'");


                st.executeUpdate("UPDATE banco SET imc = '"+ IMC +"', classification = '"+Classification+"' WHERE banco.patient = '"+pk+"'");

                con.close();
            }else{
                st.executeUpdate("DELETE FROM paciente WHERE paciente.id = '"+pk+"'");

                st.executeUpdate("DELETE FROM banco WHERE banco.patient = '"+pk+"'");
                
                con.close();
            }

        }catch (Exception e){
            System.out.println("Erro em uma das operações.\n Erro:" + e);
        }

        return Query;
    }

    // log
    public void setLogs(String logname) {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection(c_fonte, c_user, c_senha);
            Statement st = con.createStatement();
            st.executeUpdate("INSERT INTO log (type) VALUES ('" + logname + "')");

            con.close();
        } catch (Exception e) {
            System.out.println("Erro na criação do Log\nErro: " + e);
        }
    }
}