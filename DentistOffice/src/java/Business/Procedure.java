/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Business;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author miguel
 */
public class Procedure {
    private String procedureCode;
    private String procedureName;
    private String description;
    private String cost;
    private String imageUrl;
    
    public Procedure(){
        this.procedureCode = "";
        this.procedureName = "";
        this.description = "";
        this.cost = "";
        this.imageUrl = "";
    }

    public Procedure(String procedureCode, String procedureName, String description, String cost, String imageUrl) {
        this.procedureCode = procedureCode;
        this.procedureName = procedureName;
        this.description = description;
        this.cost = cost;
        this.imageUrl = imageUrl;
    }
    
    public void selectFromDb(String procCode) throws SQLException, ClassNotFoundException{
        try{
            
            Class.forName("com.mysql.jdbc.Driver");
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost/DentistOffice?autoReconnect=true&useSSL=false",
                    "miguel","password");
            Statement statement = connection.createStatement();
            
            ResultSet resultSet = statement.executeQuery("select * from Procedures where procCode='" + procCode + "';");
            
            resultSet.next();
            
            setAll(resultSet.getString(1), resultSet.getString(2), resultSet.getString(3), resultSet.getString(4), resultSet.getString(5));
            
            
            
            
        }catch(Exception ex){
            ex.printStackTrace();
        }
    }
    
    public void setAll(String procedureCode, String procedureName, String description, String cost, String imageUrl) {
        this.procedureCode = procedureCode;
        this.procedureName = procedureName;
        this.description = description;
        this.cost = cost;
        this.imageUrl = imageUrl;
    };
    
    public String getImageUrl(){
        return imageUrl;
    }

    public String getProcedureCode() {
        return procedureCode;
    }

    public void setProcedureCode(String procedureCode) {
        this.procedureCode = procedureCode;
    }

    public String getProcedureName() {
        return procedureName;
    }

    public void setProcedureName(String procedureName) {
        this.procedureName = procedureName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCost() {
        return cost;
    }

    public void setCost(String cost) {
        this.cost = cost;
    }
    
}
