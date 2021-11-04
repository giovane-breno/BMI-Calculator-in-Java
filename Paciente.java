/* *******************************************************************
 * Colegio Técnico Antônio Teixeira Fernandes (Univap)
 * Curso Técnico em Informática - Data de Entrega: 08/11/2021
 * Autores do Projeto: Giovane Breno Pereira Barbosa
 *                     Levi Custódio Kodaria Leão
 * Turma: 3E  Disciplina: Programação - II
 * Avaliação parcial referente ao 4 - Bimestre
 * Observação: <colocar se houver>
 * 
 * Paciente.java
 * ******************************************************************/
public class Paciente {
 
    public static void main(String args[]) {

        ProjectController Controller = new ProjectController();
        Screens Screen = new Screens();
        Controller.ConnectSQL();

        Screen.LoginScreen();

    }

}