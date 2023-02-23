/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

/**
 *
 * @author samar
 */
import entites.Commentaire;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import utils.MyConnection;
public class CommentaireService {
    Connection myconnex = MyConnection.getInstance().getConnection();
    
//    MyConnection myconnex =new MyConnection();
    public int ajouterCommentaire(Commentaire c) {
     int comid=-1;
        try {
           String req1 ="INSERT INTO `commentaire` (`comid`,`postid`, `userid`,`contenu`,`datecom`)"
                   + " VALUES (NULL,Null,NULL, '"+c.getContenu()+" ','"+c.getDatecom()+"'); ";
          Statement ste = myconnex.createStatement();
          comid= ste.executeUpdate(req1);
       } catch (SQLException ex) {
           System.out.println(ex);
       }
       return comid;
    }

    public boolean modifierCommentaire(Commentaire c) {
         try {
            String requete = "UPDATE commentaire set contenu=? ,datecom =? WHERE comid=?";
            PreparedStatement pst = myconnex.prepareStatement(requete);
            pst.setString(1, c.getContenu());
            pst.setDate(2, c.getDatecom());
            pst.setInt(3,1);
            pst.executeUpdate();

            System.out.println("Commentaire Modifié");

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        } //To change body of generated methods, choose Tools | Templates.
         return false;
    }

    public boolean supprimerCommentaire(Commentaire c) {
       try {
           String req="delete from commentaire where comid= ? ";
           PreparedStatement cs = myconnex.prepareStatement(req);
           cs.setInt(1, 2);
           cs.executeUpdate();
       } catch (SQLException ex) {
           System.out.println(ex);
       }
       
       return false;
    }

    public List<Commentaire> afficherCommentaires() {
      List<Commentaire> list = new ArrayList<>();
        try {
           String req = "select * from personne";
           Statement ste = myconnex.createStatement();
           ResultSet res=  ste.executeQuery(req);
           while (res.next()) {
               Commentaire c = new Commentaire();
               c.setContenu(res.getString("contenu"));
               c.setDatecom(res.getDate("datecom"));
               list.add(c);           
           }
       } catch (SQLException ex) {
       }
        return list;
    }    
}
