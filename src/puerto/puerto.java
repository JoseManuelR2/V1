package puerto;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;


public class puerto extends JFrame{

    private JLabel Description;
    private JLabel Remitent;
    private JLabel Reciever;
    private JTextField RemitentText;
    private JTextField RecieverText;
    private JLabel Inspection;
    private JLabel Operations;
    private JButton PileButton;
    private JButton UnpileButton;
    private JTextField UnplieText;
    private JButton DescriptionButton;
    private JButton NumberOfContainersButton;
    private JTextField IDtext;
    private JLabel IDNumber;
    private JLabel Weight;
    private JTextField WeightText;
    private JTextArea DescriptionTextArea;
    private JLabel Priority;
    private JRadioButton a1RadioButton;
    private JRadioButton a2RadioButton;
    private JRadioButton a3RadioButton;
    private JLabel State;
    private JTextArea StateTextArea;
    private JCheckBox InspectionCheckBox;
    private JTextArea ShowDescriptiomTextArea;
    private JComboBox NumberContainersComboBox;
    private JTextField NumberTextField;
    private JPanel miPanel;
    private JLabel Country;
    private JComboBox comboBox1;
    private JLabel CompanyLogo;
    private JLabel ErrorLabel;
    private JButton AutoCheck;
    private Hub hub = new Hub();
    FileOutputStream fileOut = new FileOutputStream("container.ser");
    ObjectOutputStream out = new ObjectOutputStream(fileOut);
    //constructor
    public puerto() throws IOException {
        setTitle("Port Management");
        setSize(1200,500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
        setContentPane(miPanel);

        //solo puedes crear un contenedor si existe un id como minimo
        IDtext.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                PileButton.setEnabled(true);
            }
        });

        //prioridad(si seleccionas uno los otros no pueden estar seleccionados)
        a1RadioButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if(a2RadioButton.isSelected() || a3RadioButton.isSelected()){
                    a2RadioButton.setSelected(false);
                    a3RadioButton.setSelected(false);
                }
            }
        });
        a2RadioButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if(a1RadioButton.isSelected() || a3RadioButton.isSelected()){
                    a1RadioButton.setSelected(false);
                    a3RadioButton.setSelected(false);
                }
            }
        });
        a3RadioButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if(a1RadioButton.isSelected() || a2RadioButton.isSelected()){
                    a1RadioButton.setSelected(false);
                    a2RadioButton.setSelected(false);
                }
            }
        });
        //crear el contenedor y apliar
        PileButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e){
                int ID = 0, weight = 0;
                ErrorLabel.setText("");
                try{
                    ID=Integer.parseInt(IDtext.getText());
                    weight=Integer.parseInt(WeightText.getText());
                }catch (IllegalArgumentException ex){
                    ErrorLabel.setText("Your container hasn't been added succesfully. Please try again (" + ex.toString() + ")");
                }
                String description=DescriptionTextArea.getText();
                String remitent=RemitentText.getText();
                String reciver= RecieverText.getText();
                String pais=comboBox1.getSelectedItem().toString();
                int prioridad;
                if(a1RadioButton.isSelected())prioridad=1;
                else if(a2RadioButton.isSelected())prioridad=2;
                else prioridad=3;
                Contenedor c;
                if(WeightText.getText()!=null || DescriptionTextArea.getText()!=null || RemitentText.getText()!=null || RecieverText.getText()!=null || comboBox1.getSelectedItem()!= null){
                    if(ID != 0 || weight != 0){
                        c=new Contenedor(ID, weight,prioridad, pais, description, remitent, reciver);
                        if(InspectionCheckBox.isSelected())c.setInspeccionado();
                        hub.apilar(c);
                        System.out.println(c);
                        System.out.println(hub);
                        try {
                            out.writeObject(c);
                            //out.close();
                            //fileOut.close();
                        } catch (IOException ex) {
                            throw new RuntimeException(ex);
                        }
                    }
                }
                StateTextArea.setText(hub.toString());
                IDtext.setText("");
                WeightText.setText("");
                DescriptionTextArea.setText("");
                RemitentText.setText("");
                RecieverText.setText("");
                comboBox1.setSelectedItem("");
                a1RadioButton.setSelected(false);
                a2RadioButton.setSelected(false);
                a3RadioButton.setSelected(false);
            }
        });
        UnpileButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                hub.desapilar(Integer.parseInt(UnplieText.getText())-1);
                StateTextArea.setText(hub.toString());
            }
        });
        //retorna la descicpion del contenedor que este arriba
        DescriptionButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                ShowDescriptiomTextArea.setText(hub.description(Integer.parseInt(UnplieText.getText())-1));
            }
        });
        NumberOfContainersButton.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                NumberTextField.setText(Integer.toString(hub.samePais(NumberContainersComboBox.getSelectedItem().toString())));
            }
        });
        AutoCheck.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String s = hub.cheked(Integer.parseInt(WeightText.getText()), Integer.parseInt(IDtext.getText()));
                JOptionPane.showMessageDialog(null, s);
            }
        });
    }
    public static void main(String[] args) throws IOException {
        new puerto();
    }

}
