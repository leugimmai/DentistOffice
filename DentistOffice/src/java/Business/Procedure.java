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
 * Procedure Business Object class
 *
 * @author miguel
 * @version 1.0
 */
public class Procedure {

    private String procedureCode;
    private String procedureName;
    private String description;
    private String cost;
    private String imageUrl;

    /**
     * Initialization of empty procedure class
     */
    public Procedure() {
        this.procedureCode = "";
        this.procedureName = "";
        this.description = "";
        this.cost = "";
        this.imageUrl = "";
    }

    /**
     * ]Constructor method for the procedure class
     *
     * @param procedureCode The procedures code
     * @param procedureName The procedures name
     * @param description The procedures description
     * @param cost The price of the procedure
     * @param imageUrl Image URL of the procedure to display
     */
    public Procedure(String procedureCode, String procedureName, String description, String cost, String imageUrl) {
        this.procedureCode = procedureCode;
        this.procedureName = procedureName;
        this.description = description;
        this.cost = cost;
        this.imageUrl = imageUrl;
    }

    /**
     * Method that finds the procedure in the database and applies the
     * parameters to the class using the procedure code as the parameter being
     * passed.
     *
     * @param procCode The procedures code
     * @throws SQLException
     * @throws ClassNotFoundException
     */
    public void selectFromDb(String procCode) throws SQLException, ClassNotFoundException {
        try {

            Class.forName("com.mysql.jdbc.Driver");
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost/DentistOffice?autoReconnect=true&useSSL=false",
                    "miguel", "password");
            Statement statement = connection.createStatement();

            ResultSet resultSet = statement.executeQuery("select * from Procedures where procCode='" + procCode + "';");

            resultSet.next();

            setAll(resultSet.getString(1), resultSet.getString(2), resultSet.getString(3), resultSet.getString(4), resultSet.getString(5));

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    /**
     * Method that sets all the variables for the Procedure class that was
     * created.
     *
     * @param procedureCode The procedures code
     * @param procedureName The name of the procedure being done
     * @param description The description of the procedure being done
     * @param cost The cost of the procedure
     * @param imageUrl The image URL of the procedure
     */
    public void setAll(String procedureCode, String procedureName, String description, String cost, String imageUrl) {
        this.procedureCode = procedureCode;
        this.procedureName = procedureName;
        this.description = description;
        this.cost = cost;
        this.imageUrl = imageUrl;
    }

    ;
    
    /**
     *Method that returns back the procedures image URL
     * @return string of the image URL
     */
    public String getImageUrl() {
        return imageUrl;
    }

    /**
     * Method that returns back the procedure code
     *
     * @return string of procedure code
     */
    public String getProcedureCode() {
        return procedureCode;
    }

    /**
     * Method that sets the procedure code
     *
     * @param procedureCode The procedures code number
     */
    public void setProcedureCode(String procedureCode) {
        this.procedureCode = procedureCode;
    }

    /**
     * Method that returns back the name of the procedure being done
     *
     * @return string of the procedure name
     */
    public String getProcedureName() {
        return procedureName;
    }

    /**
     * Method that sets the procedures name
     *
     * @param procedureName the name of the procedure
     */
    public void setProcedureName(String procedureName) {
        this.procedureName = procedureName;
    }

    /**
     * Method that returns back the description of the procedure being done
     *
     * @return string of the procedures description
     */
    public String getDescription() {
        return description;
    }

    /**
     * Method that sets the procedures description
     *
     * @param description The procedures description
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Method that returns back the cost of the procedure being done
     *
     * @return string of the procedure cost
     */
    public String getCost() {
        return cost;
    }

    /**
     * Method that sets the cost of the procedure
     *
     * @param cost
     */
    public void setCost(String cost) {
        this.cost = cost;
    }

}
